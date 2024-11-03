package com.nikola.documentservice.testcontainer;

import org.testcontainers.containers.RabbitMQContainer;

public class RabbitMqTestContainer extends RabbitMQContainer {
    private static final String IMAGE_VERSION = "rabbitmq:management";

    public RabbitMqTestContainer() {
        super(IMAGE_VERSION);
    }

    @Override
    protected void configure() {
        super.configure();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
