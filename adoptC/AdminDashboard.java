import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard {

    private static JPanel mainPanel;
    private static AdminProfile adminProfile;
    public static ManageEmployee manageEmployee;
    public static ManagePets managePets;
    public static ManageSupplies manageSupplies;

    public static void main(String[] args) {

       // Get the username passed from the login page
       String AdminUsername = args.length > 0 ? args[0] : "Guest"; // Default to "Guest" if no username is passed

        // Create the frame
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLayout(null);

        // Top green panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 177, 76)); // Green color
        topPanel.setBounds(0, 0, 900, 70);
        topPanel.setLayout(null);
        frame.add(topPanel);

        // Admin profile button
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
        JLabel usernameLabel = new JLabel(AdminUsername);
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

        // AdoptMeLogo
        ImageIcon logoIcon = new ImageIcon("AdoptMeLogo.png"); // Update with your image path
        Image logoImage = logoIcon.getImage().getScaledInstance(210, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(10, 20, 210, 80);
        leftPanel.add(logoLabel);

        // Other navigation buttons
        String[] navOptions = {"Home", "Manage Employee", "Manage Pet", "Manage Supplies"};
        int yPosition = 140;

        JButton homeButton = new JButton(navOptions[0]); // Create home button
        homeButton.setBounds(20, yPosition, 200, 30);  // Add position to the leftPanel
        homeButton.setBackground(new Color(34, 177, 76));
        homeButton.setForeground(Color.WHITE);
        leftPanel.add(homeButton);
        yPosition += 60;

        JButton manageEmployeeButton = new JButton(navOptions[1]); // Create home button
        manageEmployeeButton.setBounds(20, yPosition, 200, 30);
        manageEmployeeButton.setBackground(new Color(34, 177, 76));
        manageEmployeeButton.setForeground(Color.WHITE);
        leftPanel.add(manageEmployeeButton);
        yPosition += 60;

        JButton managePetButton = new JButton(navOptions[2]); // Create home button
        managePetButton.setBounds(20, yPosition, 200, 30);
        managePetButton.setBackground(new Color(34, 177, 76));
        managePetButton.setForeground(Color.WHITE);
        leftPanel.add(managePetButton);
        yPosition += 60;

        JButton manageSuppliesButton = new JButton(navOptions[3]); // Create home button
        manageSuppliesButton.setBounds(20, yPosition, 200, 30);
        manageSuppliesButton.setBackground(new Color(34, 177, 76));
        manageSuppliesButton.setForeground(Color.WHITE);
        leftPanel.add(manageSuppliesButton);
        yPosition += 60;


        // Main dashboard area (Initially populated)
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBounds(260, 90, 600, 400);
        mainPanel.setLayout(new GridLayout(2, 2, 20, 20));
        frame.add(mainPanel);

        // Add buttons with images in main panel
        String[] icons = {"ManageEmployellogo.png","ManageSuppliesLogo.png", "ManagePetsLogo.png"}; // Update with actual image paths
        for (String iconPath : icons) {
            ImageIcon icon = loadImageIcon(iconPath);
            if (icon != null) {
                Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                JButton button = new JButton(new ImageIcon(scaledImage));
                button.setBackground(Color.WHITE);

                // Add specific action listeners based on the icon
                if (iconPath.equals("ManageEmployellogo.png")) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.getContentPane().removeAll();
                            frame.add(new ManageEmployee(frame, AdminUsername).getEmployeePanel());
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                } else if (iconPath.equals("ManagePetsLogo.png")) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.getContentPane().removeAll();
                            frame.add(new ManagePets(frame, AdminUsername).getPetPanel());
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                } else if (iconPath.equals("ManageSuppliesLogo.png")) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.getContentPane().removeAll();
                            frame.add(new ManageSupplies(frame, AdminUsername).getSuppliesPanel());
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                } 

                // Add each icon button to the mainPanel
                mainPanel.add(button);
            }
        }

        // Create the AdminProfile instance
        adminProfile = new AdminProfile(frame, AdminUsername);

        // Add action listener for profile button
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch between main panel and profile panel
                frame.remove(mainPanel);
                frame.add(adminProfile.getProfilePanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Add action listener for home button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(adminProfile.getProfilePanel()); // Remove profile panel
                frame.add(mainPanel); // Add main panel back
                frame.revalidate();
                frame.repaint();
            }
        });

        // Add action listener
        manageEmployeeButton.addActionListener(_ -> {
            frame.getContentPane().removeAll();
            frame.add(new ManageEmployee(frame, AdminUsername).getEmployeePanel());
            frame.revalidate();
            frame.repaint();
        });

        // Add action listener
        managePetButton.addActionListener(_ -> {
            frame.getContentPane().removeAll();
            frame.add(new ManagePets(frame, AdminUsername).getPetPanel());
            frame.revalidate();
            frame.repaint();
        });

        // Add action listener
        manageSuppliesButton.addActionListener(_ -> {
            frame.getContentPane().removeAll();
            frame.add(new ManageSupplies(frame, AdminUsername).getSuppliesPanel());
            frame.revalidate();
            frame.repaint();
        });

        // Logout button action listener
        logoutButton.addActionListener(_ -> {
            frame.dispose(); // Close the dashboard
            AdoptMeLoginPage.main(null); // Return to the Login page
        });

        // Show the frame
        frame.setVisible(true);
    }

        // Helper method to load ImageIcon safely
    private static ImageIcon loadImageIcon(String path) {
        try {
            return new ImageIcon(path);
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            return null; // Return null if image is not found
        }
    }
}
