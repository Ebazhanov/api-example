package com.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static junit.framework.TestCase.assertTrue;
import static org.testng.Assert.assertEquals;

public class WebTest {

    private String existingUserEmail = "hf_challenge_123456@hf123456.com";
    private String existingUserPassword = "12345678";

    @BeforeMethod
    public void setUp() {
/*        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10, 50);*/

        Selenide.open("http://automationpractice.com/index.php");
    }

    @Test
    public void signInTest() {

        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        String name = "Firstname";
        String surname = "Lastname";


        $(".login").click();

        $(By.id("email_create")).setValue(email);

        $(By.id("SubmitCreate")).click();
        $(By.id("id_gender2")).click();


        $(By.id("customer_firstname")).setValue(name);
        $(By.id("customer_lastname")).setValue(surname);
        $(By.id("passwd")).setValue("Qwerty");
        $(By.id("days")).selectOptionByValue("1");
        $(By.id("months")).selectOptionByValue("1");
        $(By.id("years")).selectOptionByValue("2000");

        $(By.id("company")).sendKeys("Company");
        $(By.id("address1")).sendKeys("Qwerty, 123");
        $(By.id("address2")).sendKeys("zxcvb");
        $(By.id("city")).sendKeys("Qwerty");
        $(By.id("id_state")).selectOptionContainingText("Colorado");
        $(By.id("postcode")).sendKeys("12345");
        $(By.id("other")).sendKeys("Qwerty");
        $(By.id("phone")).sendKeys("12345123123");
        $(By.id("phone_mobile")).sendKeys("12345123123");
        $(By.id("alias")).sendKeys("hf");
        $(By.id("submitAccount")).click();


        $("h1").waitUntil(Condition.visible, 1000).shouldHave(Condition.text("MY ACCOUNT"));
        assertEquals($(By.className("account")).getText(), name + " " + surname);
        assertTrue($(By.className("info-account")).getText().contains("Welcome to your account."));
        assertTrue($(By.className("logout")).isDisplayed());
        assertTrue(url().contains("controller=my-account"));
    }

    @Test
    public void logInTest() {
        String fullName = "Joe Black";
        $(".login").click();
        $(By.id("email")).setValue(existingUserEmail);
        $(By.id("passwd")).setValue(existingUserPassword);
        $(By.id("SubmitLogin")).click();

        $("h1").waitUntil(Condition.visible, 1000).shouldHave(Condition.text("MY ACCOUNT"));

        $(".account").shouldHave(Condition.text(fullName));
        assertTrue($(By.className("info-account")).getText().contains("Welcome to your account."));
        assertTrue($(By.className("logout")).isDisplayed());
        assertTrue(url().contains("controller=my-account"));
    }

/*    @Test
    public void checkoutTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
        driver.findElement(By.id("SubmitLogin")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
        driver.findElement(By.name("processCarrier")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button"))).click();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        assertEquals("ORDER CONFIRMATION", heading.getText());
        assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
        assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
    }*/
}
