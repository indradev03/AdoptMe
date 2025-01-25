package src.main;

import javax.swing.*;

import src.controller.UserSuppliesController;

public class UserSuppliesApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Supplies");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize controller with the frame and logged-in username
        String loggedInUsername = "user123";  // Example username
        @SuppressWarnings("unused")
        UserSuppliesController controller = new UserSuppliesController(frame, loggedInUsername);

        frame.setVisible(true);
    }
}
