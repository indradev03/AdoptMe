package src.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserOrderModel {
    private static final List<String[]> orderHistoryItems = new ArrayList<>();
    public static List<String[]> orderHistory = orderHistoryItems; // Exposed for access

    // Method to add an order
    public static void addOrder(String orderDetails) {
        // Generate order number (e.g., using current timestamp)
        String orderNumber = "ORD-" + System.currentTimeMillis();
        
        // Get current date for order placement
        String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        // Assume that an order starts with "Pending" status
        String orderStatus = "Pending";
        
        // Add the order to the history
        orderHistoryItems.add(new String[]{orderNumber, orderDetails, orderStatus, orderDate});
    }

    // Method to get the order history
    public static List<String[]> getOrderHistory() {
        return orderHistoryItems;
    }
}

