package ui_inno_itog_project.page_object.ext;

import static ui_inno_itog_project.page_object.ext.WebDriverShutter.WD_KEY;

import ui_inno_itog_project.page_object.pom.pages.MainPage;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPageResolver implements ParameterResolver {


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(MainPage.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        WebDriver driver =
                extensionContext
                        .getStore(WebDriverShutter.namespace)
                        .getOrComputeIfAbsent(WD_KEY, (s) -> new ChromeDriver(), WebDriver.class);


        return new MainPage(driver);
    }


}
