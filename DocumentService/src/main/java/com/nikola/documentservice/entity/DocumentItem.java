package com.nikola.documentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "document_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer itemId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
    private BigDecimal tax;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}