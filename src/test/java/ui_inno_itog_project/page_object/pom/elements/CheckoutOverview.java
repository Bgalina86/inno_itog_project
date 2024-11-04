package ui_inno_itog_project.page_object.pom.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverview {

    private final By titlePageLocation = By.xpath(
        "//div[@class='header_secondary_container']");// название страницы
    private final By nameCart1Location = By.xpath(
        "//div[@class='cart_item_label']//a//div[text()='Sauce Labs Backpack']");
    private final By nameCart2Location = By.xpath(
        "//div[@class='cart_item_label']//a//div[text()='Sauce Labs Bolt T-Shirt']");
    private final By nameCart3Location = By.xpath(
        "//div[@class='cart_item_label']//a//div[text()='Sauce Labs Onesie']");
    private final By priceCard1Location = By.xpath(
        "//div[@class='cart_item'][1]/div[@class='cart_item_label']/div/div");
    private final By priceCard2Location = By.xpath(
        "//div[@class='cart_item'][2]/div[@class='cart_item_label']/div/div");
    private final By priceCard3Location = By.xpath(
        "//div[@class='cart_item'][3]/div[@class='cart_item_label']/div/div");
    private final By buttonFinishLocation = By.xpath("//button[@id='checkout']");
    private final WebDriver driver;

    public CheckoutOverview(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Запрашиваем название страницы")
    public String getTitlePageLocation() {
        return driver.findElement(titlePageLocation).getText();
    }

    @Step("Получение наименование товара №1")
    public String getNameCart1Location() {
        return driver.findElement(nameCart1Location).getText();
    }

    @Step("Получение наименование товара №2")
    public String getNameCart2Location() {
        return driver.findElement(nameCart2Location).getText();
    }

    @Step("Получение наименование товара №3")
    public String getNameCart3Location() {
        return driver.findElement(nameCart3Location).getText();
    }

    @Step("Получение стоимости товара №1")
    public String getPriceCard1Location() {
        return driver.findElement(priceCard1Location).getText();
    }

    @Step("Получение стоимости товара №2")
    public String getPriceCard2Location() {
        return driver.findElement(priceCard2Location).getText();
    }

    @Step("Получение стоимости товара №3")
    public String getPriceCard3Location() {
        return driver.findElement(priceCard3Location).getText();
    }

    @Step("Нажатия кнопки оформления заказа")
    public void buttonFinishLocation() {
        driver.findElement(buttonFinishLocation).click();
    }

}
