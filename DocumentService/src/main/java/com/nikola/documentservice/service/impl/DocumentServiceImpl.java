package com.nikola.documentservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nikola.documentservice.dto.DocumentResponse;
import com.nikola.documentservice.dto.FileItem;
import com.nikola.documentservice.dto.ProcessedData;
import com.nikola.documentservice.entity.Document;
import com.nikola.documentservice.entity.DocumentItem;
import com.nikola.documentservice.enums.DocumentType;
import com.nikola.documentservice.enums.ProcessingStatus;
import com.nikola.documentservice.event.DocumentProcessFailedEvent;
import com.nikola.documentservice.exception.DocumentNotFoundException;
import com.nikola.documentservice.mapper.DocumentMapper;
import com.nikola.documentservice.queue.EnqueueService;
import com.nikola.documentservice.repository.DocumentItemRepository;
import com.nikola.documentservice.repository.DocumentRepository;
import com.nikola.documentservice.service.DocumentService;
import com.nikola.documentservice.service.FileService;
import com.nikola.documentservice.validator.DocumentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final EnqueueService enqueueService;
    private final ApplicationEventPublisher publisher;
    private final FileService fileService;
    private final DocumentItemRepository documentItemRepository;
    private final DocumentMapper documentMapper;

    @Override
    public void upload(List<MultipartFile> files) {
        DocumentValidator.validate(files);

        files.forEach(file -> {
            Path uploadPath = fileService.saveFile(file);

            Document document = Document.builder()
                    .type(DocumentType.fromValue(file.getContentType()))
                    .status(ProcessingStatus.PROCESSING)
                    .path(uploadPath.toAbsolutePath().toString())
                    .name(file.getOriginalFilename())
                    .build();
            document = documentRepository.save(document);

            try {
                enqueueService.sendUnprocessedDocument(document);
            } catch (JsonProcessingException e) {
                publisher.publishEvent(new DocumentProcessFailedEvent(document));
            }
        });
    }

    @Override
    public void save(ProcessedData processedData) {
        log.info("Storing processed data...");
        Document document = getDocument(String.valueOf(processedData.getDocumentId()));

        List<FileItem> items = processedData.getItemList().getItems();
        if (!items.isEmpty())
            items.forEach(item -> {
                documentItemRepository.save(DocumentItem.builder()
                        .document(document)
                        .tax(item.getTax())
                        .price(item.getPrice())
                        .itemId(item.getItemId())
                        .quantity(item.getQuantity())
                        .totalPrice(item.getTotalPrice())
                        .build());
            });

        document.setStatus(ProcessingStatus.COMPLETED);
        documentRepository.save(document);
    }

    @Override
    public void failDocumentProcess(String documentId) {
        log.info("File with document id {} failed to process: ", documentId);
        Document document = getDocument(documentId);
        document.setStatus(ProcessingStatus.FAILED);
        documentRepository.save(document);
    }

    public Document getDocument(String documentId) {
        return documentRepository.findById(Long.valueOf(documentId))
                .orElseThrow(() ->
                        new DocumentNotFoundException("Document with id: " + documentId + " not found. "));
    }

    @Override
    public DocumentResponse getDocumentResponse(String documentId) {
        return documentMapper.toResponse(getDocument(documentId));
    }
}
