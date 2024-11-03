package com.nikola.documentservice;

import com.nikola.documentservice.testcontainer.PostgresqlTestContainer;
import com.nikola.documentservice.testcontainer.RabbitMqTestContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public abstract class AbstractTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @ServiceConnection
    @Container
    protected static final PostgresqlTestContainer postgresContainer = new PostgresqlTestContainer();

    @ServiceConnection
    @Container
    protected static final RabbitMqTestContainer rabbitMqContainer = new RabbitMqTestContainer();

}
