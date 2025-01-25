package src.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageEmployeeModel {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Signup";
    private static final String USER = "root";
    private static final String PASSWORD = "sarojkreya369#";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean addEmployee(Employee employee) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO managEmployee (employee_id, first_name, last_name, age, gender, username, " +
                    "password, phone, working_hours, salary, admin_id, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee.getEmployeeId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setInt(4, employee.getAge());
            statement.setString(5, employee.getGender());
            statement.setString(6, employee.getUsername());
            statement.setString(7, employee.getPassword());
            statement.setString(8, employee.getPhone());
            statement.setInt(9, employee.getWorkingHours());
            statement.setDouble(10, employee.getSalary());
            statement.setString(11, employee.getAdminId());
            statement.setString(12, employee.getEmail());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            // Log or handle error
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateEmployee(Employee employee) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "UPDATE managEmployee SET first_name = ?, last_name = ?, age = ?, gender = ?, " +
                    "username = ?, password = ?, phone = ?, working_hours = ?, salary = ?, admin_id = ?, email = ? " +
                    "WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getGender());
            statement.setString(5, employee.getUsername());
            statement.setString(6, employee.getPassword());
            statement.setString(7, employee.getPhone());
            statement.setInt(8, employee.getWorkingHours());
            statement.setDouble(9, employee.getSalary());
            statement.setString(10, employee.getAdminId());
            statement.setString(11, employee.getEmail());
            statement.setString(12, employee.getEmployeeId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean removeEmployee(String employeeId) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM managEmployee WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employeeId);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM managEmployee";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getString("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getInt("working_hours"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("admin_id"),
                        resultSet.getString("email")
                );
                employees.add(employee);
            }
        }
        return employees;
    }
}

