package base;


import com.codeborne.selenide.Selenide;
import helper.ScreenshotOnFailure;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotOnFailure.class)
public class BaseUIClass {

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        RestAssured.baseURI = "http://services.groupkt.com/country/";
        Selenide.open("http://automationpractice.com/index.php");
    }

}
