package src.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageOrderModel {
    private List<String[]> orderHistory;

    public ManageOrderModel() {
        this.orderHistory = new ArrayList<>();
    }

    // Fetch order history from the database
    public void fetchOrderHistory() {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";
        String dbUser = "root";
        String dbPassword = "sarojkreya369#"; // Replace with your database password

        String selectQuery = "SELECT receipt_number, item_name, quantity, total_price, payment_method, payment_amount, purchase_date FROM order_history";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery)) {

            orderHistory.clear();  // Clear the current history before fetching new data

            while (resultSet.next()) {
                String receiptNumber = resultSet.getString("receipt_number");
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                double totalPrice = resultSet.getDouble("total_price");
                String paymentMethod = resultSet.getString("payment_method");
                double paymentAmount = resultSet.getDouble("payment_amount");
                Timestamp purchaseDate = resultSet.getTimestamp("purchase_date");

                // Add the order data to the orderHistory list
                orderHistory.add(new String[]{
                        receiptNumber,
                        itemName,
                        String.valueOf(quantity),
                        String.format("Rs.%.2f", totalPrice),
                        paymentMethod,
                        String.format("Rs.%.2f", paymentAmount),
                        purchaseDate.toString()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately (e.g., show a message to the user)
        }
    }

    // Retrieve the order history
    public List<String[]> getOrderHistory() {
        return orderHistory;
    }

    // Remove an order from the history
    public void removeOrder(int index) {
        orderHistory.remove(index);
    }
}
