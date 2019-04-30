package com.general.cart.repository;

import com.general.cart.CartAbstractTest;
import com.general.cart.model.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:seed.sql")
public class CartRepositoryIT extends CartAbstractTest {


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

}
