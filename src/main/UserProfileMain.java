package src.main;

import javax.swing.*;

import src.controller.UserProfileController;
import src.model.UserDAO;
import src.view.UserProfileView;

public class UserProfileMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Profile");
        String loggedInUsername = "user123"; // For example
        UserProfileView view = new UserProfileView();
        UserDAO model = new UserDAO();
        new UserProfileController(view, model, loggedInUsername);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.add(view.getProfilePanel());
        frame.setVisible(true);
    }
}

