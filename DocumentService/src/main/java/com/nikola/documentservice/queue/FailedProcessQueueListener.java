package com.nikola.documentservice.queue;

import com.nikola.documentservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FailedProcessQueueListener {
    private final DocumentService documentService;
    private final EnqueueService enqueueService;

    @RabbitListener(queues = "${rabbitmq.queue-failed-process}")
    public void receiveProcessedDocument(String documentId) {
        try {
            documentService.failDocumentProcess(documentId);
        } catch (Exception e) {
            log.error(e.getMessage());
            enqueueService.sendToDeadMsgQueue(documentId);
        }
    }
}
