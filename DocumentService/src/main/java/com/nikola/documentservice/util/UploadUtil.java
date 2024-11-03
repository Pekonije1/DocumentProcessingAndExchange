package com.nikola.documentservice.util;

import com.nikola.documentservice.exception.FileTransferFailedException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

@UtilityClass
@Slf4j
public class UploadUtil {
    public static Path transfer(MultipartFile file, Path uploadPath) {
        try {
            String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
            String fileName = System.currentTimeMillis() + "_" + originalFilename;
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath);
            return filePath;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileTransferFailedException("File transfer failed with error: " + e.getMessage());
        }
    }
}
