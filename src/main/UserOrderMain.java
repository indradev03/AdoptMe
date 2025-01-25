package src.main;

import javax.swing.*;

import src.controller.UserOrderController;
import src.model.UserOrderModel;
import src.view.UserOrderView;

public class UserOrderMain {
    public static void main(String[] args) {
        // Initialize the MVC components
        UserOrderModel model = new UserOrderModel();
        UserOrderView view = new UserOrderView();
        UserOrderController controller = new UserOrderController(model, view);

        // Initialize JFrame
        JFrame frame = new JFrame("User Order History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Adding a few sample orders
        controller.addOrder("Golden Retriever - 1 unit");
        controller.addOrder("Labrador - 2 units");

        // Add the order history panel to the frame
        frame.add(controller.getOrderHistoryPanel());
        frame.setVisible(true);
    }
}
