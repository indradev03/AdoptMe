package src.model;

// PetModel.java - This will represent the data and handle the database operations.

import java.sql.*;

public class ManagePetModel {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Signup"; // Modify with your database URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "sarojkreya369#"; // Your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add a pet to the database
    public boolean addPet(String name, String type, String species, double price, int age, String adminID, String imagePath) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO ManagePets (name, type, species, price, age, admin_id, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, type);
                stmt.setString(3, species);
                stmt.setDouble(4, price);
                stmt.setInt(5, age);
                stmt.setString(6, adminID);
                stmt.setString(7, imagePath);
                return stmt.executeUpdate() > 0;
            }
        }
    }

    // Edit a pet in the database
    public boolean editPet(int petID, String name, String type, String species, double price, int age, String adminID, String imagePath) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "UPDATE ManagePets SET name = ?, type = ?, species = ?, price = ?, age = ?, admin_id = ?, image_path = ? WHERE pet_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, type);
                stmt.setString(3, species);
                stmt.setDouble(4, price);
                stmt.setInt(5, age);
                stmt.setString(6, adminID);
                stmt.setString(7, imagePath);
                stmt.setInt(8, petID);
                return stmt.executeUpdate() > 0;
            }
        }
    }

    // Remove a pet from the database
    public boolean removePet(int petID) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM ManagePets WHERE pet_id=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, petID);
                return stmt.executeUpdate() > 0;
            }
        }
    }

    // Retrieve all pets from the database
    public ResultSet getAllPets() throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM ManagePets";
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        }
    }
}
