package base;


import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;


public class BaseClass {

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        RestAssured.baseURI = "http://services.groupkt.com/country/";
        Selenide.open("http://automationpractice.com/index.php");
    }

}
