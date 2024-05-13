package in.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.BeforeClass;

import static io.restassured.config.HttpClientConfig.httpClientConfig;

public class ApiTestBase {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.config = RestAssuredConfig.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());

        // Example of other configurations such as timeouts
        RestAssured.config = RestAssured.config()
                .httpClient(httpClientConfig().setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000)
                        .setParam("http.connection-manager.timeout", 20000));
    }
}
