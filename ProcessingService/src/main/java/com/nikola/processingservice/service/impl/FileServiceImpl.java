package com.nikola.processingservice.service.impl;

import com.nikola.processingservice.dto.DocumentRequest;
import com.nikola.processingservice.dto.FileItem;
import com.nikola.processingservice.exception.ProcessingFileFailedException;
import com.nikola.processingservice.factory.FileProcessorFactory;
import com.nikola.processingservice.processor.FileProcessor;
import com.nikola.processingservice.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {
    private final FileProcessorFactory fileProcessorFactory;

    public List<FileItem> getItemsFromFile(DocumentRequest documentRequest) {
        File file = new File(documentRequest.getPath());
        FileProcessor fileProcessor = fileProcessorFactory.getFileProcessor(documentRequest.getType());
        try {
            return fileProcessor.processFile(file);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ProcessingFileFailedException("Processing failed for documentId: "
                    + documentRequest.getId());
        }
    }
}
