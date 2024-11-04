package ui_inno_itog_project.page_object.pom.pages;

import ui_inno_itog_project.page_object.pom.elements.CheckoutOverview;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final CheckoutOverview checkoutOverview;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.checkoutOverview = new CheckoutOverview(driver);
    }

    @Step("Наименование страницы")
    public String getHeader() {
        return checkoutOverview.getTitlePageLocation();//Your Cart
    }

    @Step("Получение наименования товара Sauce Labs Backpack")
    public String getnameCart1Location() {
        return checkoutOverview.getNameCart1Location();
    }

    @Step("Получение наименования товара  Sauce Labs Bolt T-Shirt")
    public String getnameCart2Location() {
        return checkoutOverview.getNameCart2Location();
    }

    @Step("Получение наименования товара Sauce Labs Onesie")
    public String getnameCart3Location() {
        return checkoutOverview.getNameCart3Location();
    }

    @Step("Получение стоимости товара Sauce Labs Backpack")
    public String getpriceCard1Location() {
        return checkoutOverview.getPriceCard1Location();
    }

    @Step("Получение стоимости товара  Sauce Labs Bolt T-Shirt")
    public String getpriceCard2Location() {
        return checkoutOverview.getPriceCard2Location();
    }

    @Step("Получение стоимости товара Sauce Labs Onesie")
    public String getpriceCard3Location() {
        return checkoutOverview.getPriceCard3Location();
    }

    @Step("Нажатие кнопки Chekout")
    public void buttonFinishLocation() {
        checkoutOverview.buttonFinishLocation();
    }

}
