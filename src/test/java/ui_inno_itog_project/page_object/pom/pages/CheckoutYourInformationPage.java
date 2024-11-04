package ui_inno_itog_project.page_object.pom.pages;

import ui_inno_itog_project.helper.ConfProperties;
import ui_inno_itog_project.page_object.pom.elements.CheckoutYourInformation;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformationPage {

    private final WebDriver driver;
    private static ConfProperties properties;

    public static void setUp() {
        properties = new ConfProperties();
    }

    private final CheckoutYourInformation checkoutYourInformation;

    public CheckoutYourInformationPage(WebDriver driver) {
        this.driver = driver;
        this.checkoutYourInformation = new CheckoutYourInformation(driver);
    }

    @Step("Заполняем форму данными")
    public void setFillingOutForm() {
        checkoutYourInformation.fillingOutForm(properties.getProperty("FIRST_NAME"),
            properties.getProperty("LAST_NAME"), properties.getProperty("POST_CODE"));
    }
}
