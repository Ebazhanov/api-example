package api.countries;

import com.hellofresh.entities.country.CountryCode;

import java.util.List;


public class CountriesRestResponse {

    private List<String> messages;
    private List<CountryCode> result;

    public List<String> getMessages() {
        return messages;
    }

    public List<CountryCode> getResult() {
        return result;
    }
}
