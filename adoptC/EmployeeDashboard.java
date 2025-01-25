import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDashboard {

    private static JPanel mainPanel;
    private static ManageCustomers manageCustomers;
    private static ManageOrders manageOrders;
    private static JPanel homePanel;
    private static EmployeeProfile employeeProfile;

    public static void main(String[] args) {

        // Get the username passed from the login page
        String EmployeeUsername = args.length > 0 ? args[0] : "Guest"; // Default to "Guest" if no username is passed

        // Create the frame
        JFrame frame = new JFrame("Employee Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLayout(null);

        // Top green panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 177, 76)); // Green color
        topPanel.setBounds(0, 0, 900, 70);
        topPanel.setLayout(null);
        frame.add(topPanel);

        // Employee profile button
        JButton profileButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.LIGHT_GRAY); // Change color when pressed
                } else {
                    g.setColor(getBackground());
                }
                g.fillOval(0, 0, getWidth(), getHeight()); // Draw circular shape
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // Draw circular border
            }

            @Override
            public boolean contains(int x, int y) {
                int radius = getWidth() / 2;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
            }
        };

        // Set the size of the profile button
        profileButton.setBounds(30, 5, 65, 65); // Adjust width and height to make it a circle
        profileButton.setBackground(Color.WHITE);
        profileButton.setForeground(Color.BLACK);
        profileButton.setFont(new Font("Serif", Font.PLAIN, 14));
        profileButton.setContentAreaFilled(false); // Avoid default rectangular background
        profileButton.setFocusPainted(false); // Remove focus rectangle

        // Load the logo image
        ImageIcon profileIcon = new ImageIcon("ProfileLogo.png"); // Update with your logo path
        Image profileImage = profileIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize the logo to fit inside the button
        profileButton.setIcon(new ImageIcon(profileImage)); // Set the logo to the button

        topPanel.add(profileButton); // Add the button to the top panel



        // Username label (beside Profile button)
        JLabel usernameLabel = new JLabel(EmployeeUsername);
        usernameLabel.setBounds(110, 22, 200, 30);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 16));
        topPanel.add(usernameLabel);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(790, 18, 80, 30);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFont(new Font("Serif", Font.PLAIN, 14));
        topPanel.add(logoutButton);

        // Left navigation panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 70, 240, 440);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Black border
        leftPanel.setLayout(null);
        frame.add(leftPanel);

        // AdoptMELogo
        ImageIcon logoIcon = new ImageIcon("AdoptMeLogo.png"); // Update with your image path
        Image logoImage = logoIcon.getImage().getScaledInstance(210, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(10, 25, 210, 80);
        leftPanel.add(logoLabel);

        // Navigation buttons
        // Home Button
        JButton homeButton = new JButton("Home");
        homeButton.setBounds(20, 140, 200, 30);
        homeButton.setBackground(new Color(34, 177, 76));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(new Font("Serif", Font.BOLD, 16));
        leftPanel.add(homeButton);

        // Customers Button
        JButton customersButton = new JButton("Customers");
        customersButton.setBounds(20, 200, 200, 30);
        customersButton.setBackground(new Color(34, 177, 76));
        customersButton.setForeground(Color.WHITE);
        customersButton.setFont(new Font("Serif", Font.BOLD, 16));
        leftPanel.add(customersButton);

        // Orders Button
        JButton ordersButton = new JButton("Orders");
        ordersButton.setBounds(20, 260, 200, 30);
        ordersButton.setBackground(new Color(34, 177, 76));
        ordersButton.setForeground(Color.WHITE);
        ordersButton.setFont(new Font("Serif", Font.BOLD, 16));
        leftPanel.add(ordersButton);


        // Profile button action listener
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(topPanel);  // Add the top panel back
                frame.add(leftPanel); // Add the left panel back
                frame.add(employeeProfile.getProfilePanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Add action listener for home button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(employeeProfile.getProfilePanel()); // Remove profile panel
                frame.getContentPane().removeAll();
                frame.add(topPanel);  // Add the top panel back
                frame.add(leftPanel); // Add the left panel back
                frame.add(mainPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

         // Add action listener
        ordersButton.addActionListener(_ -> {
            frame.getContentPane().removeAll();
            frame.add(topPanel);  // Add the top panel back
            frame.add(leftPanel); // Add the left panel back
            frame.add(new ManageOrders(frame,EmployeeUsername).getOrdersPanel());
            frame.revalidate();
            frame.repaint();
        });

         // Add action listener
        customersButton.addActionListener(_ -> {
            frame.getContentPane().removeAll();
            frame.add(topPanel);  // Add the top panel back
            frame.add(leftPanel); // Add the left panel back
            frame.add(new ManageCustomers(frame,EmployeeUsername).getCustomerPanel());
            frame.revalidate();
            frame.repaint();
        });

        // customersButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Create an instance of UserCartPanel
        //         ManageCustomers manageCustomers = new ManageCustomers(frame, EmployeeUsername);
                
        //         // Remove current content and add Cart panel
        //         frame.getContentPane().removeAll();
        //         frame.add(topPanel);  // Add the top panel back
        //         frame.add(leftPanel); // Add the left panel back
        //         frame.add(userCartPanel.getCartPanel());  // Add the cart panel
        //         frame.revalidate();
        //         frame.repaint();
        //     }
        // });


        // Main panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBounds(260, 130, 600, 500);
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        // Create additional panels
        manageCustomers = new ManageCustomers(frame, EmployeeUsername);
        manageOrders = new ManageOrders(frame, EmployeeUsername);
        homePanel = createHomePanel(EmployeeUsername);
        employeeProfile = new EmployeeProfile(frame, EmployeeUsername);

        // Set initial view to Home panel
        switchMainPanel(homePanel);



        // Logout button action listener
        logoutButton.addActionListener(_ -> {
            frame.dispose(); // Close the dashboard
            AdoptMeLoginPage.main(null); // Return to the Login page
        });

        // Show the frame
        frame.setVisible(true);
    }

    private static JPanel createHomePanel(String EmployeeUsername) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(new BorderLayout()); // Use BorderLayout for flexibility
    
        // Welcome message with username
        JLabel welcomeLabel = new JLabel("Welcome, " + EmployeeUsername );
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setForeground(new Color(34, 177, 76)); // Match the theme color
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
    
        // Path to the image
        String imagePath = "EmployeDashboard.png"; // Update with the correct image path
    
        // Create and scale the image
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
    
        // Add the image to a JLabel
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
    
        // Create a container panel for the welcome label and the image
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Arrange components vertically
        contentPanel.setBackground(new Color(240, 240, 240));
        
        contentPanel.add(welcomeLabel); // Add the welcome message
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space between the welcome message and image
        contentPanel.add(imageLabel); // Add the image below the welcome message
    
        // Add content panel to the main panel
        panel.add(contentPanel, BorderLayout.CENTER);
    
        return panel;
    }
    
    private static void switchMainPanel(JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.add(newPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
