package com.general.cart.repository;

import com.general.cart.model.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Cart findByTenantAndCartId(String tenant, Long cartId);

    <S extends Cart> S save(S entity);
}
