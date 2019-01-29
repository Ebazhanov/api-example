package com.tests;

import api.ApiJson;
import api.DataProviderStorage;
import api.schema.CountryCode;
import base.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static api.GetRequest.countryGetRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;
import static org.testng.Assert.assertEquals;

@Feature("API test examples")
public class Fresh extends BaseClass {

    @Test(dataProvider = "countryValidation", dataProviderClass = DataProviderStorage.class)
    @Description("Get each country (US, DE and GB) individually and validate the response")
    public void getRequestWithSeveralCountries(String name, String alfa2Code, String alfa3Code) {
        final String resultsApiJson = countryGetRequest("get/iso2code/", alfa2Code);
        final ApiJson apiJson = ApiJson.from(resultsApiJson);
        final CountryCode result = apiJson.getCountryCode();
        assertEquals(result.getName(), name);
        assertEquals(result.getAlpha2Code(), alfa2Code);
        assertEquals(result.getAlpha3Code(), alfa3Code);
    }

    @Test
    @Description("Get all countries and validate that US, DE and GB were returned in the response")
    public void getRequestAllCountries() {
        RestAssured
                .given()
                .get("http://services.groupkt.com/country/get/all")
                .then()
                .statusCode(200)
                .log().all()
                .body("RestResponse.result.alpha2_code", hasItems("US", "GB", "DE"))
                .body("RestResponse.result.alpha3_code", hasItems("USA", "GBR", "DEU"));
    }

    @Test
    @Description("Get information for non-existent countries and validate the response")
    public void getRequestWithNonExistentCountry() {
        String nonExistentCountry = "RR";
        RestAssured.given()
                .get("/get/iso2code/" + nonExistentCountry)
                .then()
                .statusCode(200)
                .log().all()
                .body("RestResponse.result", nullValue())
                .body("RestResponse.messages[0]", Matchers.equalTo("No matching country found for requested code [RR]."));
    }

    @Test
    @Description("validate new country addition using POST")
    public void postSpecific_TC() {
        String name = "Test Country";
        String alfa2Code = "TC";
        String alfa3Code = "TC";
        given().contentType("application/json")
                .body(getJson(name, alfa2Code, alfa3Code))
                .when()
                .post("/register/")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .body("message", Matchers.contains("ullnllnullnullvar"));
    }

    private String getJson(String name, String alpha2Code, String alpha3Code) {
        JSONObject jsonObj = new JSONObject()
                .put("name", name)
                .put("alpha2_code", alpha2Code)
                .put("alpha3_code", alpha3Code);
        return jsonObj.toString();
    }

}
