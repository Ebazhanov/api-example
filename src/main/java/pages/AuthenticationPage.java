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

}
