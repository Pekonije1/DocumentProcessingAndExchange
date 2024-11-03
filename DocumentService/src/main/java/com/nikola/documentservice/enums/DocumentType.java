package com.nikola.documentservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum DocumentType {
    PDF("application/pdf"),
    CSV("text/csv"),
    XML("application/xml");

    private final String value;

    public static final List<DocumentType> SUPPORTED_DOCUMENT_TYPES = List.of(PDF, CSV, XML);

    public static boolean isSupported(String contentType) {
        return Arrays.stream(DocumentType.values())
                .anyMatch(type -> type.getValue().equalsIgnoreCase(contentType));
    }

    public static DocumentType fromValue(String contentType) {
        for (DocumentType type : DocumentType.values()) {
            if (type.getValue().equals(contentType)) {
                return type;
            }
        }
        return null;
    }
}
