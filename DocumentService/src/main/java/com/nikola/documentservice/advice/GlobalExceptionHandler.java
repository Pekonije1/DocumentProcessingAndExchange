package com.nikola.documentservice.advice;

import com.nikola.documentservice.dto.ErrorDto;
import com.nikola.documentservice.exception.DocumentInvalidException;
import com.nikola.documentservice.exception.DocumentNotFoundException;
import com.nikola.documentservice.exception.FailedToUploadException;
import com.nikola.documentservice.exception.FileTransferFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleDocumentInvalidException(DocumentInvalidException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleDocumentNotFoundException(DocumentNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleDocumentTypeNotSupportedException(FailedToUploadException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorDto.builder().message("Unexpected error occurred").build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleDocumentTypeNotSupportedException(FileTransferFailedException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorDto.builder().message("Unexpected error occurred.").build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleUnexpectedError(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorDto.builder().message("Unexpected error occurred.").build());
    }
}
