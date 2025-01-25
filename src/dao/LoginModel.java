package src.model;

import java.sql.*;

public class LoginModel {
    private static final String DATABASE_NAME = "Signup";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/" + DATABASE_NAME;
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "sarojkreya369#";

    public boolean validateLogin(String username, String password, String role) {
        String query;
        if ("Admin".equalsIgnoreCase(role)) {
            query = "SELECT * FROM Admintable2 WHERE username = ? AND password = ?";
        } else if ("User".equalsIgnoreCase(role)) {
            query = "SELECT * FROM users WHERE username = ? AND password = ?";
        } else if ("Employee".equalsIgnoreCase(role)) {
            query = "SELECT * FROM managEmployee WHERE username = ? AND password = ?";
        } else {
            System.out.println("Invalid role: " + role);
            return false; // Invalid role
        }

        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Return true if a record exists
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
