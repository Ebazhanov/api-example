package api.country;

public class CountryCodeNew {

    private String name;
    private String alpha2_code;
    private String alpha3_code;

    public CountryCodeNew(String name, String alpha2_code, String alpha3_code) {
        this.name = name;
        this.alpha2_code = alpha2_code;
        this.alpha3_code = alpha3_code;
    }


    public String getName() {
        return name;
    }

    public String getAlpha2_code() {
        return alpha2_code;
    }

    public String getAlpha3_code() {
        return alpha3_code;
    }


}
