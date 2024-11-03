package com.nikola.documentservice.exception;

public class FileTransferFailedException extends RuntimeException {
    public FileTransferFailedException(String message) {
        super(message);
    }
}
