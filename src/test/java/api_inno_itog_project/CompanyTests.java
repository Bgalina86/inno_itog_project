package api_inno_itog_project;

import static api_inno_itog_project.constClass.ConstStatusCode.HTTP_CODE_CREATE;
import static api_inno_itog_project.constClass.ConstStatusCode.HTTP_CODE_OK;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import api_inno_itog_project.x_clients.helper.CompanyApiHelper;
import helper.ConfProperties;
import api_inno_itog_project.x_clients.model.AuthResponse;
import api_inno_itog_project.x_clients.model.CreateCompanyRequest;
import api_inno_itog_project.x_clients.model.CreateCompanyResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Тесты на модуль Company")
@DisplayName("Тесты на модуль Company")
@Owner("Smirnova G.")
public class CompanyTests {

    private static ConfProperties properties;
    private static String username;
    private static String password;
    CompanyApiHelper companyApiHelper;

    @BeforeEach
    public void setUpL() {

        companyApiHelper = new CompanyApiHelper();
    }

    @BeforeAll
    public static void setUp() {
        properties = new ConfProperties();
        RestAssured.baseURI = properties.getProperty("baseURI");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Могу отредактировать наименование компании и ее описание")
    @Story("Редактирование записи о компании")
    @Severity(SeverityLevel.BLOCKER)
    public void iCanCreateNewCompany() {
        AuthResponse info = companyApiHelper.auth(username, password);
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("TecnaSchool",
            "Онлайн-курсы");
        given()
            .basePath("company")
            .body(createCompanyRequest)
            .header("x-client-token", info.userToken())
            .contentType(ContentType.JSON)
            .when()
            .post()
            .then()
            .assertThat()
            .statusCode(HTTP_CODE_CREATE)
            .and()
            .body("id", is(greaterThan(0)));
    }

    @Test
    @DisplayName("Могу удалить компанию")
    @Story("Удаление компании")
    @Severity(SeverityLevel.BLOCKER)
    public void iCanDeleteCompany() {
        CreateCompanyResponse response = (CreateCompanyResponse) companyApiHelper.createCompany(
            "TecnaSchool",
            "Онлайн-курсы");
        Response r = companyApiHelper.deleteCompany(response.id());
        r.then().statusCode(HTTP_CODE_OK);
    }

    @Test
    @DisplayName("Могу получить информацию по компании")
    @Story("Информация  компании")
    @Severity(SeverityLevel.BLOCKER)
    public void getCompanyIdNewCompany() {
        AuthResponse info = companyApiHelper.auth(username, password);
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("TecnaSchool",
            "Онлайн-курсы");
        int companyId = RestAssured.given()
            .basePath("company")
            .body(createCompanyRequest)
            .header("x-client-token", info.userToken())
            .contentType(ContentType.JSON)
            .when()
            .post()
            .then().extract().response().jsonPath().getInt("id");
        System.out.println(companyId);
    }
}
