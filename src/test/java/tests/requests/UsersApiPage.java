package tests.requests;

import io.restassured.response.Response;
import tests.models.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersApiPage {

    public Response postUsers(User requestBody) {
        return given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users");
    }

    public Response putUsersId(User updatedUser, int id){
        return given()
                .header("Content-Type", "application/json")
                .body(updatedUser)
                .when()
                .put("/users/" + id);
    }

    public void validateId(Response response, int id) {
        response.then()
                .body("id", equalTo(id));
    }
}