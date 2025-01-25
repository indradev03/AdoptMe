package src.view;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardView {
    private JFrame frame;
    private JPanel mainPanel, leftPanel, topPanel;
    private JButton profileButton, logoutButton, homeButton, manageEmployeeButton, managePetButton, manageSuppliesButton;

    public AdminDashboardView() {
        // Create the frame
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLayout(null);

        // Top green panel
        topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 177, 76)); // Green color
        topPanel.setBounds(0, 0, 900, 70);
        topPanel.setLayout(null);
        frame.add(topPanel);

        // Admin profile button
        profileButton = new JButton("Profile");
        profileButton.setBounds(30, 13, 80, 50);
        profileButton.setBackground(Color.WHITE);
        profileButton.setForeground(Color.BLACK);
        profileButton.setFont(new Font("Serif", Font.PLAIN, 14));
        topPanel.add(profileButton);

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
        frame.add(leftPanel);

        // Logo
        ImageIcon logoIcon = new ImageIcon("Screenshot 2024-12-22 185055.png"); // Update with your image path
        Image logoImage = logoIcon.getImage().getScaledInstance(210, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(10, 20, 210, 80);
        leftPanel.add(logoLabel);

        // Other navigation buttons
        homeButton = new JButton("Home");
        homeButton.setBounds(20, 140, 200, 30);
        homeButton.setBackground(new Color(34, 177, 76));
        homeButton.setForeground(Color.WHITE);
        leftPanel.add(homeButton);

        manageEmployeeButton = new JButton("Manage Employee");
        manageEmployeeButton.setBounds(20, 200, 200, 30);
        manageEmployeeButton.setBackground(new Color(34, 177, 76));
        manageEmployeeButton.setForeground(Color.WHITE);
        leftPanel.add(manageEmployeeButton);

        managePetButton = new JButton("Manage Pet");
        managePetButton.setBounds(20, 260, 200, 30);
        managePetButton.setBackground(new Color(34, 177, 76));
        managePetButton.setForeground(Color.WHITE);
        leftPanel.add(managePetButton);

        manageSuppliesButton = new JButton("Manage Supplies");
        manageSuppliesButton.setBounds(20, 320, 200, 30);
        manageSuppliesButton.setBackground(new Color(34, 177, 76));
        manageSuppliesButton.setForeground(Color.WHITE);
        leftPanel.add(manageSuppliesButton);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBounds(260, 90, 600, 400);
        frame.add(mainPanel);

        // Show the frame
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
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

    public JButton getManageEmployeeButton() {
        return manageEmployeeButton;
    }

    public JButton getManagePetButton() {
        return managePetButton;
    }

    public JButton getManageSuppliesButton() {
        return manageSuppliesButton;
    }

    public JPanel getMainPanel() {
            return leftPanel;

    }

    public JPanel createProfilePanel(String adminUsername) {
;
return leftPanel;
    }
}

