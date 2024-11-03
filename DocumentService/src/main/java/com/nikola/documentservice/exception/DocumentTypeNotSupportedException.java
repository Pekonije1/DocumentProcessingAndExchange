package com.nikola.documentservice.exception;

public class DocumentTypeNotSupportedException extends RuntimeException {
    public DocumentTypeNotSupportedException(String message) {
        super(message);
    }
}
