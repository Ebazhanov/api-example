package base;


import com.codeborne.selenide.Selenide;
import helpers.ScreenshotOnFailure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotOnFailure.class)
public class BaseUIClass {

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        Selenide.open("http://automationpractice.com/index.php");
    }

}
