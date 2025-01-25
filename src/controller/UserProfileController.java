package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.model.User;
import src.model.UserDAO;
import src.view.UserDashboardView;
import src.view.UserProfileView;

public class UserProfileController {
    private UserProfileView view;
    private UserDAO model;
    private String username;

    public UserProfileController(UserProfileView view, UserDAO model, String username) {
        this.view = view;
        this.model = model;
        this.username = username;

        // Fetch the user details and display them in the view
        User user = model.getUserDetails(username);
        if (user != null) {
            view.getFirstNameField().setText(user.getFirstName());
            view.getLastNameField().setText(user.getLastName());
            view.getUsernameField().setText(user.getUsername());
            view.getEmailField().setText(user.getEmail());
            view.getPasswordField().setText(user.getPassword());
            view.getPhoneField().setText(user.getPhone());
        }

        // Add listener for the Save button
        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });
    }

    public UserProfileController(User user, UserDashboardView view2) {
    }

    void saveProfile() {
        String updatedFirstName = view.getFirstNameField().getText();
        String updatedLastName = view.getLastNameField().getText();
        String updatedUsername = view.getUsernameField().getText();
        String updatedEmail = view.getEmailField().getText();
        String updatedPassword = new String(view.getPasswordField().getPassword());
        String updatedPhone = view.getPhoneField().getText();

        // Validation logic here (email format, etc.)

        User updatedUser = new User(updatedFirstName, updatedLastName, updatedUsername, updatedEmail, updatedPassword, updatedPhone);
        model.updateUserDetails(updatedUser);
    }
}
