package com.nikola.documentservice.validator;

import com.nikola.documentservice.enums.DocumentType;
import com.nikola.documentservice.exception.DocumentInvalidException;
import com.nikola.documentservice.exception.DocumentTypeNotSupportedException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@UtilityClass
@Slf4j
public class DocumentValidator {
    public static void validate(List<MultipartFile> documents) {
        log.info("Validating received files...");
        if (documents.isEmpty())
            throw new DocumentInvalidException("No documents received.");
        documents.forEach(
                document -> {
                    if (!DocumentType.isSupported(document.getContentType())) {
                        throw new DocumentTypeNotSupportedException("Document type: "
                                + document.getContentType() + " not supported.");
                    }
                }
        );
    }
}
