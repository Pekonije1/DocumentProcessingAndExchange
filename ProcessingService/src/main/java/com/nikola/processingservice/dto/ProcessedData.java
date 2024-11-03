package com.nikola.processingservice.dto;

import com.nikola.processingservice.enums.ProcessingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessedData {
    private ItemList itemList;
    private Long documentId;
    private ProcessingStatus processingStatus;
}
