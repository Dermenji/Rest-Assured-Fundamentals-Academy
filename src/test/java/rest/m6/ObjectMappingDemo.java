package rest.m6;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;
import psrestassured.AnotherUser;
import psrestassured.User;

import static org.testng.AssertJUnit.assertEquals;

public class ObjectMappingDemo {

    static final String URL = "https://api.github.com/users/rest-assured";

    @Test
    void objectMappingTestOne() {
        User user = RestAssured.get(URL).as(User.class);

        assertEquals(user.getLogin(), "rest-assured");
        assertEquals(user.getId(), 19369327);
        assertEquals(user.getPublicRepos(),2);
    }


    @Test
    void objectMappingRelyingOnMapperType() {

        User user = RestAssured.get(URL).as(User.class, ObjectMapperType.GSON);
        assertEquals(user.getLogin(), "rest-assured");
    }

}
