package com.nikola.documentservice.controller;

import com.nikola.documentservice.dto.DocumentResponse;
import com.nikola.documentservice.dto.UploadResponse;
import com.nikola.documentservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentservice;

    @PostMapping("/upload")
    public UploadResponse uploadDocuments(@RequestParam List<MultipartFile> files) {
        return documentservice.upload(files);
    }

    @GetMapping("/{documentId}")
    public DocumentResponse get(@PathVariable("documentId") String documentId) {
        return documentservice.getDocumentResponse(documentId);
    }
}
