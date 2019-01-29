package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

import java.util.ResourceBundle;

public class ApiRequest {

    private static final String SEARCH_API_URL = ResourceBundle.getBundle("test").getString("SEARCH_API_URL");

    @Step
    public static String makeGetRequest() {
        String json = RestAssured.given()
                .log().all()
                .then().log().all()
                .request()
                .when()
                .get(SEARCH_API_URL)
                .getBody()
                .asString();
        return json;
    }

}
