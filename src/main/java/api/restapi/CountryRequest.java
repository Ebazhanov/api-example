package api.restapi;


import api.countries.CountriesIsoCode;
import api.country.CountryIsoCode;
import api.helpers.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;


public class CountryRequest {
    private static final String PATH_ALL_COUNTRIES = "/country/get/all";
    private static final String PATH_GET_COUNTRY = "/country/get/iso2code/{alpha2_code}";
    private static final String PATH_POST_COUNTRY = "/country/get/iso2code";

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigurationManager.getHostUrl())
            .setContentType(ContentType.JSON)
            .log(LogDetail.URI)
            .build();

    private final ResponseSpecification getCountrySpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    public CountriesIsoCode getAllCountries() {
        return given().spec(requestSpec).when().get(PATH_ALL_COUNTRIES).then()
                .spec(getCountrySpec)
                .log().ifError()
                .extract().body().as(CountriesIsoCode.class);
    }

    public CountryIsoCode getCountry(String alpha2_code) {
        return given().spec(requestSpec).pathParam("alpha2_code", alpha2_code).when().get(PATH_GET_COUNTRY).then()
                .spec(getCountrySpec)
                .log().ifError()
                .extract().body().as(CountryIsoCode.class);
    }

    public CountryIsoCode storeCountry(String countryIsoJson) {
        return given().spec(requestSpec).body(countryIsoJson).when().post(PATH_POST_COUNTRY).then()
                .statusCode(201)
                .log().ifError()
                .extract().body().as(CountryIsoCode.class);
    }

}
