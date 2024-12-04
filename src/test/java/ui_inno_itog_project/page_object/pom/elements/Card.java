package ui_inno_itog_project.page_object.pom.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Card {

    private final By headerLocation = By.xpath("//div[@class='header_secondary_container']/span");
    private final By card1Location = By.xpath(
        "//div[@class=\"inventory_item_name \"][text()='Sauce Labs Backpack']");
    private final By card2Location = By.xpath(
        "//div[@class=\"inventory_item_name \"][text()='Sauce Labs Bolt T-Shirt']");
    private final By card3Location = By.xpath(
        "//div[@class=\"inventory_item_name \"][text()='Sauce Labs Onesie']");
    private final By clickButtonCard1Location = By.xpath(
        "//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    private final By clickButtonCard2Location = By.xpath(
        "//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
    private final By clickButtonCard3Location = By.xpath(
        "//*[@id=\"add-to-cart-sauce-labs-onesie\"]");
    private final By shoppingCartLocation = By.xpath(
        "//span[@class=\"shopping_cart_badge\"][text()='3']");
    private final WebDriver driver;

    public Card(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Запрашиваем название страницы")
    public String getHeader() {
        return driver.findElement(headerLocation).getText();
    }

    @Step("Получение наименование товара №1")
    public String getCard1Name() {
        return driver.findElement(card1Location).getText();
    }

    @Step("Получение наименование товара №2")
    public String getCard2Name() {
        return driver.findElement(card2Location).getText();
    }

    @Step("Получение наименование товара №3")
    public String getCard3Name() {
        return driver.findElement(card3Location).getText();
    }

    @Step("Добавление в корзину товара №1")
    public void clickButtonCard1Button() {
        driver.findElement(clickButtonCard1Location).click();
    }

    @Step("Добавление в корзину товара №2")
    public void clickButtonCard2Button() {
        driver.findElement(clickButtonCard2Location).click();
    }

    @Step("Добавление в корзину товара №3")
    public void clickButtonCard3Button() {
        driver.findElement(clickButtonCard3Location).click();
    }

    @Step("Переход на страницу корзины по иконке Корзина")
    public void clickShoppingCartButton() {
        driver.findElement(shoppingCartLocation).click();
    }
}
