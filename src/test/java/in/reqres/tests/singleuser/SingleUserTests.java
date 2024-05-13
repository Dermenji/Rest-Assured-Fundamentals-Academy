package in.reqres.tests.singleuser;

import in.reqres.response.UserResponse;
import in.reqres.tests.ApiTestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SingleUserTests extends ApiTestBase {
    @Test
    public void testGetUserDetails() {
        UserResponse response = given()
                .when()
                .pathParam("userId", 2)
                .when()
                .get("/users/{userId}")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", containsString("To keep ReqRes free, contributions towards server costs are appreciated!"))
                .extract().as(UserResponse.class);

        // Additional direct Java assertions using the extracted response
        assert response.getData().getEmail().equals("janet.weaver@reqres.in");
        assert response.getSupport().getUrl().contains("reqres.in");
    }

    @Test
    public void testGetUserDetailsPOJO() {
        UserResponse response = given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UserResponse.class);

        // Assertions using TestNG
        assertEquals(response.getData().getId(), 2, "User ID does not match expected value.");
        assertEquals(response.getData().getEmail(), "janet.weaver@reqres.in", "Email does not match expected value.");
        assertEquals(response.getData().getFirstName(), "Janet", "First name does not match expected value.");
        assertEquals(response.getData().getLastName(), "Weaver", "Last name does not match expected value.");
        assertEquals(response.getData().getAvatar(), "https://reqres.in/img/faces/2-image.jpg", "Avatar URL does not match expected value.");
        assertEquals(response.getSupport().getUrl(), "https://reqres.in/#support-heading", "Support URL does not match expected value.");
        assertTrue(response.getSupport().getText().contains("To keep ReqRes free, contributions towards server costs are appreciated!"), "Support text does not match expected value.");
    }
}
