package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class GetRequest {

    @Step
    public static String countryGetRequest(String url, String alpha2Code) {
        String json = RestAssured.given()
                .log().all()
                .then().log().all()
                .request()
                .when()
                .get(url + alpha2Code)
                .getBody()
                .asString();
        return json;
    }
}
