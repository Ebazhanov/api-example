package helpers;


import base.BaseUIClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class PropertiesReader {

    protected final static Properties prop = new Properties();

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    public static void readPropertiesFileWithTestData() throws IOException {
        InputStream inputStream = BaseUIClass.class.getClassLoader().getResourceAsStream("testdata.properties");
        try {
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            try {
                prop.load(reader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } finally {
            inputStream.close();
        }
    }
}
