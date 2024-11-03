package com.nikola.documentservice.mapper;

import com.nikola.documentservice.dto.DocumentItemResponse;
import com.nikola.documentservice.entity.DocumentItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentItemMapper {
    DocumentItemResponse toResponse(DocumentItem documentItem) {
        return DocumentItemResponse.builder()
                .id(documentItem.getId())
                .documentId(documentItem.getDocument().getId())
                .tax(documentItem.getTax())
                .price(documentItem.getPrice())
                .itemId(documentItem.getItemId())
                .totalPrice(documentItem.getTotalPrice())
                .quantity(documentItem.getQuantity())
                .build();
    }

    List<DocumentItemResponse> toResponse(List<DocumentItem> documentItems) {
        return documentItems.stream().map(this::toResponse).toList();
    }
}
