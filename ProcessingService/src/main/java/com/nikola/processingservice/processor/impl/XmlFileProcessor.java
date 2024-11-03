package com.nikola.processingservice.processor.impl;

import com.nikola.processingservice.dto.FileItem;
import com.nikola.processingservice.dto.ItemList;
import com.nikola.processingservice.processor.FileProcessor;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@Slf4j
public class XmlFileProcessor implements FileProcessor {
    @Override
    public List<FileItem> processFile(File file) throws JAXBException {
        log.info("Started processing Xml file...");
        JAXBContext jaxbContext = JAXBContext.newInstance(ItemList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ItemList itemList = (ItemList) unmarshaller.unmarshal(file);

        log.info("File processing done successfully.");
        return itemList.getItems();
    }
}
