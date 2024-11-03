package com.nikola.documentservice.service.impl;

import com.nikola.documentservice.service.FileService;
import com.nikola.documentservice.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${documents.upload-path:/Users/nikolapekovic/Desktop/documentexchange/documentservice}")
    private String UPLOAD_PATH;

    @Override
    public Path saveFile(MultipartFile file) {
        log.info("Saving file to location: " + UPLOAD_PATH);
        Path uploadPath = Paths.get(UPLOAD_PATH);
        return UploadUtil.transfer(file, uploadPath);
    }
}
