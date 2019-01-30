package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthenticationPage {

    @Step
    public AuthenticationPage enterEmailInAccountBlock(String email){
        $(By.id("email_create")).setValue(email);
        return this;
    }

    @Step
    public CreateAnAccountPage creatAnAccountButton(){
        $(By.id("SubmitCreate")).click();
        return new CreateAnAccountPage();
    }

    @Step
    public AuthenticationPage enterExistingEmail(String existingUserEmail) {
        $(By.id("email")).setValue(existingUserEmail);
        return this;
    }

    @Step
    public AuthenticationPage enterExistingPassword(String existingUserPassword) {
        $(By.id("passwd")).setValue(existingUserPassword);
        return this;
    }

    @Step
    public AuthenticationPage clickSignInButton() {
        $(By.id("SubmitLogin")).click();
        return this;
    }
}
