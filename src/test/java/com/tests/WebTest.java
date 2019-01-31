package com.tests;

import base.BaseUIClass;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static junit.framework.TestCase.assertTrue;
import static org.testng.Assert.assertEquals;

@Feature("Main functionality")
public class WebTest extends BaseUIClass {

    private String existingUserEmail = "hf_challenge_123456@hf123456.com";
    private String existingUserPassword = "12345678";

    @Test
    @Story("Check registration of a new customer ")
    public void signInTest() {
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        String firstName = "Firstname";
        String lastName = "Lastname";
        new HomePage()
                .signInButton()
                .enterEmailInAccountBlock(email)
                .creatAnAccountButton()
                .fillInPersonalData("PersonalData");
        $("h1").waitUntil(Condition.visible, 1000).shouldHave(Condition.text("MY ACCOUNT"));
        assertEquals($(By.className("account")).getText(), firstName + " " + lastName);
        assertTrue($(By.className("info-account")).getText().contains("Welcome to your account."));
        assertTrue($(By.className("logout")).isDisplayed());
        assertTrue(url().contains("controller=my-account"));
    }

    @Test
    @Story("Check login with existing Customer")
    public void logInTest() {
        String fullName = "Joe Black";
        new HomePage()
                .signInButton()
                .enterExistingEmail(existingUserEmail)
                .enterExistingPassword(existingUserPassword)
                .clickSignInButton();
        $("h1").waitUntil(Condition.visible, 1000).shouldHave(Condition.text("MY ACCOUNT"));
        $(".account").shouldHave(Condition.text(fullName));
        assertTrue($(By.className("info-account")).getText().contains("Welcome to your account."));
        assertTrue($(By.className("logout")).isDisplayed());
        assertTrue(url().contains("controller=my-account"));
    }

    @Test
    public void checkoutTest() {
        new HomePage()
                .signInButton()
                .enterExistingEmail(existingUserEmail)
                .enterExistingPassword(existingUserPassword)
                .clickSignInButton();
        $(By.linkText("Women")).click();
        $(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
        $(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
        $(By.name("Submit")).click();
        $(By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")).click();
        $(By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")).click();
        $(By.name("processAddress")).click();
        $(By.id("uniform-cgv")).click();
        $(By.name("processCarrier")).click();
        $(By.className("bankwire")).click();
        $(By.xpath("//*[@id='cart_navigation']/button")).click();
        $("h1").waitUntil(Condition.visible, 1000).shouldHave(Condition.text("ORDER CONFIRMATION"));
        $(By.xpath("//li[@class='step_done step_done_last four']")).shouldBe(Condition.visible);
        $(By.xpath("//li[@id='step_end' and @class='step_current last']")).shouldBe(Condition.visible);
        $(By.xpath("//*[@class='cheque-indent']/strong")).text().contains("Your order on My Store is complete.");
        assertTrue(url().contains("controller=order-confirmation"));
    }

}
