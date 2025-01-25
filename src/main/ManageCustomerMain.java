package src.main;

import javax.swing.*;

import src.controller.ManageCustomersController;
import src.view.ManageCustomersView;

public class ManageCustomerMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Manage Customers");

        // Create the view and controller
        ManageCustomersView view = new ManageCustomersView();
        @SuppressWarnings("unused")
        ManageCustomersController controller = new ManageCustomersController(view);

        // Setup the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.add(view.getPanel());
        frame.setVisible(true);
    }
}
