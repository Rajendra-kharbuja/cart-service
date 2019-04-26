package com.general.cart.model.entity;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class Cart {
    private String cartId;
    private Timestamp dateTimeOfCreation;
    private List<Product> products;
    private BigDecimal totalPrice;

//    public Cart(){
//        this.dateTimeOfCreation = Timestamp.from(Instant.now(Clock.systemUTC()));
//    }
}
