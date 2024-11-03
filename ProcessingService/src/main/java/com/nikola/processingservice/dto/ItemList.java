package com.nikola.processingservice.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@XmlRootElement(name = "root")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemList {
    private List<FileItem> items;

    @XmlElement(name = "row")
    public List<FileItem> getItems() {
        return items;
    }

}
