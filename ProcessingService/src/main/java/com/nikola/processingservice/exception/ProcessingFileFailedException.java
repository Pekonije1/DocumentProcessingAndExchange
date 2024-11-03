package com.nikola.processingservice.exception;

public class ProcessingFileFailedException extends RuntimeException {
    public ProcessingFileFailedException(String message) {
        super(message);
    }
}
