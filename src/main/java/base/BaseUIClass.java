package base;


import com.codeborne.selenide.Selenide;
import helpers.ScreenshotOnFailure;
import org.testng.annotations.*;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.BrowserSelection.selectBrowser;
import static helpers.ResourceLoader.loadPropertyName;

@Listeners(ScreenshotOnFailure.class)
public class BaseUIClass {

    @Parameters({"browser","deviceName"})
    @BeforeClass(alwaysRun = true)
    public void beforeTest(@Optional String browser, @Optional String deviceName) {
        selectBrowser(browser, deviceName);
        Selenide.open(loadPropertyName("UI_BASE_URL"));
    }

    @AfterClass(alwaysRun = true)
    void after() {
        clearBrowserCache();
        closeWebDriver();
    }

}
