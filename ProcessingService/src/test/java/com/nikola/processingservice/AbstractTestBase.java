package com.nikola.processingservice;

import com.nikola.processingservice.testcontainer.RabbitMqTestContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public abstract class AbstractTestBase {
    @ServiceConnection
    @Container
    protected static final RabbitMqTestContainer rabbitMqContainer = new RabbitMqTestContainer();

}
