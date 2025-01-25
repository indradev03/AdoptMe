package src.view;

import javax.swing.*;
import java.awt.*;

public class AdminProfileView {
    private JPanel profilePanel;
    private JTextField firstNameField, lastNameField, usernameField, emailField, phoneField;
    private JButton saveButton;

    public AdminProfileView() {
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setBounds(200, 90, 600, 400);
        profilePanel.setLayout(null);

        JPanel profileForm = new JPanel();
        profileForm.setLayout(null);
        profileForm.setBounds(50, 30, 500, 300);

        // First Name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        firstNameLabel.setBounds(10, 10, 100, 25);
        profileForm.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(120, 10, 150, 25);
        profileForm.add(firstNameField);

        // Last Name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        lastNameLabel.setBounds(10, 50, 100, 25);
        profileForm.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(120, 50, 150, 25);
        profileForm.add(lastNameField);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameLabel.setBounds(10, 90, 100, 25);
        profileForm.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 90, 150, 25);
        profileForm.add(usernameField);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        emailLabel.setBounds(10, 130, 100, 25);
        profileForm.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 130, 150, 25);
        profileForm.add(emailField);

        // Phone label and text field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        phoneLabel.setBounds(10, 170, 100, 25);
        profileForm.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 170, 150, 25);
        profileForm.add(phoneField);

        // Admin specific fields (Optional)
        JLabel adminRoleLabel = new JLabel("Role:");
        adminRoleLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        adminRoleLabel.setBounds(10, 210, 100, 25);
        profileForm.add(adminRoleLabel);

        JTextField adminRoleField = new JTextField();
        adminRoleField.setBounds(120, 210, 150, 25);
        adminRoleField.setText("Admin");
        adminRoleField.setEditable(false);
        profileForm.add(adminRoleField);

        // Save button
        saveButton = new JButton("Save");
        saveButton.setBounds(120, 250, 100, 30);
        profileForm.add(saveButton);

        profilePanel.add(profileForm);
    }

    public JPanel getProfilePanel() {
        return profilePanel;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
