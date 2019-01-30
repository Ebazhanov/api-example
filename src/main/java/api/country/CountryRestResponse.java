package api.country;

import api.schema.CountryCode;

import java.util.List;

public class CountryRestResponse {
    List<String> messages;
    CountryCode result;

    public List<String> getMessages() {
        return messages;
    }

    public CountryCode getResult() {
        return result;
    }
}
