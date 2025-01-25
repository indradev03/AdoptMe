package src.controller;

import javax.swing.*;

import src.model.User;
import src.view.UserDashboardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserDashboardController {
    private User user;
    private UserDashboardView view;
    private UserProfileController userProfileController;

    public UserDashboardController(User user, UserDashboardView view) {
        this.user = user;
        this.view = view;
        this.userProfileController = new UserProfileController(user, view);
        
        initialize();
    }

    private void initialize() {
        // Setup top panel
        JButton profileButton = new JButton("Profile");
        JLabel usernameLabel = new JLabel(user.getUsername());
        JButton logoutButton = new JButton("Logout");

        view.addProfileButton(profileButton);
        view.addUsernameLabel(usernameLabel);
        view.addLogoutButton(logoutButton);

        // Setup left panel
        JButton homeButton = new JButton("Home");
        JButton petsButton = new JButton("Pets");
        JButton suppliesButton = new JButton("Supplies");
        JButton cartButton = new JButton("Cart");
        JButton orderHistoryButton = new JButton("Order History");

        view.addNavButton(homeButton);
        view.addNavButton(petsButton);
        view.addNavButton(suppliesButton);
        view.addNavButton(cartButton);
        view.addNavButton(orderHistoryButton);

        // Setup action listeners for buttons
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userProfileController.saveProfile();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle home button
                // Update view as needed
            }
        });

        // Similar actions for other buttons like petsButton, suppliesButton, etc.
    }

    public void show() {
        view.setVisible(true);
    }
}

