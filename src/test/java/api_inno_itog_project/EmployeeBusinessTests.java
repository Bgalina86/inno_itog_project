package api_inno_itog_project;

import static api_inno_itog_project.x_clients.helper.EmployeeRandomeService.generateEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import api_inno_itog_project.x_clients.ext.DatabaseService;
import api_inno_itog_project.x_clients.ext.DbProperties;
import api_inno_itog_project.x_clients.helper.CompanyApiHelper;
import api_inno_itog_project.x_clients.helper.ConfProperties;
import api_inno_itog_project.x_clients.helper.EmployeeApiHelper;
import api_inno_itog_project.x_clients.model.CreateEmployeeResponse;
import api_inno_itog_project.x_clients.model.Employee;
import api_inno_itog_project.x_clients.model.PatchEmployeeRequest;
import io.qameta.allure.Epic;
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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("")
public class EmploeeBusinessTests {

    private static DatabaseService databaseService;
    private static int companyId;
    private static int employeeId;
    private static CompanyApiHelper companyApiHelper;
    private static EmployeeApiHelper employeeApiHelper;
    private static ConfProperties properties;
    private static String username;
    private static String password;

    @BeforeAll
    public static void setUp() throws SQLException, IOException {

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


    @AfterAll
    public static void tearDown() throws SQLException {
        databaseService.deleteCompanyAndItsEmloyees(companyId);
        databaseService.closeConnection();
    }


    @Test
    @Description("���������, ��� ���� ������� ������ ������������")
    @DisplayName("")
    @Tag("")
    @Story("")
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
       // assertEquals(employee.email(), resultSet.getString(6));
        assertEquals(employee.avatar_url(), resultSet.getString(7));
        assertEquals(employee.phone(), resultSet.getString(8));
        assertEquals(employee.birthdate().toString(), resultSet.getDate(9).toString());
        assertEquals(employee.isActive(), resultSet.getBoolean(10));

    }

    @Test
    @DisplayName("")
    @Tag("")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanEditEmployee() {

        PatchEmployeeRequest patchEmployeeRequest = fakerEmployee();
        Employee employee = employeeApiHelper.editEmployee(employeeId, patchEmployeeRequest);
        assertEquals(employee.lastName(),patchEmployeeRequest.lastName());
        assertEquals(employee.email(),patchEmployeeRequest.email());
        assertEquals(employee.avatar_url(),patchEmployeeRequest.url());
        assertEquals(employee.phone(),patchEmployeeRequest.phone());
        assertEquals(employee.isActive(),patchEmployeeRequest.isActive());
    }

    @Test
    @Description("���� ������� ������ ������������.��� ���������� ���������")
    @DisplayName("")
    @Tag("")
    @Story("")
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
    @Description("���� ������� ������ ������������. ������� ���������� ���������")
    @DisplayName("")
    @Tag("")
    @Story("")
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
    @Description("���� ������� ������ ������������. �������� ���������� ���������")
    @DisplayName("")
    @Tag("")
    @Story("")
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
    @Description("���� ������� ������ ������������. Email ������� ���������")
    @DisplayName("")
    @Tag("")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanAddNewEmployeeEmail() throws SQLException {
        Faker faker = new Faker();
        Employee employee = generateEmployee(faker, companyId);

        CreateEmployeeResponse response = employeeApiHelper.createEmployee(employee);

        ResultSet resultSet = databaseService.getEmployeeInfo(response.id());
        resultSet.next();
        assertEquals(response.id(), resultSet.getInt(1));
        assertEquals(employee.email(), resultSet.getString(6), "EMAIL �� ���������");
    }

    @Test
    @Description("���� ������� ������ ������������. ������� ������� ���������")
    @DisplayName("")
    @Tag("")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
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
    @Description("���� ������� ������ ������������. ���� �������� ������� ���������")
    @DisplayName("")
    @Tag("")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
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
    @Description("���� ������� ������ ������������. IsActive ������� ���������")
    @DisplayName("")
    @Tag("")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
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
    @Description("���� �������� ���������� � ������������")
    @DisplayName("")
    @Tag("")
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
    public void ICanGetEmployeeInfo() {
        Employee employee = employeeApiHelper.getEmployeeInfo(employeeId);
        assertEquals(employee.id(), employeeId);
        assertNotNull(employee.lastName());
        assertNotNull(employee.firstName());
    }

    @Test
    @Description("���� �������� ������ ����������� �� ��������")
    @DisplayName("")
    @Tag("")
    @Story("")
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
