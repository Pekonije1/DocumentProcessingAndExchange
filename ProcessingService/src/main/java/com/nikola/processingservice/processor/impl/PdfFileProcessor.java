package com.nikola.processingservice.processor.impl;

import com.nikola.processingservice.dto.FileItem;
import com.nikola.processingservice.processor.FileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PdfFileProcessor implements FileProcessor {
    @Override
    public List<FileItem> processFile(File file) throws IOException {
        log.info("Started processing Pdf file...");
        List<FileItem> items = new ArrayList<>();

        PDDocument document = Loader.loadPDF(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        String[] lines = text.split("\\r?\\n");

        // Do not process header
        for (int i = 2; i < lines.length; i++) {
            String line = lines[i].trim();
            if (!line.isEmpty()) {
                String[] columns = line.split("\\s+"); // Split by whitespace
                if (columns.length == 5) {
                    Integer itemId = Integer.parseInt(columns[0]);
                    BigDecimal price = new BigDecimal(columns[1]);
                    Integer quantity = Integer.parseInt(columns[2]);
                    BigDecimal totalPrice = new BigDecimal(columns[3]);
                    BigDecimal tax = new BigDecimal(columns[4]);

                    items.add(new FileItem(itemId, price, quantity, totalPrice, tax));
                }
            }
        }

        log.info("File processing done successfully.");
        return items;
    }
}
