package com.nikola.processingservice.factory;

import com.nikola.processingservice.enums.DocumentType;
import com.nikola.processingservice.processor.FileProcessor;
import com.nikola.processingservice.processor.impl.CsvFileProcessor;
import com.nikola.processingservice.processor.impl.PdfFileProcessor;
import com.nikola.processingservice.processor.impl.XmlFileProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileProcessorFactory {
    private final CsvFileProcessor csvFileProcessor;
    private final XmlFileProcessor xmlFileProcessor;
    private final PdfFileProcessor pdfFileProcessor;

    public FileProcessor getFileProcessor(DocumentType documentType) {
        if (documentType.equals(DocumentType.CSV))
            return csvFileProcessor;
        if (documentType.equals(DocumentType.XML))
            return xmlFileProcessor;
        if (documentType.equals(DocumentType.PDF))
            return pdfFileProcessor;
        else
            throw new UnsupportedOperationException("Unsupported file type: " + documentType);
    }
}
