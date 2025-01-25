package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import src.main.AdminDashboardApp;
import src.main.AdoptMeSignUpPage;
import src.main.EmployeeDashboardPage;
import src.main.UserDashboardPage;
import src.model.LoginModel;
import src.view.LoginView;

public class LoginController {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        // Add listeners
        view.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.usernameField.getText();
                String password = new String(view.passwordField.getPassword());
                String role = (String) view.loginAsComboBox.getSelectedItem();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(view.frame, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (model.validateLogin(username, password, role)) {
                    view.frame.dispose(); // Close login window
                    if ("Admin".equals(role)) {
                        AdminDashboardApp.main(new String[]{username});
                    } else if ("User".equals(role)) {
                        UserDashboardPage.main(new String[]{ username });
                    } else if ("Employee".equals(role)) {
                        EmployeeDashboardPage.main(new String[]{username});
                    }
                } else {
                    JOptionPane.showMessageDialog(view.frame, "Invalid Username, Password, or Role. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.frame.dispose();
                AdoptMeSignUpPage.main(null);
            }
        });
    }

    public static void main(String[] args) {
        LoginModel model = new LoginModel();
        LoginView view = new LoginView();
        new LoginController(model, view);
    }
}
