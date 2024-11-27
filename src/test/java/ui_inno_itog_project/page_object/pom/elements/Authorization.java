package ui_inno_itog_project.page_object.pom.elements;

import helper.ConfProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Authorization {

    private static ConfProperties properties;
    private final By logoLocation = By.xpath("//div[@class=\"login_logo\"]");//Swag Labs
    private final By usernameLocation = By.xpath("//input[@placeholder=\"Username\"]");
    private final By passwordLocation = By.xpath("//input[@placeholder=\"Password\"]");
    private final By clickButtonLocation = By.xpath("//input[@type=\"submit\"]");
    private final By errorMessageLocation = By.xpath(
        "//div[@class='error-message-container error']/h3");
    private final WebDriver driver;

    public static void setUp() {
        properties = new ConfProperties();
    }

    public Authorization(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextPage() {
        return driver.findElement(logoLocation).getText();
    }

    @Step("Ввод логина в поле формы авторизации")
    public void inputLogin(String usernameStandard) {
        driver.findElement(usernameLocation).sendKeys(usernameStandard);
    }

    @Step("Ввод пароля в поле формы авторизации")
    public void inputPassword(String password) {
        driver.findElement(passwordLocation).sendKeys(password);
    }

    @Step("Нажимаем кнопку")
    public void clickButton() {
        driver.findElement(clickButtonLocation).click();
    }

    @Step("Вывод сообщения об ошибки при вводе невалидных данных в форму авторизации")
    public String getErrorMessage() {
        return driver.findElement(errorMessageLocation).getText();
    }
}

