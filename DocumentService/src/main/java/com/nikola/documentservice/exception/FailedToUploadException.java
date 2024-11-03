package com.nikola.documentservice.exception;

public class FailedToUploadException extends RuntimeException {
    public FailedToUploadException(String message) {
        super(message);
    }
}
