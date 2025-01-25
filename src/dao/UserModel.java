package src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {
    private static final String DATABASE_NAME = "Signup";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/" + DATABASE_NAME;
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "sarojkreya369#";

    public boolean registerUser(String firstName, String lastName, String username, String email, String password, String phone) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "INSERT INTO users (first_name, last_name, username, email, password, phone, role) VALUES (?, ?, ?, ?, ?, ?, 'User')";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setString(6, phone);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

