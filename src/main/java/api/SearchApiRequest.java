package api;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class SearchApiRequest {

    private static final String SEARCH_API_URL = ResourceBundle.getBundle("test").getString("SEARCH_API_URL");

    public static String makeSearchRequest() {
        String json = given()
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
