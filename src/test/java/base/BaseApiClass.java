package base;


import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;


public class BaseApiClass {

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        RestAssured.baseURI = "http://services.groupkt.com/country/";
    }

}
