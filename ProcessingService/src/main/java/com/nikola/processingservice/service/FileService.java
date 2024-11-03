package com.nikola.processingservice.service;

import com.nikola.processingservice.dto.DocumentRequest;
import com.nikola.processingservice.dto.FileItem;

import java.util.List;

public interface FileService {
    List<FileItem> getItemsFromFile(DocumentRequest documentRequest);
}
