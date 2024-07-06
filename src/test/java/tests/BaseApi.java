package tests;

import io.restassured.RestAssured;

public class BaseApi {
    public BaseApi(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }
}
