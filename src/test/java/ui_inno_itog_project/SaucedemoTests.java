package ui_inno_itog_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import ui_inno_itog_project.page_object.ext.MainPageResolver;
import ui_inno_itog_project.page_object.ext.WebDriverShutter;
import ui_inno_itog_project.page_object.pom.pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName("Тесты по сайту https://www.saucedemo.com/")
@Epic("Тесты по сайту https://www.saucedemo.com/")
@Owner("Smirnova G.")
@ExtendWith(MainPageResolver.class)
@ExtendWith(WebDriverShutter.class)
public class SaucedemoTests extends BaseTest {

    @Test
    @DisplayName("Проверка авторизации пользователем,который зарегистрирован")
    @Tag("positive")
    @Story("Блок авторизация")
    @Severity(SeverityLevel.BLOCKER)
    public void authorizationStandardUser(MainPage mainPage) {
        mainPage.open();
        assertEquals("Swag Labs", mainPage.authorizationPage.getTextHeader());
        mainPage.authorizationPage.getLoginStandart();
        mainPage.authorizationPage.getPassword();
        mainPage.authorizationPage.clikButtonLogin();
        assertEquals("Products", mainPage.cardsPage.getHeader());
    }


    @Test
    @DisplayName("Проверка авторизации пользователем,который забыл пароль")
    @Tag("negative ")
    @Story("Блок авторизация")
    @Severity(SeverityLevel.BLOCKER)
    public void authorizationError(MainPage mainPage) {
        mainPage.open();
        assertEquals("Swag Labs", mainPage.authorizationPage.getTextHeader());
        mainPage.authorizationPage.getLoginError();
        mainPage.authorizationPage.getPassword();
        mainPage.authorizationPage.clikButtonLogin();
        assertEquals("Epic sadface: Sorry, this user has been locked out.",
            mainPage.authorizationPage.getErrorMessage());
    }


    @Test
    @DisplayName("Пользовательский тест-кейс по покупке товара")
    @Tag("positive")
    @Story("Проверка авторизации пользователем,который зарегистрирован")
    @Severity(SeverityLevel.BLOCKER)
    public void e2eLoginStandart(MainPage mainPage) {
        mainPage.open();
        assertEquals("Swag Labs", mainPage.authorizationPage.getTextHeader());
        mainPage.authorizationPage.getLoginStandart();
        e2e(mainPage);
    }


    @Test
    @DisplayName("Пользовательский тест-кейс по покупке товара с ожиданиями")
    @Tag("positive")
    @Story("Проверка авторизации пользователем,который зарегистрирован")
    @Severity(SeverityLevel.BLOCKER)
    public void e2eUsernamePerformance(MainPage mainPage) {
        mainPage.open();
        assertEquals("Swag Labs", mainPage.authorizationPage.getTextHeader());
        mainPage.authorizationPage.getLoginUsernamePerformance();
        e2e(mainPage);
    }
    private void e2e(MainPage mainPage){
        mainPage.authorizationPage.getPassword();
        mainPage.authorizationPage.clikButtonLogin();
        assertEquals("Products", mainPage.cardsPage.getHeader());
        assertEquals("Sauce Labs Backpack", mainPage.cardsPage.getNameCard1(),
            "Проверка наличия товара с наименованием Sauce Labs Backpack");
        mainPage.cardsPage.clickButtonCard1Button();
        assertEquals("Sauce Labs Bolt T-Shirt", mainPage.cardsPage.getNameCard2(),
            "Проверка наличия товара с наименованием Sauce Labs Bolt T-Shirt");
        mainPage.cardsPage.clickButtonCard2Button();
        assertEquals("Sauce Labs Onesie", mainPage.cardsPage.getNameCard3(),
            "Проверка наличия товара с наименованием Sauce Labs Onesie");
        mainPage.cardsPage.clickButtonCard3Button();
        mainPage.cardsPage.clickShoppingCartButton();
        assertEquals("Your Cart", mainPage.checkoutOverviewPage.getHeader());
        assertEquals("Sauce Labs Backpack", mainPage.checkoutOverviewPage.getnameCart1Location(),
            "Проверка наличия товара с наименованием Sauce Labs Backpack");
        assertEquals("Sauce Labs Bolt T-Shirt",
            mainPage.checkoutOverviewPage.getnameCart2Location(),
            "Проверка наличия товара с наименованием Sauce Labs Bolt T-Shirt");
        assertEquals("Sauce Labs Onesie", mainPage.checkoutOverviewPage.getnameCart3Location(),
            "Проверка наличия товара с наименованием Sauce Labs Onesie");
        assertEquals("$29.99", mainPage.checkoutOverviewPage.getpriceCard1Location(),
            "Проверка стоимости товара Sauce Labs Backpack $29.99");
        assertEquals("$15.99", mainPage.checkoutOverviewPage.getpriceCard2Location(),
            "Проверка стоимости товара Sauce Labs Bolt T-Shirt $15.99");
        assertEquals("$7.99", mainPage.checkoutOverviewPage.getpriceCard3Location(),
            "Проверка стоимости товара Sauce Labs Onesie $7.99");
        mainPage.checkoutOverviewPage.clickButtonFinishLocation();
        mainPage.checkoutYourInformationPage.setFillingOutForm();
        assertEquals("Sauce Labs Backpack", mainPage.orderCartPage.getnameCart1Location(),
            "Проверка наличия товара с наименованием Sauce Labs Backpack");
        assertEquals("Sauce Labs Bolt T-Shirt", mainPage.orderCartPage.getnameCart2Location(),
            "Проверка наличия товара с наименованием Sauce Labs Bolt T-Shirt");
        assertEquals("Sauce Labs Onesie", mainPage.orderCartPage.getnameCart3Location(),
            "Проверка наличия товара с наименованием Sauce Labs Onesie");
        assertEquals("Total: $58.29", mainPage.orderCartPage.getTotalPriceLocation(),
            "Проверка общей стоимости товара. Total: $58.29");
        mainPage.orderCartPage.clickButtonCheckoutLocation();
        assertEquals("Thank you for your order!", mainPage.orderCartPage.getTextFinishPage());
    }
}
