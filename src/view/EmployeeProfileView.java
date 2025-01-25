package src.view;

import javax.swing.*;
import java.awt.*;

public class EmployeeProfileView {
    private JPanel profilePanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JButton saveButton;

    public EmployeeProfileView() {
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setBounds(200, 90, 600, 400);
        profilePanel.setLayout(null);

        JPanel profileForm = new JPanel();
        profileForm.setLayout(null);
        profileForm.setBounds(50, 30, 500, 300);

        // Add form components
        firstNameField = new JTextField();
        firstNameField.setBounds(120, 10, 150, 25);
        profileForm.add(createLabel("First Name:", 10, 10));
        profileForm.add(firstNameField);

        lastNameField = new JTextField();
        lastNameField.setBounds(120, 50, 150, 25);
        profileForm.add(createLabel("Last Name:", 10, 50));
        profileForm.add(lastNameField);

        usernameField = new JTextField();
        usernameField.setBounds(120, 90, 150, 25);
        profileForm.add(createLabel("Username:", 10, 90));
        profileForm.add(usernameField);

        emailField = new JTextField();
        emailField.setBounds(120, 130, 150, 25);
        profileForm.add(createLabel("Email:", 10, 130));
        profileForm.add(emailField);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 170, 150, 25);
        profileForm.add(createLabel("Password:", 10, 170));
        profileForm.add(passwordField);

        phoneField = new JTextField();
        phoneField.setBounds(120, 210, 150, 25);
        profileForm.add(createLabel("Phone:", 10, 210));
        profileForm.add(phoneField);

        saveButton = new JButton("Save");
        saveButton.setBounds(120, 250, 100, 30);
        profileForm.add(saveButton);

        profilePanel.add(profileForm);
    }

    public JPanel getProfilePanel() {
        return profilePanel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getPhone() {
        return phoneField.getText();
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setBounds(x, y, 100, 25);
        return label;
    }
}
