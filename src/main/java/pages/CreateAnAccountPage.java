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
        $(By.id("company")).setValue(json.getCompany());
        $(By.id("address1")).setValue(json.getAddress1());
        $(By.id("address2")).setValue(json.getAddress2());
        $(By.id("city")).setValue(json.getCity());
        $(By.id("id_state")).selectOptionContainingText(json.getStateId());
        $(By.id("postcode")).setValue(json.getPostcode());
        $(By.id("other")).setValue(json.getOther());
        $(By.id("phone")).setValue(json.getPhone());
        $(By.id("phone_mobile")).setValue(json.getPhoneMobile());
        $(By.id("alias")).setValue(json.getAlias());
        $(By.id("submitAccount")).click();
        return this;
    }

}
