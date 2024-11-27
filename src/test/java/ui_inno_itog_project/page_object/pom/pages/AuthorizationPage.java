package ui_inno_itog_project.page_object.pom.pages;

import ui_inno_itog_project.BaseTest;

import ui_inno_itog_project.page_object.pom.elements.Authorization;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage extends BaseTest {

    private final WebDriver driver;
    private Authorization authorization;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        this.authorization = new Authorization(driver);
    }

    @Step("Проверка наименования сайта")
    public String getTextHeader() {
        return authorization.getTextPage();
    }

    @Step("Ввод логина в форму авторизации")
    public void getLoginStandart() {
        authorization.inputLogin(properties.getProperty("USERNAME_STANDARD"));
    }

    @Step("Ввод логина. Проверка ошибки входа")
    public void getLoginError() {
        authorization.inputLogin(properties.getProperty("USERNAME_LOCKED"));
    }

    @Step("Ввод пароля")
    public void getPassword() {
        authorization.inputPassword(properties.getProperty("PASSWORD"));
    }

    @Step("Ввод логина. Проверка пользователя USERNAME_PERFORMANCE")
    public void getLoginUsernamePerformance() {
        authorization.inputLogin(properties.getProperty("USERNAME_PERFORMANCE"));
    }

    @Step("Нажатия кнопки \"Login\"")
    public void clikButtonLogin() {
        authorization.clickButton();
    }

    @Step("Вывод ошибки при не успешной авторизации")
    public String getErrorMessage() {
        return authorization.getErrorMessage();
    }
}