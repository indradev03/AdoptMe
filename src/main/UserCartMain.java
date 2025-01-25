package src.main;

import javax.swing.*;

import src.controller.UserCartController;
import src.model.UserCartModel;
import src.view.UserCartView;

public class UserCartMain {
    public static void main(String[] args) {
        UserCartModel model = new UserCartModel();
        UserCartView view = new UserCartView();
        UserCartController controller = new UserCartController(model, view);

        // Initialize and display the view
        JFrame frame = new JFrame("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        controller.initialize();  // Initialize the controller (including the cart panel)

        frame.add(view.createCartPanel());  // Add the cart panel to the frame
        frame.setVisible(true);
    }
}

