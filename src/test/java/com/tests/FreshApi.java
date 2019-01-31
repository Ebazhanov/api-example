package com.tests;

import api.ApiJson;
import api.DataProviderStorage;
import api.schema.CountryCode;
import base.BaseApiClass;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static api.GetRequest.countriesGetRequest;
import static api.GetRequest.countryGetRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;
import static org.testng.Assert.assertEquals;

@Feature("API test examples")
public class FreshApi extends BaseApiClass {

    public static final String REGISTER = "/register/";
    public static final String GET_ALL = "/get/all";
    public static final String GET_ISO_2_CODE = "/get/iso2code/";

    @Test(dataProvider = "countryValidation", dataProviderClass = DataProviderStorage.class)
    @Description("Get each country (US, DE and GB) individually and validate the response")
    public void shouldRequestWithSeveralCountries(String name, String alfa2Code, String alfa3Code) {
        final String resultsApiJson = countryGetRequest("get/iso2code/", alfa2Code);
        final ApiJson apiJson = ApiJson.from(resultsApiJson);
        final CountryCode result = apiJson.getCountryCode();
        assertEquals(result.getName(), name);
        assertEquals(result.getAlpha2Code(), alfa2Code);
        assertEquals(result.getAlpha3Code(), alfa3Code);
    }

    @Test
    public void shouldRequestWithSeveral() {
        final String resultsApiJson = countriesGetRequest("get/all");
        final ApiJson apiJson = ApiJson.from(resultsApiJson);
        final List<CountryCode> result = apiJson.getAllCountriesCodes();
    }

    @Test
    @Description("Get all countries and validate that US, DE and GB were returned in the response")
    public void shouldRequestAllCountries() {
        RestAssured
                .given()
                .get(GET_ALL)
                .then()
                .statusCode(200)
                .log().all()
                .body("RestResponse.result.alpha2_code", hasItems("US", "GB", "DE"))
                .body("RestResponse.result.alpha3_code", hasItems("USA", "GBR", "DEU"));
    }

    @Test
    @Description("Get information for non-existent countries and validate the response")
    public void shouldRequestWithNonExistentCountry() {
        String nonExistentCountry = "RR";
        RestAssured.given()
                .get(GET_ISO_2_CODE + nonExistentCountry)
                .then()
                .statusCode(200)
                .log().all()
                .body("RestResponse.result", nullValue())
                .body("RestResponse.messages[0]", Matchers.equalTo("No matching country found for requested code [RR]."));
    }

    @Test
    @Description("validate new country addition using POST")
    public void shouldPostSpecific_TC() {
        String name = "Test Country";
        String alfa2Code = "TC";
        String alfa3Code = "TC";
        given()
                .contentType("application/json")
                .body(getJson(name, alfa2Code, alfa3Code))
                .when()
                .post(REGISTER)
                .then()
                .assertThat()
                .statusCode(200)
                //.log().all()
                .extract();

        //TODO add GET verification
    }

    private String getJson(String name, String alpha2Code, String alpha3Code) {
        JSONObject jsonObj = new JSONObject()
                .put("name", name)
                .put("alpha2_code", alpha2Code)
                .put("alpha3_code", alpha3Code);
        return jsonObj.toString();
    }

}
