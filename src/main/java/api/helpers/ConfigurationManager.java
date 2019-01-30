package api.helpers;


public class ConfigurationManager {

    private static final String hostUrl= getProperty("restassured.baseUrl");

    private static String getProperty(String propertyName) {
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue == null) {
            propertyValue = ResourceLoader.loadAppProperty(propertyName);
        }
        return propertyValue;
    }

    public static String getHostUrl() {
        return hostUrl;
    }


}

