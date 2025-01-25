package src.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplyDAO {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Signup";
    private static final String USER = "root";
    private static final String PASSWORD = "sarojkreya369#";
    private static final Logger LOGGER = Logger.getLogger(SupplyDAO.class.getName());

    // Singleton instance for the DAO class
    private static SupplyDAO instance;

    public SupplyDAO() {
        // Private constructor to prevent instantiation
    }

    public static SupplyDAO getInstance() {
        if (instance == null) {
            instance = new SupplyDAO();
        }
        return instance;
    }

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to add a new supply
    public void addSupply(Supply supply) throws SQLException {
        String query = "INSERT INTO supplies (supply_id, name, type, quantity, price, admin_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supply.getSupplyId());
            statement.setString(2, supply.getName());
            statement.setString(3, supply.getType());
            statement.setInt(4, supply.getQuantity());
            statement.setDouble(5, supply.getPrice());
            statement.setString(6, supply.getAdminId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error adding supply: ", ex);
            throw new SQLException("Error adding supply: " + ex.getMessage(), ex);
        }
    }

    // Method to retrieve all supplies
    public List<Supply> getAllSupplies() throws SQLException {
        List<Supply> supplies = new ArrayList<>();
        String query = "SELECT * FROM supplies";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Supply supply = new Supply();
                supply.setSupplyId(rs.getString("supply_id"));
                supply.setName(rs.getString("name"));
                supply.setType(rs.getString("type"));
                supply.setQuantity(rs.getInt("quantity"));
                supply.setPrice(rs.getDouble("price"));
                supply.setAdminId(rs.getString("admin_id"));
                supplies.add(supply);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving supplies: ", ex);
            throw new SQLException("Error retrieving supplies: " + ex.getMessage(), ex);
        }
        return supplies;
    }

    // Method to delete a supply by its ID
    public void deleteSupply(String supplyId) throws SQLException {
        String query = "DELETE FROM supplies WHERE supply_id = ?";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supplyId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting supply: ", ex);
            throw new SQLException("Error deleting supply: " + ex.getMessage(), ex);
        }
    }

    // Method to update a supply
    public void updateSupply(Supply supply) throws SQLException {
        String query = "UPDATE supplies SET name = ?, type = ?, quantity = ?, price = ?, admin_id = ? WHERE supply_id = ?";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supply.getName());
            statement.setString(2, supply.getType());
            statement.setInt(3, supply.getQuantity());
            statement.setDouble(4, supply.getPrice());
            statement.setString(5, supply.getAdminId());
            statement.setString(6, supply.getSupplyId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating supply: ", ex);
            throw new SQLException("Error updating supply: " + ex.getMessage(), ex);
        }
    }

    // Method to search supplies based on criteria (e.g., name)
    public List<Supply> searchSupplies(String searchTerm) throws SQLException {
        List<Supply> supplies = new ArrayList<>();
        String query = "SELECT * FROM supplies WHERE name LIKE ? OR type LIKE ?";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchTerm + "%");
            statement.setString(2, "%" + searchTerm + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Supply supply = new Supply();
                    supply.setSupplyId(rs.getString("supply_id"));
                    supply.setName(rs.getString("name"));
                    supply.setType(rs.getString("type"));
                    supply.setQuantity(rs.getInt("quantity"));
                    supply.setPrice(rs.getDouble("price"));
                    supply.setAdminId(rs.getString("admin_id"));
                    supplies.add(supply);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error searching supplies: ", ex);
            throw new SQLException("Error searching supplies: " + ex.getMessage(), ex);
        }
        return supplies;
    }

    // Supply.java (Model)
    public static class Supply {
        private String supplyId;
        private String name;
        private String type;
        private int quantity;
        private double price;
        private String adminId;

        // Getters and setters
        public String getSupplyId() {
            return supplyId;
        }

        public void setSupplyId(String supplyId) {
            this.supplyId = supplyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }
    }
}
