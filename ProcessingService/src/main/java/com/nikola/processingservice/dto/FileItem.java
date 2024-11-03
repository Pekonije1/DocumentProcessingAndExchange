package com.nikola.processingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileItem {
    private Integer itemId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
    private BigDecimal tax;
}
