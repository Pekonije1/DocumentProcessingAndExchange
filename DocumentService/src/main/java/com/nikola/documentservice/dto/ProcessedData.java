package com.nikola.documentservice.dto;

import com.nikola.documentservice.enums.ProcessingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessedData {
    private ItemList itemList;
    private Long documentId;
    private ProcessingStatus processingStatus;
}
