package com.general.cart.controller;

import com.general.cart.CartAbstractTest;
import com.general.cart.model.entity.Cart;
import com.general.cart.model.entity.response.CartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:seed.sql")
public class CartControllerIT extends CartAbstractTest {

    @Test
    public void verifyCreateCart(){
        final Cart cartToBeCreated = this.createDummyCart();
        final CartResponse cartResponse =
            given()
                    .spec(getDefaultRequestSpec())
                    .body(cartToBeCreated)
                    .when()
                    .post("/carts")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.CREATED.value())
                    .extract()
                    .body()
                    .as(CartResponse.class);

        assertThat(cartResponse.getCartId()).isGreaterThan(0L);
    }

    @Test
    public void verifyCreateCartWithoutTenantHeaderReturnsBadRequest(){
        final Cart cartToBeCreated = this.createDummyCart();
        given()
                .spec(getRequestSpecWithoutTenantHeader())
                .body(cartToBeCreated)
                .when()
                .post("/carts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void verifyGetCart(){
        final Cart cart =
                given()
                        .spec(getDefaultRequestSpec())
                        .when()
                        .get("/carts/1")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .body()
                        .as(Cart.class);
        assertThat(cart.getCartId()).isEqualTo(1L);
        assertThat(cart.getItems().size()).isEqualTo(2);
        assertThat(cart.getTotalPrice()).isEqualTo(50.6);

    }
}
