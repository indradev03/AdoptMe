package src.view;

import javax.swing.*;
import java.awt.*;

public class SignUpView {  
    private JFrame frame;
    private JTextField firstNameField, lastNameField, usernameField, emailField, phoneField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JLabel signInLabel;

    public SignUpView() {
        frame = new JFrame("AdoptMe - Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(Color.WHITE);

        // Sign-Up Section
        JLabel signUpLabel = new JLabel("SIGN UP");
        signUpLabel.setFont(new Font("Serif", Font.BOLD, 24));
        signUpLabel.setBounds(580, 125, 150, 30);
        frame.add(signUpLabel);

        // UI Elements
        addLabelAndTextField("First Name:", 350, 200, 100, 25);
        firstNameField = addTextField(430, 200, 150, 25);

        addLabelAndTextField("Last Name:", 600, 200, 100, 25);
        lastNameField = addTextField(680, 200, 150, 25);

        addLabelAndTextField("Username:", 350, 250, 100, 25);
        usernameField = addTextField(430, 250, 150, 25);

        addLabelAndTextField("Email:", 600, 250, 100, 25);
        emailField = addTextField(680, 250, 150, 25);

        addLabelAndTextField("Password:", 350, 300, 100, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(430, 300, 150, 25);
        frame.add(passwordField);

        addLabelAndTextField("Phone:", 600, 300, 100, 25);
        phoneField = addTextField(680, 300, 150, 25);

        signUpButton = new JButton("SIGN UP");
        signUpButton.setBounds(400, 370, 400, 30);
        signUpButton.setBackground(new Color(34, 177, 76));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Serif", Font.BOLD, 16));
        frame.add(signUpButton);

        signInLabel = new JLabel("Sign In");
        signInLabel.setFont(new Font("Serif", Font.BOLD, 14));
        signInLabel.setForeground(Color.BLUE);
        signInLabel.setBounds(670, 435, 300, 20);
        frame.add(signInLabel);

        JLabel alreadyAccountLabel = new JLabel("Already Have an Account?");
        alreadyAccountLabel.setFont(new Font("Serif", Font.BOLD, 14));
        alreadyAccountLabel.setBounds(490, 435, 300, 20);
        frame.add(alreadyAccountLabel);

        frame.setVisible(true);
    }

    private void addLabelAndTextField(String labelText, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setBounds(x, y, width, height);
        frame.add(label);
    }

    private JTextField addTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        frame.add(textField);
        return textField;
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

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public JLabel getSignInLabel() {
        return signInLabel;
    }
}
