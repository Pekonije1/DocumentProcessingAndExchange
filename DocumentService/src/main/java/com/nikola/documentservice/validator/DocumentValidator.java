package com.nikola.documentservice.validator;

import com.nikola.documentservice.enums.DocumentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
@Slf4j
public class DocumentValidator {
    public static boolean isValid(MultipartFile document) {
        log.info("Validating received file: {}", document.getOriginalFilename());
        return DocumentType.isSupported(document.getContentType());
    }
}

