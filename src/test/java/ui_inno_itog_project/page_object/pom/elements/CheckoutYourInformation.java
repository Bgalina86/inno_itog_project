package ui_inno_itog_project.page_object.pom.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformation {

    private final By firstNameLocation = By.xpath(
        "//div[@class='form_group']//*[@id='first-name']");//Smirnova
    private final By lastNameLocation = By.xpath(
        "//div[@class='form_group']//*[@id='last-name']");//Galina
    private final By postalCodeLocation = By.xpath(
        "//div[@class='form_group']//*[@id='postal-code']");//124578
    private final By continueLocation = By.xpath("//input[@id='continue']");
    private final WebDriver driver;

    public CheckoutYourInformation(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполняем форму. Вводим Фамилию, Имя и Индекс")
    public void fillingOutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameLocation).sendKeys(firstName);
        driver.findElement(lastNameLocation).sendKeys(lastName);
        driver.findElement(postalCodeLocation).sendKeys(postalCode);
        driver.findElement(continueLocation).submit();
    }
}
