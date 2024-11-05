package com.nikola.documentservice.service;

import com.nikola.documentservice.dto.DocumentResponse;
import com.nikola.documentservice.dto.ProcessedData;
import com.nikola.documentservice.dto.UploadResponse;
import com.nikola.documentservice.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    UploadResponse upload(List<MultipartFile> files);

    void save(ProcessedData processedData);

    void failDocumentProcess(String documentId);

    Document getDocument(String documentId);

    DocumentResponse getDocumentResponse(String documentId);
}
