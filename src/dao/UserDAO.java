package src.model;

import java.sql.*;

public class UserDAO {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Signup";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "sarojkreya369#";

    public User getUserDetails(String username) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
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

    public void updateUserDetails(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, phone = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getUsername());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Failed to update profile.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

