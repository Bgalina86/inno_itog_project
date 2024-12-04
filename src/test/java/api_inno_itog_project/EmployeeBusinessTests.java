package api_inno_itog_project;

import static api_inno_itog_project.x_clients.helper.EmployeeRandomeService.generateEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.javafaker.Faker;
import api_inno_itog_project.x_clients.ext.DatabaseService;
import api_inno_itog_project.x_clients.ext.DbProperties;
import api_inno_itog_project.x_clients.helper.CompanyApiHelper;
import helper.ConfProperties;
import api_inno_itog_project.x_clients.helper.EmployeeApiHelper;
import api_inno_itog_project.x_clients.model.CreateEmployeeResponse;
import api_inno_itog_project.x_clients.model.Employee;
import api_inno_itog_project.x_clients.model.PatchEmployeeRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import jdk.jfr.Description;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Бизнес тесты на модуль Employee")
@DisplayName("Бизнес тесты на модуль Employee")
@Owner("Smirnova G.")
public class EmployeeBusinessTests {

    private static DatabaseService databaseService;
    private static int companyId;
    private static int employeeId;
    private static CompanyApiHelper companyApiHelper;
    private static EmployeeApiHelper employeeApiHelper;
    private static ConfProperties properties;
    private static String username;
    private static String password;

    @BeforeEach
    public void setUp() throws SQLException, IOException {

        RestAssured.baseURI = DbProperties.getProperties("baseURI");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        databaseService = new DatabaseService();
        databaseService.connectToDb();
        companyId = databaseService.createNewCompany();
        employeeId = databaseService.createNewEmployee(companyId);
        databaseService.createNewEmployee(companyId);

        companyApiHelper = new CompanyApiHelper();
        employeeApiHelper = new EmployeeApiHelper();

        RestAssured.defaultParser = Parser.JSON;
        properties = new ConfProperties();
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }


    @AfterEach
    public void tearDown() throws SQLException {
        databaseService.deleteCompanyAndItsEmloyees(companyId);
        databaseService.closeConnection();
    }


    @Test
    @DisplayName("Проверяем, что могу создать нового пользователя")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanAddNewEmployee() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.firstName(), resultSet.getString(2));
        assertEquals(employee.lastName(), resultSet.getString(3));
        assertEquals(employee.middleName(), resultSet.getString(4));
        assertEquals(employee.avatar_url(), resultSet.getString(7));
        assertEquals(employee.phone(), resultSet.getString(8));
        assertEquals(employee.birthdate().toString(), resultSet.getDate(9).toString());
        assertEquals(employee.isActive(), resultSet.getBoolean(10));

    }


    @Test
    @DisplayName("Могу создать нового пользователя.Имя записалось корректно")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanAddNewEmployeeFirstName() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.firstName(), resultSet.getString(2));
    }

    @Test
    @DisplayName("Могу создать нового пользователя. Фамилия записалась корректно")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanAddNewEmployeeLastName() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.lastName(), resultSet.getString(3));
    }

    @Test
    @DisplayName("Могу создать нового пользователя. Отчество записалось корректно")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanAddNewEmployeeMiddleName() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.middleName(), resultSet.getString(4));
    }

    @Test
    @Disabled
    @Issue("jira jp-123")
    @DisplayName("Могу создать нового пользователя. Email записан корректно")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.CRITICAL)
    public void ICanAddNewEmployeeEmail() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.email(), resultSet.getString(6), "EMAIL не записался");
    }

    @Test
    @DisplayName("Могу создать нового пользователя. Телефон записан корректно")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.CRITICAL)
    public void ICanAddNewEmployeePhone() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.phone(), resultSet.getString(8));

    }

    @Test
    @DisplayName("Могу создать нового пользователя. День рождения записан корректно")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.CRITICAL)
    public void ICanAddNewEmployeeBirthdate() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.birthdate(), resultSet.getDate(9).toString());
    }

    @Test
    @DisplayName("Могу создать нового пользователя. IsActive записан корректно")
    @Story("Создание менеджера")
    @Severity(SeverityLevel.CRITICAL)
    public void ICanAddNewEmployeeIsActive() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);
        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);
        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.isActive(), resultSet.getBoolean(10));
    }

    @Test
    @DisplayName("Могу получить информацию о пользователе")
    @Story("Получение информации о пользователе")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanGetEmployeeInfo() {
        Employee employee = employeeApiHelper.getEmployeeInfo(employeeId);
        assertEquals(employee.id(), employeeId);
        assertNotNull(employee.lastName());
        assertNotNull(employee.firstName());
    }

    @Test
    @DisplayName("Могу получить список сотрудников по компании")
    @Story("Получение информации о пользователе")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanGetEmployeeListByCompany() {
        List<Employee> employeeList = employeeApiHelper.getListOfEmployee(companyId);
        assertTrue(employeeList.size() > 0);
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
