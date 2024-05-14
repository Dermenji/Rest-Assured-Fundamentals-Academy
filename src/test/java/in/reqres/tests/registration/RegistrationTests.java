package in.reqres.tests.registration;

import in.reqres.requests.RegistrationRequest;
import in.reqres.tests.ApiTestBase;
import in.reqres.utils.ApiUtils;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RegistrationTests extends ApiTestBase {
    @Test
    public void testSuccessfulRegistration() {
        RegistrationRequest register = new RegistrationRequest("eve.holt@reqres.in", "pistol");

        given()
                .spec(ApiUtils.jsonRequestSpec())
                .body(register)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue());///tetet
    }

    @Test
    public void testUnsuccessfulRegistration() {
        RegistrationRequest register = new RegistrationRequest("sydney@fife", null); // Missing password

        given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}
