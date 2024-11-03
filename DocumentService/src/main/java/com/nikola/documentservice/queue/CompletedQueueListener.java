package com.nikola.documentservice.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikola.documentservice.dto.ProcessedData;
import com.nikola.documentservice.event.DataSuccessfullyProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CompletedQueueListener {
    private final ApplicationEventPublisher publisher;
    private final ObjectMapper objectMapper;
    private final EnqueueService enqueueService;

    @RabbitListener(queues = "${rabbitmq.queue-completed}")
    public void receiveProcessedDocument(String document) {
        try {
            ProcessedData processedData = objectMapper.readValue(document, ProcessedData.class);
            publisher.publishEvent(new DataSuccessfullyProcessedEvent(processedData));
        } catch (Exception e) {
            log.error(e.getMessage());
            enqueueService.sendToDeadMsgQueue(document);
        }

    }
}
