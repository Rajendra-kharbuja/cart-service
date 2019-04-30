package com.general.cart;

import com.general.cart.model.entity.Cart;
import com.general.cart.model.entity.Item;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.Arrays;
import java.util.HashSet;

public abstract class CartAbstractTest {
    @Value("${testTenant}")
    protected String testTenant;

    @Value("${deploymentTarget}")
    protected String deploymentTarget;

    @LocalServerPort
    protected int port;


    protected RequestSpecification getDefaultRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addHeader("tenant", testTenant);
        builder.setContentType(ContentType.JSON);
        builder.setBaseUri(deploymentTarget);
        builder.setPort(port);

        return builder.build();
    }

    protected RequestSpecification getRequestSpecWithoutTenantHeader() {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setContentType(ContentType.JSON);
        builder.setBaseUri(deploymentTarget);
        builder.setPort(port);

        return builder.build();
    }

    protected Cart createDummyCart(){
        final Item item1 = Item.builder().code("P0001").name("T0001").unitPrice(12.2).quantity(1).build();
        final Item item2 = Item.builder().code("P0002").name("T0002").unitPrice(10.2).quantity(2).build();
        return Cart.builder().cartId(2L).customerId("C0001")
                .tenant(testTenant)
                .items(new HashSet<>(Arrays.asList(item1,item2))).build();
    }
}
