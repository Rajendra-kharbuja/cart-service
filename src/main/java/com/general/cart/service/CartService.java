package com.general.cart.service;

import com.general.cart.model.entity.Cart;
import com.general.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Long createCart(final String tenant, final Cart cart) {

        cart.setTenant(tenant);
        cart.setCreated_at(Timestamp.from(Instant.now(Clock.systemUTC())));
        final Double totalPrice = cart.getItems().stream()
                .map(item -> item.getUnitPrice()*item.getQuantity())
                .reduce(Double::sum)
                .orElse(0.0);
        cart.setTotalPrice(totalPrice);

        final Cart savedCart = cartRepository.save(cart);

        return savedCart.getCartId();
    }

    public Cart findCartById(final String tenant, final Long cartId) {
        return cartRepository.findByTenantAndCartId(tenant, cartId);
    }
}
