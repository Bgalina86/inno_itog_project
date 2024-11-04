package ui_inno_itog_project.page_object.pom.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderCart {

    private final By nameCart1Location = By.xpath(
        "//div[@class='cart_item_label']//a//div[text()='Sauce Labs Backpack']");
    private final By nameCart2Location = By.xpath(
        "//div[@class='cart_item_label']//a//div[text()='Sauce Labs Bolt T-Shirt']");
    private final By nameCart3Location = By.xpath(
        "//div[@class='cart_item_label']//a//div[text()='Sauce Labs Onesie']");
    private final By totalPriceLocation = By.xpath("//div[@class='summary_total_label']");
    private final By buttonCheckoutLocation = By.xpath("//button[@id=\"finish\"]");
    private final By finishHeaderLocation = By.xpath("//h2");
    private final WebDriver driver;

    public OrderCart(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получение наименования товара №1")
    public String getNameCart1Location() {
        return driver.findElement(nameCart1Location).getText();
    }

    @Step("Получение наименования товара №1")
    public String getNameCart2Location() {
        return driver.findElement(nameCart2Location).getText();
    }

    @Step("Получение наименования товара №1")
    public String getNameCart3Location() {
        return driver.findElement(nameCart3Location).getText();
    }

    @Step("Получаем общую стоимость товаров")
    public String getTotalPriceLocation() {
        return driver.findElement(totalPriceLocation).getText();
    }

    @Step("Нажимаем кнопку")
    public void clickButtonCheckoutLocation() {
        driver.findElement(buttonCheckoutLocation).click();
    }

    @Step("Получаем текст страницы в завершении оформления заказа")
    public String getFinishText() {
        return driver.findElement(finishHeaderLocation).getText();
    }
}
