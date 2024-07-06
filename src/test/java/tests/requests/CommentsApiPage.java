package tests.requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommentsApiPage {

    public Response getCommentsByName(String name) {
        return given()
                .queryParam("name", name)
                .when()
                .get("/comments");
    }

    public void validateComment(Response response, int position, int postId, int id, String name, String email, String body) {
        response.then()
                .body("[" + position + "].postId", equalTo(postId))
                .body("[" + position + "].id", equalTo(id))
                .body("[" + position + "].name", equalTo(name))
                .body("[" + position + "].email", equalTo(email))
                .body("[" + position + "].body", equalTo(body));
    }
}