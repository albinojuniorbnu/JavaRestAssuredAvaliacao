package tests.scenarios;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.BaseApi;
import tests.models.User;
import tests.requests.UsersApiPage;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(AllureJunit5.class)
public class UsersApiTest {

    private static UsersApiPage usersApiPage;

    @BeforeAll
    public static void setup() {
        new BaseApi("https://jsonplaceholder.typicode.com");
        usersApiPage = new UsersApiPage();
    }

    @Test
    @Feature("Post Users")
    @Description("Incluir novo user")
    public void testPostUser() {
        // Arrange
        User user = new User("Albino", "Jr", "albinojr@example.com");

        // Act
        // Realiza a requisição POST
        Response response = usersApiPage.postUsers(user);
        response.then().statusCode(201);

        // Assert
        // Valida o body da requisição
        usersApiPage.validateId(response, 11);
    }

    @Test
    @Feature("Put Users")
    @Description("Alterar user id 5")
    public void testPutUserId() {
        // Arrange
        User.Geo geo = new User.Geo("-31.8129", "62.5342");
        User.Address address = new User.Address("testes", "351", "testes", "132356498", geo);
        User updatedUser = new User(5, "Testes", "testes", "testes@example.com", address, "(254)954-1289", "testes.info", null);

        // Act
        // Realiza a requisição PUT
        Response response = usersApiPage.putUsersId(updatedUser, 5);
        response.then().statusCode(200)
        .body("email", equalTo("testes@example.com"))
                .body("address.geo.lat", equalTo("-31.8129"))
                .body("address.geo.lng", equalTo("62.5342"))
                .extract()
                .response();
    }
}