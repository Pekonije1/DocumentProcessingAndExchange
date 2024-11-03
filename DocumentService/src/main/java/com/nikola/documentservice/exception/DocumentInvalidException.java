package com.nikola.documentservice.exception;

public class DocumentInvalidException extends RuntimeException {
    public DocumentInvalidException(String message) {
        super(message);
    }
}
