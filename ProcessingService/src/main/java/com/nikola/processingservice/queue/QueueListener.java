package com.nikola.processingservice.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikola.processingservice.dto.DocumentRequest;
import com.nikola.processingservice.dto.FileItem;
import com.nikola.processingservice.dto.ItemList;
import com.nikola.processingservice.dto.ProcessedData;
import com.nikola.processingservice.enums.ProcessingStatus;
import com.nikola.processingservice.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class QueueListener {

    private final ObjectMapper objectMapper;
    private final EnqueueService enqueueService;
    private final FileService fileService;

    @RabbitListener(queues = "${rabbitmq.queue-processing}")
    public void receiveProcessedDocument(String document) {
        try {
            log.info("Received message from processing queue: " + document);
            DocumentRequest documentRequest = objectMapper.readValue(document, DocumentRequest.class);
            try {
                List<FileItem> fileItems = fileService.getItemsFromFile(documentRequest);
                enqueueService.sendProcessedData(ProcessedData.builder()
                        .documentId(Long.valueOf(documentRequest.getId()))
                        .processingStatus(ProcessingStatus.COMPLETED)
                        .itemList(ItemList.builder().items(fileItems).build())
                        .build());
            } catch (Exception e) {
                log.error(e.getMessage());
                enqueueService.sendToFailedQueue(documentRequest.getId());
            }
        } catch (JsonProcessingException jsonProcessingException) {
            log.error(jsonProcessingException.getMessage());
            enqueueService.sendToDeadMsgQueue(document);
        }
    }
}