package com.nikola.documentservice.dto;

import com.nikola.documentservice.enums.DocumentType;
import com.nikola.documentservice.enums.ProcessingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DocumentResponse implements Serializable {
    private Long id;
    private String name;
    private String path;
    private DocumentType type;
    private ProcessingStatus status;
    private List<DocumentItemResponse> documentItems;
}
