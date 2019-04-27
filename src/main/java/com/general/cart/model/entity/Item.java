package com.general.cart.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Data
@Builder
public class Item {

    @Id
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    private String code;

    private BigDecimal unitPrice;
    private String name;
    private String Description;


}
