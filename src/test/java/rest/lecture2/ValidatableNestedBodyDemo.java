package rest.lecture2;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ValidatableNestedBodyDemo {

    static final String LIMIT_EP = "https://api.github.com/rate_limit";

    @Test
    void nestedBodyValidation() {
        RestAssured.get(LIMIT_EP)
                .then()
                .rootPath("resources.core")
                    .body("limit", equalTo(60))
                    .body("remaining", lessThanOrEqualTo(60))
                    .body("reset", notNullValue())
                .rootPath("resources.search")
                    .body("limit", equalTo(10))
                    .body("remaining", lessThanOrEqualTo(10))
                .noRootPath()
                    .body("resources.graphql.limit", equalTo(0));
    }

    public static class ValidatableResponseRepeatingItemsDemo {

        public static final String USERS_EP = "https://reqres.in/api/users?page=1";

        @Test
        void repeatingItems() {
            RestAssured.get(USERS_EP)
                    .then()
                    //.body("data.id", equalTo(1))
                    .body("data.id[0]", equalTo(1))
                    .body("data.id[1]", equalTo(2))
                    .body("data.first_name[2]", equalTo("Emma"))
                    .body("data.first_name[3]", equalTo("Eve"))
    //                .body("data.first_name", containsInAnyOrder("Eve", "Emma"))
                    .body("data.first_name", hasItem("Eve"))
                    .body("data.first_name", hasItems("Eve", "Emma"))
                    .body("data.first_name", hasItem(endsWith("ma")));
        }
    }
}
