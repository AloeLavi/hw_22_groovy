package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class UserSpecs {
    public static RequestSpecification UserRequestSpec = with()
            .filter(new AllureRestAssured())
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification UserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
         //   .expectStatusCode(201)
            .expectBody("id", notNullValue())
            .build();

}
