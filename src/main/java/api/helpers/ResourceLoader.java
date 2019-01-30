package api.helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

import static java.lang.ClassLoader.getSystemClassLoader;

public class ResourceLoader {

    private static Logger log = LoggerFactory.getLogger(ResourceLoader.class);
    private static final String appPropertiesFileName = "application.properties";

    public static String loadAppProperty(String name) {
        return loadProperty(name, appPropertiesFileName);
    }

    private static String loadProperty(String name, String filename) {
        String propertyValue = "";
        Properties prop = new Properties();
        getSystemClassLoader();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream is = classLoader.getResourceAsStream(filename)) {
            prop.load(is);
        } catch (Exception e) {
            log.error("Failed to get property: " + name, e);
        }

        if (name != null) {
            propertyValue = prop.getProperty(name);
        }
        return propertyValue;
    }

}
