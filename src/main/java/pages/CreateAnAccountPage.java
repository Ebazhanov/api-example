package pages;

import com.google.gson.Gson;
import helper.ResourceLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.entity.PersonProfile;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CreateAnAccountPage {

    private static PersonProfile getJsonData(String jsonFileName) {
        String pathToResourcesFolder = ResourceLoader.getResourceAsString(format("users/%s.json", jsonFileName));
        return new Gson().fromJson(pathToResourcesFolder, PersonProfile.class);
    }

    @Step
    public CreateAnAccountPage fillInPersonalData(String registrationData) {
        PersonProfile json = getJsonData(registrationData);
        $(By.id("id_gender2")).click();
        $(By.id("customer_firstname")).setValue(json.getFirstName());
        $(By.id("customer_lastname")).setValue(json.getLastName());
        $(By.id("passwd")).setValue(json.getPassword());
        $(By.id("days")).selectOptionByValue(json.getBirthday().getDay());
        $(By.id("months")).selectOptionByValue(json.getBirthday().getMonth());
        $(By.id("years")).selectOptionByValue(json.getBirthday().getYear());
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
        return this;
    }

}
