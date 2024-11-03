package com.nikola.processingservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    @Value("${rabbitmq.queue-processing:document-processing-queue}")
    private String PROCESSING_QUEUE;
    @Value("${rabbitmq.queue-completed:document-completed-queue}")
    private String COMPLETED_QUEUE;
    @Value("${rabbitmq.queue-dead-msg:dead-messages-queue}")
    private String DEAD_MESSAGES_QUEUE;
    @Value("${rabbitmq.queue-failed-process:failed-process-queue}")
    private String FAILED_PROCESS_QUEUE;

    @Bean
    public Queue processingQueue() {
        return new Queue(PROCESSING_QUEUE, true);
    }

    @Bean
    public Queue completedQueue() {
        return new Queue(COMPLETED_QUEUE, true);
    }

    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_MESSAGES_QUEUE, true);
    }

    @Bean
    public Queue failedQueue() {
        return new Queue(FAILED_PROCESS_QUEUE, true);
    }

}
