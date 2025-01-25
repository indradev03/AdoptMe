package src.view;
import javax.swing.*;
import java.awt.*;

public class UserProfileView {
    private JPanel profilePanel;
    private JTextField firstNameField, lastNameField, usernameField, emailField, phoneField;
    private JPasswordField passwordField;

    public UserProfileView() {
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setLayout(null);

        JPanel profileForm = new JPanel();
        profileForm.setLayout(null);
        profileForm.setBounds(50, 30, 500, 300);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        firstNameLabel.setBounds(10, 10, 100, 25);
        profileForm.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(120, 10, 150, 25);
        profileForm.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        lastNameLabel.setBounds(10, 50, 100, 25);
        profileForm.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(120, 50, 150, 25);
        profileForm.add(lastNameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameLabel.setBounds(10, 90, 100, 25);
        profileForm.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 90, 150, 25);
        profileForm.add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        emailLabel.setBounds(10, 130, 100, 25);
        profileForm.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 130, 150, 25);
        profileForm.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        passwordLabel.setBounds(10, 170, 100, 25);
        profileForm.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 170, 150, 25);
        profileForm.add(passwordField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        phoneLabel.setBounds(10, 210, 100, 25);
        profileForm.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 210, 150, 25);
        profileForm.add(phoneField);

        JButton saveButton = new JButton("Save");
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

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getSaveButton() {
            return null;

    }
}
