package com.nikola.documentservice.event.listener;

import com.nikola.documentservice.enums.ProcessingStatus;
import com.nikola.documentservice.event.DocumentProcessFailedEvent;
import com.nikola.documentservice.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessFailListener {
    public final DocumentRepository documentRepository;

    @EventListener
    public void failProcess(DocumentProcessFailedEvent event) {
        event.document().setStatus(ProcessingStatus.FAILED);
        documentRepository.save(event.document());
    }
}
