package src.controller;

import javax.swing.*;

import src.model.Emp;
import src.model.EmployeeDatabase;
import src.view.EmployeeProfileView;

import java.awt.event.ActionListener;

public class EmployeeProfileController {
    private EmployeeProfileView view;
    private EmployeeDatabase model;

    public EmployeeProfileController(EmployeeProfileView view, EmployeeDatabase model) {
        this.view = view;
        this.model = model;

        // Fetch employee details when view is initialized
        String username = "sampleUsername";  // This can be passed in constructor or set dynamically
        Emp employee = model.getEmployeeDetails(username);
        if (employee != null) {
            view.firstNameField.setText(employee.getFirstName());
            view.lastNameField.setText(employee.getLastName());
            view.usernameField.setText(employee.getUsername());
            view.emailField.setText(employee.getEmail());
            view.passwordField.setText(employee.getPassword());
            view.phoneField.setText(employee.getPhone());
        }

        // Add save button listener
        view.getSaveButton().addActionListener(saveButtonListener());
    }

    private ActionListener saveButtonListener() {
        return e -> {
            String firstName = view.getFirstName();
            String lastName = view.getLastName();
            String username = view.getUsername();
            String email = view.getEmail();
            String password = view.getPassword();
            String phone = view.getPhone();

            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                return;
            }

            if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                JOptionPane.showMessageDialog(null, "Invalid email format.");
                return;
            }

            Emp updatedEmployee = new Emp(firstName, lastName, username, email, password, phone);
            if (model.updateEmployeeDetails(updatedEmployee)) {
                JOptionPane.showMessageDialog(null, "Profile updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update profile.");
            }
        };
    }
}

