package api_inno_itog_project;

import static api_inno_itog_project.constClass.ConstStatusCode.*;
import static api_inno_itog_project.x_clients.helper.EmployeeRandomeService.generateEmployee;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import com.github.javafaker.Faker;
import api_inno_itog_project.x_clients.ext.DatabaseService;
import api_inno_itog_project.x_clients.helper.CompanyApiHelper;
import helper.ConfProperties;
import api_inno_itog_project.x_clients.helper.EmployeeApiHelper;
import api_inno_itog_project.x_clients.model.AuthResponse;
import api_inno_itog_project.x_clients.model.Employee;
import api_inno_itog_project.x_clients.model.PatchEmployeeRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.sql.SQLException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Контрактные тесты на модуль Employee")
@DisplayName("Контрактные тесты на модуль Employee")
@Owner("Smirnova G.")
public class EmployeeContractTest {

    public static ConfProperties properties;
    static DatabaseService databaseService;
    static EmployeeApiHelper employeeApiHelper;
    static CompanyApiHelper companyHelper;
    static int companyId;
    static int employeeId;
    static String headersProperties;
    private static String username;
    private static String password;

    @BeforeEach
    public void setUp() throws SQLException {
        properties = new ConfProperties();
        RestAssured.baseURI = properties.getProperty("baseURI");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        headersProperties = properties.getProperty("headers");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        databaseService = new DatabaseService();
        databaseService.connectToDb();
        companyId = databaseService.createNewCompany();
        employeeId = databaseService.createNewEmployee(companyId);
        companyHelper = new CompanyApiHelper();
        employeeApiHelper = new EmployeeApiHelper();
    }


    @AfterEach
    public void tearDown() throws SQLException {
        databaseService.deleteCompanyAndItsEmloyees(companyId);
        databaseService.closeConnection();
    }

    @Test
    @DisplayName("Получаем список работников по существующей компании")
    @Story("Список сотрудников")
    @Severity(SeverityLevel.BLOCKER)
    public void getListEmployeeByCompany() throws SQLException {
        int id = databaseService.getAnyCompanyID();
        given()
            .basePath("employee")
            .queryParam("company", id)
            .when()
            .get()
            .then()
            .statusCode(HTTP_CODE_OK)
            .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test
    @DisplayName("Ожидаем пустое тело по запросу списка работников по НЕ существующему id компании")
    @Story("Получение информации о менеджерах")
    @Severity(SeverityLevel.BLOCKER)
    public void getBodyEmployeeByCompany() throws SQLException {
        int id = databaseService.getLastCompanyID();
        given()
            .basePath("employee")
            .queryParam("company", id + 1)
            .when()
            .get()
            .then()
            .statusCode(HTTP_CODE_OK)
            .header("Content-Type", "application/json; charset=utf-8")
            .body(equalTo("[]"));
    }

    @Test
    @DisplayName("Ожидаем статус 200,при запросе сотрудника по его id")
    @Story("Список сотрудников")
    @Severity(SeverityLevel.BLOCKER)
    public void getEmployeeById() throws SQLException {
        int id = databaseService.getAnyEmployeeId();
        given()
            .basePath("employee")
            .when()
            .get("{Id}", id)
            .then()
            .statusCode(HTTP_CODE_OK)
            .header("Content-Type", "application/json; charset=utf-8")
            .and()
            .extract().response().equals(id);
    }

    @Test
    @DisplayName("Ожидаем статус 200 и Content-length=0, при получение сотрудника с несуществующем id")
    @Story("Список сотрудников")
    @Severity(SeverityLevel.BLOCKER)
    public void getEmployeerByInvalidId() throws SQLException {

        int id = databaseService.getLastEmployeeID();
        given()
            .basePath("employee")
            .when()
            .get("{Id}", id + 1)
            .then()
            .statusCode(HTTP_CODE_OK)
            .header("Content-length", Matchers.equalTo("0"))
            .extract().response().equals(employeeId);
    }

    @Test
    @DisplayName("Проверяем, что не  можем создать сотрудника без токена, status-401")
    @Story("Список сотрудников")
    @Severity(SeverityLevel.BLOCKER)
    public void iCannotAddNewEmployee() {
        Faker faker = new Faker();
        Employee createEmployeeRequest = generateEmployee(faker, companyId);
        given()
            .basePath("employee")
            .body(createEmployeeRequest)
            .contentType(ContentType.JSON)
            .when()
            .post()
            .then()
            .assertThat()
            .statusCode(HTTP_CODE_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Проверяем что можем создать сотрудника c токеном")
    @Story("Список сотрудников")
    @Severity(SeverityLevel.BLOCKER)
    public void iCanAddNewEmployee() {

        AuthResponse info = employeeApiHelper.auth(username, password);
        Faker faker = new Faker();
        Employee createEmployeeRequest = generateEmployee(faker, companyId);
        given()
            .basePath("employee")
            .body(createEmployeeRequest)
            .header("x-client-token", info.userToken())
            .contentType(ContentType.JSON)
            .when()
            .post()
            .then()
            .assertThat()
            .statusCode(HTTP_CODE_CREATE);
    }

    @Test
    @DisplayName("Проверяем, что можем изменить информацию о сотруднике")
    @Story("Редактирование информации о сотрудниках")
    @Severity(SeverityLevel.BLOCKER)
    public void iCanEditEmployee() {
        AuthResponse info = employeeApiHelper.auth(username, password);
        PatchEmployeeRequest patchEmployeeRequest = fakerEmployee();
        given()
            .basePath("employee")
            .body(patchEmployeeRequest)
            .header("x-client-token", info.userToken())
            .contentType(ContentType.JSON)
            .when()
            .patch("{id}", employeeId)
            .then()
            .assertThat()
            .statusCode(HTTP_CODE_OK);
    }

    @Test
    @DisplayName("Проверяем, что при отправке запросы на изменение несуществующего сотрудника получаем 500")
    @Story("Редактирование информации о сотрудниках")
    @Severity(SeverityLevel.BLOCKER)
    public void iCannotEditEmployee() {
        AuthResponse info = employeeApiHelper.auth(username, password);
        PatchEmployeeRequest patchEmployeeRequest = fakerEmployee();

        given()
            .basePath("employee")
            .body(patchEmployeeRequest)
            .header("x-client-token", info.userToken())
            .contentType(ContentType.JSON)
            .when()
            .patch("{id}", employeeId + 1000)
            .then()
            .assertThat()
            .statusCode(HTTP_CODE_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Получение списка сотрудников для компании")
    @Story("Список сотрудников")
    @Severity(SeverityLevel.BLOCKER)
    public void getEmployeeCompany() {
        employeeApiHelper.printGetEmployeeIsCompany(companyId);
    }

    public PatchEmployeeRequest fakerEmployee() {
        Faker faker = new Faker();
        return new PatchEmployeeRequest(faker.name().lastName(),
            faker.internet().emailAddress(),
            faker.internet().url(),
            faker.phoneNumber().phoneNumber(),
            faker.bool().bool());
    }
}
