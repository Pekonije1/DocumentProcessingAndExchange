package com.nikola.processingservice.unit;

import com.nikola.processingservice.dto.FileItem;
import com.nikola.processingservice.processor.impl.CsvFileProcessor;
import com.nikola.processingservice.processor.impl.PdfFileProcessor;
import com.nikola.processingservice.processor.impl.XmlFileProcessor;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

class FileProcessorTest {
    @Test
    void shouldProcessCsv() throws IOException {
        CsvFileProcessor processor = new CsvFileProcessor();
        File file = ResourceUtils.getFile("classpath:" + "files/" + "item_data.csv");
        List<FileItem> items = processor.processFile(file);
        Assertions.assertNotNull(items);
        Assertions.assertEquals(5, items.size());
    }

    @Test
    void shouldProcessXml() throws FileNotFoundException, JAXBException {
        XmlFileProcessor processor = new XmlFileProcessor();
        File file = ResourceUtils.getFile("classpath:" + "files/" + "item_data.xml");
        List<FileItem> items = processor.processFile(file);
        Assertions.assertNotNull(items);
        Assertions.assertEquals(5, items.size());
    }

    @Test
    void shouldProcessPdf() throws IOException {
        PdfFileProcessor processor = new PdfFileProcessor();
        File file = ResourceUtils.getFile("classpath:" + "files/" + "item_data.pdf");
        List<FileItem> items = processor.processFile(file);
        Assertions.assertNotNull(items);
        Assertions.assertEquals(5, items.size());
    }
}
