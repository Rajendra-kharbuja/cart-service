package com.general.cart.service;

import com.general.cart.CartAbstractTest;
import com.general.cart.model.entity.Cart;
import com.general.cart.model.entity.Item;
import com.general.cart.repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest extends CartAbstractTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void verifyTotalPriceForACart() {
        final Cart cart = this.createDummyCart();
        ArgumentCaptor<Cart> cartArgumentCaptor = ArgumentCaptor.forClass(Cart.class);

        doReturn(cart).when(cartRepository).save(cart);
        cartService.createCart(testTenant, cart);

        verify(cartRepository).save(cartArgumentCaptor.capture());

        Cart cartArgument = cartArgumentCaptor.getValue();
        Set<Item> items = cartArgument.getItems();
        assertThat(items).isEqualTo(cart.getItems());

        // ∑(unitprice * quantity) -> 12.2 + (10.2 * 2)
        assertThat(cart.getTotalPrice()).isEqualTo(12.2 + (10.2* 2));

    }

}
