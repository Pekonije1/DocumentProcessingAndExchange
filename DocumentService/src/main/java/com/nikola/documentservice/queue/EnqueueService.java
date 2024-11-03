package com.nikola.documentservice.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikola.documentservice.entity.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnqueueService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.queue-processing:document-processing-queue}")
    private String PROCESSING_QUEUE;

    @Value("${rabbitmq.queue-dead-msg:dead-messages-queue}")
    private String DEAD_MESSAGES_QUEUE;

    public void sendUnprocessedDocument(Document document) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(PROCESSING_QUEUE, objectMapper.writeValueAsString(document));
    }

    public void sendToDeadMsgQueue(String message) {
        rabbitTemplate.convertAndSend(DEAD_MESSAGES_QUEUE, message);
    }
}
