package api_inno_itog_project.x_clients.helper;

import static io.restassured.RestAssured.given;

import api_inno_itog_project.x_clients.model.AuthRequest;
import api_inno_itog_project.x_clients.model.AuthResponse;
import api_inno_itog_project.x_clients.model.CreateCompanyRequest;
import api_inno_itog_project.x_clients.model.CreateCompanyResponse;
import helper.ConfProperties;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CompanyApiHelper {

    private static ConfProperties properties = new ConfProperties();
    private String username = properties.getProperty("username");
    private String password = properties.getProperty("password");


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

    @Step("Создание компании")
    public Object createCompany(String name, String descr) {
        AuthResponse info = auth(username, password);

        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest(name, descr);

        return given()
            .basePath("company")
            .body(createCompanyRequest)
            .header("x-client-token", info.userToken())
            .contentType(ContentType.JSON)
            .when()
            .post().body().as(CreateCompanyResponse.class);
    }

    @Step("Удаление компании")
    public Response deleteCompany(int id) {
        AuthResponse info = auth(username, password);

        return given()
            .basePath("company/delete")
            .header("x-client-token", info.userToken())
            .contentType(ContentType.JSON)
            .when()
            .get("{id}", id);

    }
}
