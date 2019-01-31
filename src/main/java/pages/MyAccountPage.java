package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static pages.CreateAccountPageLocators.ACCOUNT_NAME;

public class MyAccountPage {

    protected static final String HEADER_ACCOUNT = "h1";
    protected static final By INFO_ACCOUNT_TEXT = By.className("info-account");
    protected static final By LOGOUT = By.className("logout");
    protected static final String PART_OF_URL_MY_ACCOUNT = "controller=my-account";

    public MyAccountPage verifyFirstLastName(String firstLastName) {
        $(ACCOUNT_NAME).shouldHave(Condition.text(firstLastName));
        return this;
    }

    public MyAccountPage verificationForSuccessLogin() {
        $(HEADER_ACCOUNT).waitUntil(Condition.visible, 1000).shouldHave(Condition.text("MY ACCOUNT"));
        $(INFO_ACCOUNT_TEXT).shouldHave(Condition.text("Welcome to your account."));
        $(LOGOUT).shouldBe(Condition.visible);
        url().contains(PART_OF_URL_MY_ACCOUNT);
        return this;
    }
}