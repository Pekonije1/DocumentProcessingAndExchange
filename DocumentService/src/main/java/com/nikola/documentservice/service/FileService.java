package com.nikola.documentservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileService {
    Path saveFile(MultipartFile file);
}
