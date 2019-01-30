package helper;

import com.codeborne.selenide.Configuration;

public class ConfigurationManager {

    public static void setup() {
        setupSystemProperty("webdriver.chrome.driver");
        setupSystemProperty("webdriver.gecko.driver");
        setupSystemProperty("webdriver.ie.driver");

        Configuration.baseUrl = getProperty("selenide.baseUrl");
        Configuration.browser = getProperty("selenide.browser");
    }

    private static void setupSystemProperty(String propertyName) {
        System.setProperty(propertyName, getProperty(propertyName));

    }

    private static String getProperty(String propertyName) {
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue == null) {
            propertyValue = ResourceLoader.loadAppProperty(propertyName);
        }
        return propertyValue;
    }

}

