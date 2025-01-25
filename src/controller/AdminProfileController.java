package src.controller;

import src.model.Admin;
import src.view.AdminProfileView;

public class AdminProfileController {
    private AdminProfileView view;
    private Admin model;

    public AdminProfileController(AdminProfileView view, String username) {
        this.view = view;
        this.model = Admin.getAdminDetails(username);

        if (model != null) {
            view.getFirstNameField().setText(model.getFirstName());
            view.getLastNameField().setText(model.getLastName());
            view.getUsernameField().setText(model.getUsername());
            view.getEmailField().setText(model.getEmail());
            view.getPhoneField().setText(model.getPhone());
        }

        view.getSaveButton().addActionListener(e -> {
            String updatedFirstName = view.getFirstNameField().getText();
            String updatedLastName = view.getLastNameField().getText();
            String updatedUsername = view.getUsernameField().getText();
            String updatedEmail = view.getEmailField().getText();
            String updatedPhone = view.getPhoneField().getText();

            // Input validation
            if (updatedFirstName.isEmpty() || updatedLastName.isEmpty() || updatedUsername.isEmpty() || updatedEmail.isEmpty() || updatedPhone.isEmpty()) {
                view.showError("Please fill out all fields.");
                return;
            }

            // Simple email validation
            if (!updatedEmail.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                view.showError("Invalid email format.");
                return;
            }

            // Update the admin details in the model
            Admin.updateAdminDetails(updatedFirstName, updatedLastName, updatedUsername, updatedEmail, updatedPhone);
            view.showSuccess("Profile updated successfully!");
        });
    }
}


