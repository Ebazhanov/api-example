package com.tests;

import api.ApiJson;
import api.ApiRequest;
import api.schema.Result;
import base.BaseClass;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Feature("API test examples")
public class Fresh extends BaseClass {

    @Test
    public void getRequest() {
        final String resultsApiJson = ApiRequest.makeGetRequest();
        final ApiJson apiJson = ApiJson.from(resultsApiJson);
        final Result result = apiJson.getResult();
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


    @Test
    public void postRequest1() {
        String end_point = "/register";
        RestAssured.baseURI = "http://services.groupkt.com/country" + end_point;
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Test Country");
        requestParams.put("alpha2_code", "TC");
        requestParams.put("alpha3_code", "TCY");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post(end_point);
        System.out.println(response);
    }

}
