package src.controller;

import javax.swing.*;

import src.model.UserOrderModel;
import src.view.UserOrderView;

public class UserOrderController {
    @SuppressWarnings("unused")
    private UserOrderModel model;
    private UserOrderView view;

    public UserOrderController(UserOrderModel model, UserOrderView view) {
        this.model = model;
        this.view = view;
    }

    // Method to add an order
    public void addOrder(String orderDetails) {
        UserOrderModel.addOrder(orderDetails); // Add order to the model
    }

    // Method to get the order history panel
    public JPanel getOrderHistoryPanel() {
        return view.createOrderHistoryPanel(); // Retrieve the updated order history view
    }
}

