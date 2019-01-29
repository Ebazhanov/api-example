package base;


import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;


public class BaseClass {

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        RestAssured.baseURI = "http://services.groupkt.com/country/get/iso2code/DE";
    }

}