package rest.m6crud;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class OtherMethodsDemo {

    static final String REPOS_EP = "https://api.github.com/user/repos";
    static final String TOKEN = "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";

    @Test(description = "Create a repo")
    void postTest() {
        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
              //  .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme\"}")
                .when()
                .post(REPOS_EP)
                .then()
                .statusCode(201);
    }

    @Test(description = "Update a repo")
    void patchTest() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme-patched1\"}")
                .when()
                .post("https://api.github.com/repos/dermenji/deleteme")
                .then()
                .statusCode(200);
    }

    @Test(description = "Delete a repo")
    void deleteTest() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .when()
                .delete("https://api.github.com/repos/dermenji/deleteme-patched1")
                .then()
                .statusCode(204);
    }
}
