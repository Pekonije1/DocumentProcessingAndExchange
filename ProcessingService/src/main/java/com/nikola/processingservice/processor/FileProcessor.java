package com.nikola.processingservice.processor;

import com.nikola.processingservice.dto.FileItem;
import jakarta.xml.bind.JAXBException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileProcessor {
    List<FileItem> processFile(File file) throws IOException, JAXBException;
}
