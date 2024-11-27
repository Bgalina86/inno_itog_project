package api_inno_itog_project.x_clients.ext;
import helper.ConfProperties;
import io.qameta.allure.Step;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {

    private static ConfProperties properties;
    private Connection connection;

    static final String SQL_GET_ANY_COMPANY_ID = "select c.id, count(c.id) from company c left join employee e on c.id = e.company_id group by c.id having count(c.id) > 0 limit 1;";
    static final String SQL_GET_LAST_COMPANY_ID = "SELECT c.id from company c order by c.id desc limit 1;";
    static final String SQL_GET_LAST_EMPLOYEE_ID = "select e.id from employee e order by e.id desc limit 1;";
    static final String SQL_ADD_NEW_COMPANY = "insert into company (name,description)values ('TecnaSchool', 'TecnaSchool decription');";
    static final String SQL_GET_SPECIAL_COMPANY_ID = "select id from company where description ='TecnaSchool decription' order by id desc limit 1;";
    static final String SQL_DELETE_EMPLOYEES_OF_SPECIAL_COMPANY = "delete from employee e where e.company_id in (select c.id from company c where c.description ='TecnaSchool decription');";
    static final String SQL_DELETE_SPECIAL_COMPANY = "delete from company c where description ='TecnaSchool decription'";
    static final String SQL_GET_EMPLOYEE_INFO = "select e.id, e.first_name,e.last_name,e.middle_name,e.company_id,e.email, e.avatar_url, e.phone,e.birthdate,e.is_active from employee e where e.id  =?;";
    static final String SQL_GET_ANY_EMPLOYEE_ID = "select e.id from employee e limit  1;";
    static final String SQL_ADD_NEW_EMPLOYEE = "insert into employee (is_active, last_name, first_name, phone, company_id) values (true, 'TecnaSchool', 'Test', '79545547845', ?);";
    static final String SQL_GET_SPECIAL_EMPLOYEE = "select e.id from employee e where e.last_name ='TecnaSchool' order by e.id desc limit 1;";
    protected static String dbUsername;
    protected static String dbPassword;
    protected static String dbConnectionString;
    public static void setUp() {
        properties = new ConfProperties();
        dbUsername = properties.getProperty("dbUsername");
        dbPassword = properties.getProperty("dbPassword");
        dbConnectionString = properties.getProperty("dbConnectionString");
    }
    @Step("Подключение к базе данных")
    public void connectToDb() throws SQLException {
        connection = DriverManager.getConnection(ConfProperties.getProperty("dbConnectionString"),
            ConfProperties.getProperty("dbUsername"),
            ConfProperties.getProperty("dbPassword"));
    }
    @Step("Запрашиваем первую компанию из списка по companyId")
    public int getAnyCompanyID() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SQL_GET_ANY_COMPANY_ID);
        resultSet.next();
        return resultSet.getInt(1);
    }
    @Step("Запрашиваем companyId последней созданной компании")
    public int getLastCompanyID() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SQL_GET_LAST_COMPANY_ID);
        resultSet.next();
        return resultSet.getInt(1);
    }
    @Step("Запрашиваем employeeId последнего созданного менеджера в компании")
    public int getLastEmployeeID() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SQL_GET_LAST_EMPLOYEE_ID);
        resultSet.next();
        return resultSet.getInt(1);
    }
    @Step("Создаем компанию и получаем её companyId")
    public int createNewCompany() throws SQLException {
        connection.createStatement().executeUpdate(SQL_ADD_NEW_COMPANY);
        ResultSet resultSet = connection.createStatement().executeQuery(SQL_GET_SPECIAL_COMPANY_ID);
        resultSet.next();
        resultSet.getInt(1);
        return resultSet.getInt(1);
    }
    @Step("Закрываем соединение с базой данных")
    public void closeConnection() throws SQLException {
        connection.close();
    }
    @Step("Удаляем менеджеров из компании и потом удаляем компанию")
    public void deleteCompanyAndItsEmloyees(int companyId) throws SQLException {
        connection.createStatement().executeUpdate(SQL_DELETE_EMPLOYEES_OF_SPECIAL_COMPANY);
        connection.createStatement().executeUpdate(SQL_DELETE_SPECIAL_COMPANY);
    }

    @Step("Создаем нового менеджера в компании и потом получаем его employeeId ")
    public int createNewEmployee(int companyId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_ADD_NEW_EMPLOYEE);
        statement.setInt(1, companyId);
        statement.executeUpdate();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL_GET_SPECIAL_EMPLOYEE);
        resultSet.next();
        return resultSet.getInt(1);
    }
    @Step("Запрос информации о менеджере по его employeeId")
    public ResultSet getEmployeeInfo(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_GET_EMPLOYEE_INFO);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    @Step("Получаем первого менеджжера в списке")
    public int getAnyEmployeeId() throws SQLException {
        String sqlQuery = SQL_GET_ANY_EMPLOYEE_ID;
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
}