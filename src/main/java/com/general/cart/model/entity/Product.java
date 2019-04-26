package com.general.cart.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String code;
    private BigDecimal unitPrice;
    private String name;
    private String Description;
    private Boolean isActive;
}
