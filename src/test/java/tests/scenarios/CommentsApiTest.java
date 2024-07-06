package tests.scenarios;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.BaseApi;
import tests.requests.CommentsApiPage;

@ExtendWith(AllureJunit5.class)
public class CommentsApiTest {
    private static CommentsApiPage commentsApiPage;

    @BeforeAll
    public static void setup() {
        new BaseApi("https://jsonplaceholder.typicode.com");
        commentsApiPage = new CommentsApiPage();
    }

    @Test
    @Feature("Get Comments")
    @Description("Buscar comentários pelo nome")
    public void testGetCommentsByName() {
        // Realiza a requisição GET
        Response response = commentsApiPage.getCommentsByName("alias odio sit");
        response.then().statusCode(200);

        // Valida o body da requisição
        commentsApiPage.validateComment(response, 0, 1, 4, "alias odio sit", "Lew@alysha.tv",
                "non et atque\noccaecati deserunt quas accusantium unde odit nobis qui voluptatem\nquia voluptas consequuntur itaque dolor\net qui rerum deleniti ut occaecati");
    }
}