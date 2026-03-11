import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OrderApiTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testCreateOrder() {
        String requestBody = """
                {
                  "id": 1500,
                  "petId": 10,
                  "quantity": 1,
                  "status": "placed",
                  "complete": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .body("id", equalTo(1500))
                .body("status", equalTo("placed"));
    }

    @Test
    public void testGetOrderById() {
        given()
                .pathParam("orderId", 1500)
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200)
                .body("petId", equalTo(10))
                .body("complete", equalTo(true));
    }
    @Test
    public void testGetOrderNotFound() {
        given()
                .pathParam("orderId", 99999999)
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(404)
                .body("type", equalTo("error"))
                .body("message", equalTo("Order not found"));
    }
}