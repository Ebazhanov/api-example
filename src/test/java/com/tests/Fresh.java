package com.tests;

import api.ApiJson;
import api.ApiRequest;
import api.schema.CountryCode;
import base.BaseClass;
import io.qameta.allure.Feature;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Feature("API test examples")
public class Fresh extends BaseClass {


    @Test
    public void getRequestWithSingleCountry() {
        final String resultsApiJson = ApiRequest.makeGetRequest();
        final ApiJson apiJson = ApiJson.from(resultsApiJson);
        final CountryCode result = apiJson.getCountryCode();
        assertEquals(result.getName(), "Germany");
        assertEquals(result.getAlpha2Code(), "DE");
        assertEquals(result.getAlpha3Code(), "DEU");
    }

    @Test
    public void getRequest() {
        final String resultsApiJson = ApiRequest.makeGetRequest();
        final ApiJson apiJson = ApiJson.from(resultsApiJson);
        final CountryCode result = apiJson.getCountryCode();
        assertEquals(result.getName(), "Germany");
        assertEquals(result.getAlpha2Code(), "DE");
        assertEquals(result.getAlpha3Code(), "DEU");
    }

    @Test
    public void postSpecific_TC() {

        String NAME = "Test Country";
        String ALFA2CODE = "TC";
        String ALFA3CODE = "TC";

        given().contentType("application/json")
                .body(getJson(NAME, ALFA2CODE, ALFA3CODE))
                .when()
                .post("http://services.groupkt.com/country/register")
                .then()
                .assertThat()
                .statusCode(200)
                .body("RestResponse.messages[0]", Matchers.equalTo("Country added or overwritten [TC]."));
    }

    private String getJson(String name, String alpha2Code, String alpha3Code) {
        JSONObject jsonObj = new JSONObject()
                .put("name", name)
                .put("alpha2_code", alpha2Code)
                .put("alpha3_code", alpha3Code);
        return jsonObj.toString();
    }

}
