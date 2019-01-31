package api.countries;

import api.country.CountryCodeNew;

import java.util.List;


public class CountriesRestResponse {

    private List<String> messages;
    private List<CountryCodeNew> result;

    public List<String> getMessages() {
        return messages;
    }

    public List<CountryCodeNew> getResult() {
        return result;
    }
}
