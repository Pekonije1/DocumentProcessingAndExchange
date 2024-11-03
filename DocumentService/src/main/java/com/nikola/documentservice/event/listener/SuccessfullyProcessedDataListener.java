package com.nikola.documentservice.event.listener;

import com.nikola.documentservice.event.DataSuccessfullyProcessedEvent;
import com.nikola.documentservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class SuccessfullyProcessedDataListener {

    private final DocumentService documentService;

    @EventListener
    public void saveData(DataSuccessfullyProcessedEvent event) {
        documentService.save(event.processedData());
    }
}
