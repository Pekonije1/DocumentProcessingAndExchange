package com.nikola.documentservice.event;

import com.nikola.documentservice.dto.ProcessedData;

public record DataSuccessfullyProcessedEvent(ProcessedData processedData) {
}
