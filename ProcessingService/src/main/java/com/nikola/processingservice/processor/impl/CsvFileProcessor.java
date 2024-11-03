package com.nikola.processingservice.processor.impl;

import com.nikola.processingservice.dto.FileItem;
import com.nikola.processingservice.processor.FileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CsvFileProcessor implements FileProcessor {
    @Override
    public List<FileItem> processFile(File file) throws IOException {
        log.info("Started processing Pdf file...");
        List<FileItem> items = new ArrayList<>();
        FileReader reader = new FileReader(file);
        CSVParser parser = new CSVParser(reader, CSVFormat.Builder
                .create(CSVFormat.DEFAULT)
                .setHeader("itemId", "price", "quantity", "totalPrice", "tax")
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build());

        for (CSVRecord record : parser) {
            Integer itemId = Integer.parseInt(record.get("itemId"));
            BigDecimal price = new BigDecimal(record.get("price"));
            Integer quantity = Integer.parseInt(record.get("quantity"));
            BigDecimal totalPrice = new BigDecimal(record.get("totalPrice"));
            BigDecimal tax = new BigDecimal(record.get("tax"));

            FileItem item = new FileItem(itemId, price, quantity, totalPrice, tax);
            items.add(item);
        }
        log.info("File processing done successfully.");
        return items;
    }
}
