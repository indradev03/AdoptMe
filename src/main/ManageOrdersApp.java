package src.main;

import javax.swing.*;

import src.controller.ManageOrderController;
import src.model.ManageOrderModel;
import src.view.ManageOrderView;

public class ManageOrdersApp {
    public static void main(String[] args) {
        // Initialize the MVC components
        ManageOrderModel model = new ManageOrderModel();
        ManageOrderView view = new ManageOrderView();
        ManageOrderController controller = new ManageOrderController(model, view);

        // Create the JFrame
        JFrame frame = new JFrame("Order Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.add(view);
        frame.setVisible(true);
    }
}
