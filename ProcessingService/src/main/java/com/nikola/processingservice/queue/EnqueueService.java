package com.nikola.processingservice.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikola.processingservice.dto.ProcessedData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnqueueService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.queue-completed:document-completed-queue}")
    private String COMPLETED_QUEUE;

    @Value("${rabbitmq.queue-dead-msg:dead-messages-queue}")
    private String DEAD_MESSAGES_QUEUE;

    @Value("${rabbitmq.queue-failed-process:failed-process-queue}")
    private String FAILED_PROCESS_QUEUE;

    public void sendProcessedData(ProcessedData processedData) throws JsonProcessingException {
        log.info("Sending processed data...");
        rabbitTemplate.convertAndSend(COMPLETED_QUEUE, objectMapper.writeValueAsString(processedData));
        log.info("Data successfully sent.");
    }

    public void sendToDeadMsgQueue(String message) {
        log.info("Sending message to dead letter queue: " + message);
        rabbitTemplate.convertAndSend(DEAD_MESSAGES_QUEUE, message);
    }

    public void sendToFailedQueue(String message) {
        log.info("Sending message to failed queue: " + message);
        rabbitTemplate.convertAndSend(FAILED_PROCESS_QUEUE, message);
    }
}
