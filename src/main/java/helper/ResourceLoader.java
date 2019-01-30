package helper;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.entity.PersonProfile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.lang.String.format;

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

    public static String getResourceAsString(String resourcePath) {
        String resourceString = "";

        try {
            resourceString = Resources.toString(Resources.getResource(resourcePath), Charsets.UTF_8);
        } catch (IOException e) {
            log.error("Failed to read resource as string: " + resourcePath, e);
        }
        return resourceString;
    }


}
