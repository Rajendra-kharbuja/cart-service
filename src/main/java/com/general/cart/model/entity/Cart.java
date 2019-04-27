package com.general.cart.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "cart")
@Data
@Builder
public class Cart {
    @Id
    @Column(name = "cart_id")
    private Long cartId;


    private String tenantId;
    private String customerId;

    private Timestamp created_at;

    @OneToMany(mappedBy = "cart")
    private Set<Item> items;

    private BigDecimal totalPrice;

}
