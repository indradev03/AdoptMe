package src.view;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    public JFrame frame;
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JComboBox<String> loginAsComboBox;
    public JButton loginButton;
    public JLabel signUpLabel;

    public LoginView() {
        frame = new JFrame("AdoptMe - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);

        // Login section
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 24));
        loginLabel.setBounds(610, 120, 150, 30);
        frame.add(loginLabel);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameLabel.setBounds(450, 170, 100, 25);
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(550, 170, 200, 25);
        frame.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        passwordLabel.setBounds(450, 210, 100, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(550, 210, 200, 25);
        frame.add(passwordField);

        // Login As
        JLabel loginAsLabel = new JLabel("Login As:");
        loginAsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        loginAsLabel.setBounds(450, 270, 100, 25);
        frame.add(loginAsLabel);

        String[] loginOptions = {"User", "Admin", "Employee"};
        loginAsComboBox = new JComboBox<>(loginOptions);
        loginAsComboBox.setBounds(550, 270, 200, 25);
        frame.add(loginAsComboBox);

        // Login button
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(450, 335, 300, 25);
        frame.add(loginButton);

        // Sign-Up
        signUpLabel = new JLabel("Sign Up");
        signUpLabel.setFont(new Font("Serif", Font.BOLD, 14));
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setBounds(650, 430, 300, 20);
        frame.add(signUpLabel);

        frame.setVisible(true);
    }
}

