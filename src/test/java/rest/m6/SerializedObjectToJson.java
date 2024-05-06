package rest.m6;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import psrestassured.Repository;

public class SerializedObjectToJson {

    static final String REPOS = "https://api.github.com/user/repos";
    static final String TOKEN = "your token here";

    @Test
    void postTest() {

        var repo = new Repository("deleteme");

        RestAssured
                .given()
                    .auth().oauth2(TOKEN)
                    .body(repo)
                .when()
                    .post(REPOS)
                .then()
                    .statusCode(201);
    }
}
