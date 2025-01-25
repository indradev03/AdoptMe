package src.model;

import java.sql.*;

public class EmployeeDatabase {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Signup";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "sarojkreya369#";

    public static Emp getEmployeeDetails(String username) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM managEmployee WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Emp(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateEmployeeDetails(Emp  employee) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "UPDATE managEmployee SET first_name = ?, last_name = ?, email = ?, password = ?, phone = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPassword());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getUsername());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
