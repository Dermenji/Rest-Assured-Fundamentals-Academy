package in.reqres.tests.users;

import in.reqres.requests.User;
import in.reqres.tests.ApiTestBase;
import in.reqres.utils.ApiUtils;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertNotNull;

public class UserTests extends ApiTestBase {
    @Test
    public void testGetSingleUser() {
        given()
                .pathParam("userId", 2)
                .when()
                .get("/users/{userId}")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setName("morpheus");
        newUser.setJob("leader");

        User createdUser = given()
                .spec(ApiUtils.jsonRequestSpec())
                .body(newUser)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .extract().as(User.class);

        assertNotNull(createdUser.getId());
    }
}
