import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserDashboard {

    static List<String[]> cartItems = new ArrayList<>(); // To store cart details
    static JPanel mainPanel;
    public static UserProfile userProfile;
    public static PetProfile petProfile;
    public static UserSupplies userSupplies;
    public static UserCart userCart;
    public static UserOrder userOrder;
    
    public static void main(String[] args) {
        
        // Get the username passed from the login page
        String loggedInUsername = args.length > 0 ? args[0] : "Guest"; // Default to "Guest" if no username is passed


        // Create the frame
        JFrame frame = new JFrame("User Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLayout(null);

        // Top green panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(34, 177, 76)); // Green color
        topPanel.setBounds(0, 0, 900, 70);
        topPanel.setLayout(null);
        frame.add(topPanel);

        // User profile button
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
        JLabel usernameLabel = new JLabel(loggedInUsername); // Display the logged-in username
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
        ImageIcon logoIcon = loadImageIcon("AdoptMeLogo.png");
        if (logoIcon != null) {
            Image logoImage = logoIcon.getImage().getScaledInstance(210, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            logoLabel.setBounds(10, 25, 210, 80);
            leftPanel.add(logoLabel);
        }

        // Other navigation buttons
        String[] navOptions = {"Home", "Pets", "Supplies", "Cart", " Order History"};
        int yPosition = 140;
        JButton homeButton = new JButton(navOptions[0]);
        homeButton.setBounds(20, yPosition, 200, 30);
        homeButton.setBackground(new Color(34, 177, 76));
        homeButton.setForeground(Color.WHITE);
        leftPanel.add(homeButton);
        yPosition += 60;

        JButton petsButton = new JButton(navOptions[1]);
        petsButton.setBounds(20, yPosition, 200, 30);
        petsButton.setBackground(new Color(34, 177, 76));
        petsButton.setForeground(Color.WHITE);
        leftPanel.add(petsButton);
        yPosition += 60;

        JButton suppliesButton = new JButton(navOptions[2]);
        suppliesButton.setBounds(20, yPosition, 200, 30);
        suppliesButton.setBackground(new Color(34, 177, 76));
        suppliesButton.setForeground(Color.WHITE);
        leftPanel.add(suppliesButton);
        yPosition += 60;

        JButton cartButton = new JButton(navOptions[3]);
        cartButton.setBounds(20, yPosition, 200, 30);
        cartButton.setBackground(new Color(34, 177, 76));
        cartButton.setForeground(Color.WHITE);
        leftPanel.add(cartButton);
        yPosition += 60;

        JButton orderHistoryButton = new JButton(navOptions[4]);
        orderHistoryButton.setBounds(20, yPosition, 200, 30);
        orderHistoryButton.setBackground(new Color(34, 177, 76));
        orderHistoryButton.setForeground(Color.WHITE);
        leftPanel.add(orderHistoryButton);
        yPosition += 60;



        // Main dashboard area (Initially populated)
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBounds(260, 90, 600, 400);
        mainPanel.setLayout(new GridLayout(2, 2, 20, 20));
        frame.add(mainPanel);

        // Main panel (homepage) with icon buttons
        String[] icons = {"Pets.png", "ManageSuppliesLogo.png", "Mycart.png","OrderHistory.png"}; // Example icon paths
        for (String iconPath : icons) {
            ImageIcon icon = loadImageIcon(iconPath);
            if (icon != null) {
                Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                JButton button = new JButton(new ImageIcon(scaledImage));
                button.setBackground(Color.WHITE);

                // Add specific action listeners based on the icon
                if (iconPath.equals("Pets.png")) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PetProfile petProfile = new PetProfile(frame, loggedInUsername); // Create an instance of PetProfile
                            frame.getContentPane().removeAll();
                            frame.add(topPanel);  // Re-add the top panel
                            frame.add(leftPanel); // Re-add the left panel 
                            frame.add(petProfile.getPetsPanel()); // Add the pet profile panel
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                } else if (iconPath.equals("ManageSuppliesLogo.png")) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Show Supplies panel when Supplies icon is clicked
                            UserSupplies userSupplies = new UserSupplies(frame, loggedInUsername);
                            frame.getContentPane().removeAll();
                            frame.add(topPanel);  // Re-add the top panel
                            frame.add(leftPanel); // Re-add the left panel
                            frame.add(userSupplies.getssupplyPanel()); // Add the supplies panel
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                } else if (iconPath.equals("Mycart.png")) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Create an instance of UserCartPanel
                            UserCart userCartPanel = new UserCart();
                            
                            // Remove current content and add Cart panel
                            frame.getContentPane().removeAll();
                            frame.add(topPanel);  // Add the top panel back
                            frame.add(leftPanel); // Add the left panel back
                            frame.add(userCartPanel.getCartPanel());  // Add the cart panel
                            frame.revalidate();
                            frame.repaint();
                        }
                    });
                } else if (iconPath.equals("OrderHistory.png")) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JPanel orderHistoryPanel = UserOrder.addToOrderHistory(loggedInUsername); // Pass empty string or order details
                            frame.getContentPane().removeAll();
                            frame.add(topPanel);  // Re-add the top panel
                            frame.add(leftPanel); // Re-add the left panel
                            frame.add(orderHistoryPanel); // Add the order history panel
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
        userProfile = new UserProfile(frame, loggedInUsername);

        // Add action listener for the profile button
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch between main panel and profile panel
                frame.getContentPane().removeAll();
                frame.add(topPanel);  // Re-add the top panel
                frame.add(leftPanel); // Re-add the left panel 
                frame.add(userProfile.getProfilePanel());
                frame.revalidate();
                frame.repaint();
            }
        });
            // Add action listener for home button
            homeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                    frame.getContentPane().removeAll();
                    frame.add(topPanel);  // Re-add the top panel
                    frame.add(leftPanel); // Re-add the left panel 
                    frame.add(mainPanel); // Add main panel back
                    frame.revalidate();
                    frame.repaint();
    
                }
            });


        // Action listener to show PetProfile panel
        petsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PetProfile petProfile = new PetProfile(frame, loggedInUsername); // Create an instance of PetProfile
                frame.getContentPane().removeAll();
                frame.add(topPanel);  // Re-add the top panel
                frame.add(leftPanel); // Re-add the left panel 
                frame.add(petProfile.getPetsPanel()); // Add the pet profile panel
                frame.revalidate();
                frame.repaint();
            }
        });

        // Action listener to show Supplies panel
        suppliesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSupplies userSupplies = new UserSupplies(frame, loggedInUsername); // Create an instance of UserSupplies
                frame.getContentPane().removeAll();
                frame.add(topPanel);  // Re-add the top panel
                frame.add(leftPanel); // Re-add the left panel 
                frame.add(userSupplies.getssupplyPanel()); // Add the supplies panel
                frame.revalidate();
                frame.repaint();
            }
        });

        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of UserCartPanel
                UserCart userCartPanel = new UserCart();
                
                // Remove current content and add Cart panel
                frame.getContentPane().removeAll();
                frame.add(topPanel);  // Add the top panel back
                frame.add(leftPanel); // Add the left panel back
                frame.add(userCartPanel.getCartPanel());  // Add the cart panel
                frame.revalidate();
                frame.repaint();
            }
        });

        // Action listener for the order history button
        orderHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the order history panel from UserOrder class
                JPanel orderHistoryPanel = UserOrder.addToOrderHistory(loggedInUsername); // Pass empty string or order details
                frame.getContentPane().removeAll();
                frame.add(topPanel);  // Re-add the top panel
                frame.add(leftPanel); // Re-add the left panel
                frame.add(orderHistoryPanel); // Add the order history panel
                frame.revalidate();
                frame.repaint();
            }
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
