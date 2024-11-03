package com.nikola.documentservice.testcontainer;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlTestContainer extends PostgreSQLContainer<PostgresqlTestContainer> {
    private static final String IMAGE_VERSION = "postgres:latest";

    public PostgresqlTestContainer() {
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
