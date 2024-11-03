package com.nikola.documentservice.mapper;

import com.nikola.documentservice.dto.DocumentResponse;
import com.nikola.documentservice.entity.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentMapper {
    private final DocumentItemMapper documentItemMapper;
    public DocumentResponse toResponse(Document document) {
        return DocumentResponse.builder()
                .id(document.getId())
                .type(document.getType())
                .name(document.getName())
                .path(document.getPath())
                .status(document.getStatus())
                .documentItems(documentItemMapper.toResponse(document.getDocumentItems()))
                .build();
    }
}
