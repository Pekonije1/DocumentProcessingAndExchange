package com.nikola.documentservice.event;

import com.nikola.documentservice.entity.Document;

public record DocumentProcessFailedEvent(Document document) {
}
