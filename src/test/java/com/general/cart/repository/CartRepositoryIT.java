package com.general.cart.repository;

import com.general.cart.model.entity.Cart;
import com.general.cart.model.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:seed.sql")
public class CartRepositoryIT {


    @Value("${testTenant}")
    private String testTenant;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void verifySaveCart(){
        Cart cartCreated = cartRepository.save(createDummyCart());
        assertThat(cartCreated.getCartId()).isGreaterThan(0L);
    }

    @Test
    public void verifyFindByTenantAndCartId(){
        Cart cart = cartRepository.findByTenantAndCartId(testTenant, 1L);
        assertThat(cart).isNotNull();
        assertThat(cart.getCustomerId()).isEqualTo("C0001");
        assertThat(cart.getItems().size()).isEqualTo(2);
    }

    private Cart createDummyCart(){
        final Item item = Item.builder().code("P0001").name("T0001").unitPrice(new BigDecimal(12.2)).quantity(1).build();
        final Cart cart = Cart.builder().cartId(2L).customerId("C0001").tenant(testTenant).items(new HashSet<>(Collections.singletonList(item))).build();
        return cart;
    }

}
