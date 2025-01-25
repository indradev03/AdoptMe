package src.view;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardView {
    private JFrame frame;
    private JPanel topPanel, leftPanel, mainPanel;
    private JLabel usernameLabel;
    private JButton profileButton, logoutButton, homeButton, customersButton, ordersButton;

    public EmployeeDashboardView() {
        frame = new JFrame("Employee Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLayout(null);

        // Top green panel
        topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 177, 76)); // Green color
        topPanel.setBounds(0, 0, 900, 70);
        topPanel.setLayout(null);

        // Profile button
        profileButton = new JButton("Profile");
        profileButton.setBounds(30, 13, 80, 50);
        profileButton.setBackground(Color.WHITE);
        profileButton.setForeground(Color.BLACK);
        profileButton.setFont(new Font("Serif", Font.PLAIN, 14));
        topPanel.add(profileButton);

        // Username label (beside Profile button)
        usernameLabel = new JLabel("Guest");
        usernameLabel.setBounds(120, 22, 200, 30);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 16));
        topPanel.add(usernameLabel);

        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(800, 15, 80, 30);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFont(new Font("Serif", Font.PLAIN, 14));
        topPanel.add(logoutButton);

        // Left navigation panel
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 70, 240, 440);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Black border
        leftPanel.setLayout(null);

        // Navigation buttons
        homeButton = new JButton("Home");
        homeButton.setBounds(20, 140, 200, 30);
        homeButton.setBackground(new Color(34, 177, 76));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(new Font("Serif", Font.BOLD, 16));
        leftPanel.add(homeButton);

        customersButton = new JButton("Customers");
        customersButton.setBounds(20, 200, 200, 30);
        customersButton.setBackground(new Color(34, 177, 76));
        customersButton.setForeground(Color.WHITE);
        customersButton.setFont(new Font("Serif", Font.BOLD, 16));
        leftPanel.add(customersButton);

        ordersButton = new JButton("Orders");
        ordersButton.setBounds(20, 260, 200, 30);
        ordersButton.setBackground(new Color(34, 177, 76));
        ordersButton.setForeground(Color.WHITE);
        ordersButton.setFont(new Font("Serif", Font.BOLD, 16));
        leftPanel.add(ordersButton);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBounds(260, 90, 600, 400);
        mainPanel.setLayout(new BorderLayout());

        frame.add(topPanel);
        frame.add(leftPanel);
        frame.add(mainPanel);
    }

    public void setUsername(String username) {
        usernameLabel.setText(username);
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getCustomersButton() {
        return customersButton;
    }

    public JButton getOrdersButton() {
        return ordersButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void showView() {
        frame.setVisible(true);
    }

    public JPanel getEmployeeProfilePanel() {
            return mainPanel;
    }

    public JPanel getTopPanel() {
            return topPanel;

    }

    public JPanel getLeftPanel() {
            return leftPanel;
    }
}
