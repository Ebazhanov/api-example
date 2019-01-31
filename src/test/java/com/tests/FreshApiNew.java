package com.tests;

import api.countries.CountriesIsoCode;
import api.country.CountryCodeNew;
import api.country.CountryIsoCode;
import api.restapi.CountryRequest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FreshApiNew {

    @Test
    public void getAllCountriesDeUsGbTest() {
        CountriesIsoCode countries = new CountryRequest()
                .getAllCountries();

        assertThat(countries)
                .extracting("RestResponse")
                .flatExtracting("result")
                .extracting("alpha2_code")
                .contains("DE", "US", "GB");
    }

    private Object[] parametersForGetCountryCodeTest() {
        return new Object[]{
                new Object[]{new CountryCodeNew("Germany", "DE", "DEU")},
                new Object[]{new CountryCodeNew("United Kingdom of Great Britain and Northern Ireland", "GB", "GBR")},
                new Object[]{new CountryCodeNew("United States of America", "US", "USA")}
        };
    }

    @Test
    public void getCountryCodeTest(CountryCodeNew countryIsoCodeExpected) {
        CountryIsoCode countryIsoCodeActual = new CountryRequest().getCountry(countryIsoCodeExpected.getAlpha2_code());
        assertThat(countryIsoCodeActual).extracting("RestResponse").extracting("result").isNotEmpty();
        assertThat(countryIsoCodeActual.getRestResponse().getResult()).isEqualToComparingFieldByField(countryIsoCodeExpected);
    }

    @Test
    public void getCountryNegativeTest() {

        CountryIsoCode countryIsoCodeActual = new CountryRequest().getCountry("Test");
        assertThat(countryIsoCodeActual)
                .extracting("RestResponse")
                .flatExtracting("messages")
                .containsOnlyOnce("No matching country found for requested code [Test].");
    }

}
