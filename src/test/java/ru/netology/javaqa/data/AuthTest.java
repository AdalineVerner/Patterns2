package ru.netology.javaqa.data;

import com.codeborne.selenide.Condition;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;

public class AuthTest {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private AuthTest(){

    }
    static DataGenerator.RegistrationDto post(DataGenerator.RegistrationDto user){
        given()
                .spec(requestSpec)
                .body(user)
                .when().log().all()
                .post("/api/system/users")
                .then().log().all()
                .statusCode(200);
        return user;

    }


}
