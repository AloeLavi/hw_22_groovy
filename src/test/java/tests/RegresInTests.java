package tests;

import static java.util.logging.Level.ALL;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.CreateUserBody;
import lombok.CreateUserResponse;
import lombok.GetUserResponse;
import org.junit.jupiter.api.Test;
import specs.CommonSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static specs.CommonSpecs.CommonRequestSpec;
import static specs.UserSpecs.UserRequestSpec;
import static specs.UserSpecs.UserResponseSpec;


public class RegresInTests {

    @Test
    void createUser() {
        CreateUserBody body = new CreateUserBody();
        body.setName("morpheus");
        body.setJob("leader");

        CreateUserResponse response= given()
                .spec(UserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(UserResponseSpec)
                .extract().as(CreateUserResponse.class);

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");

    }
    @Test
    void getSingleUser() {

        GetUserResponse response = given()
                .spec(UserRequestSpec)
                .when()
                .get("/2")
                .then()
                .statusCode(200)
                .extract().as(GetUserResponse.class);
        assertThat(response.getUser().getEmail()).isEqualTo("janet.weaver@reqres.in");
        assertThat(response.getUser().getFirstName()).isEqualTo("Janet");
        assertThat(response.getUser().getLastName()).isEqualTo("Weaver");
        assertThat(response.getSupport().getUrl()).isEqualTo("https://reqres.in/#support-heading");

    }

    @Test
    void getUsersList() {
        given()
                .spec(UserRequestSpec)
                .when()
                .get("?page=2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.findAll{it.last_name == \"Edwards\"}.email",
                        hasItem("george.edwards@reqres.in"))
               ;

    }

    @Test
    void getUnknownList() {
        given()
                .spec(CommonRequestSpec)
                .when()
                .get("/api/unknown")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.findAll{it.year > 2001 }.name",
                        hasItems("blue turquoise","tigerlily"));


    }


}
