package com.nikola.documentservice.entity;

import com.nikola.documentservice.enums.DocumentType;
import com.nikola.documentservice.enums.ProcessingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "documents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;
    @Enumerated(EnumType.STRING)
    private DocumentType type;
    @Enumerated(EnumType.STRING)
    private ProcessingStatus status;

    @OneToMany(mappedBy = "document")
    private List<DocumentItem> documentItems;

}
