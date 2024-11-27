package api_inno_itog_project.x_clients.helper;

import static io.restassured.RestAssured.given;
import api_inno_itog_project.x_clients.model.AuthRequest;
import api_inno_itog_project.x_clients.model.AuthResponse;
import api_inno_itog_project.x_clients.model.CreateEmployeeResponse;
import api_inno_itog_project.x_clients.model.Employee;
import helper.ConfProperties;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.util.List;


public class EmployeeApiHelper {

    public static ConfProperties properties = new ConfProperties();
    static String username = properties.getProperty("username");;
    static String password = properties.getProperty("password");;
    @Step("Авторизация")
    public AuthResponse auth(String username, String password) {
        AuthRequest authRequest = new AuthRequest(username, password);

        return given()
            .basePath("/auth/login")
            .body(authRequest)
            .contentType(ContentType.JSON)
            .when()
            .post()
            .as(AuthResponse.class);
    }
    @Step("Печатаем информации о менеджере компании")
    public Object printGetEmployeeIsCompany(int id) {

        return given()
            .basePath("employee")
            .queryParam("company", id)
            .when()
            .get()
            .body().prettyPrint();
    }
    @Step("Редактирование менеджера")
    public CreateEmployeeResponse createEmployee(Employee employee) {
        AuthResponse authResponse = auth(username,
            password);
        return given()
            .basePath("employee")
            .body(employee)
            .header("x-client-token", authResponse.userToken())
            .contentType(ContentType.JSON)
            .when()
            .post().body().as(CreateEmployeeResponse.class);
    }
    @Step("Получение информации о менеджере")
    public Employee getEmployeeInfo(int employeeId) {
        return given()
            .basePath("employee")
            .when()
            .get("{Id}", employeeId).body().<Employee>as(Employee.class);
    }
    @Step("Получение списка менеджеров компании")
    public List<Employee> getListOfEmployee(int companyId) {
        return given()
            .basePath("employee")
            .queryParam("company", companyId)
            .when()
            .get().body().as(new TypeRef<>() {
            });
    }
}
