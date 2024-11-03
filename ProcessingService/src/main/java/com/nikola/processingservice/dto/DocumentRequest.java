package com.nikola.processingservice.dto;

import com.nikola.processingservice.enums.DocumentType;
import com.nikola.processingservice.enums.ProcessingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DocumentRequest implements Serializable {
    private String id;
    private String name;
    private String path;
    private DocumentType type;
    private ProcessingStatus status;

}
