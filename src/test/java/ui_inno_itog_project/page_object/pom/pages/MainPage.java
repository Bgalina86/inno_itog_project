package ui_inno_itog_project.page_object.pom.pages;

import helper.ConfProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class MainPage {

    private final WebDriver driver;
    public final AuthorizationPage authorizationPage;
    public final CardsPage cardsPage;
    public final CheckoutYourInformationPage checkoutYourInformationPage;
    public final OrderCartPage orderCartPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    private static ConfProperties properties;

    public static void setUp() {
        properties = new ConfProperties();
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.authorizationPage = new AuthorizationPage(driver);
        this.cardsPage = new CardsPage(driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
        this.checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        this.orderCartPage = new OrderCartPage(driver);
    }

    @Step("Открываем сайт")
    public void open() {
        driver.get(properties.getProperty("URL"));
    }
}