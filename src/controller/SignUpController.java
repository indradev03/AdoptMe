package src.controller;

import javax.swing.*;

import src.main.AdoptMeLoginPage;
import src.model.UserModel;
import src.view.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController {
    private UserModel model;
    private SignUpView view;

    public SignUpController(UserModel model, SignUpView view) {
        this.model = model;
        this.view = view;

        // Add action listeners
        view.getSignUpButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });

        view.getSignInLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.getSignUpButton().getParent().setVisible(false); // Hide current frame
                AdoptMeLoginPage.main(null); // Navigate to login
            }
        });
    }

    private void handleSignUp() {
        String firstName = view.getFirstNameField().getText();
        String lastName = view.getLastNameField().getText();
        String username = view.getUsernameField().getText();
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());
        String phone = view.getPhoneField().getText();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.registerUser(firstName, lastName, username, email, password, phone)) {
            JOptionPane.showMessageDialog(null, "Sign-Up Successful! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            view.getSignUpButton().getParent().setVisible(false);
            AdoptMeLoginPage.main(null);
        } else {
            JOptionPane.showMessageDialog(null, "Sign-Up Failed! Username might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
