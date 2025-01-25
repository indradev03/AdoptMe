import javax.swing.*;
import java.awt.*;

public class UserSupplies {
    public JPanel supplyPanel;
    public final String[][] supplies = {
        // {ID, Category, ImagePath, Description, Quantity, Price, Availability}

        // Foods
        {"1", "Food", "Food1.png", "Dog Food", "", "500", "Available"},
        {"2", "Food", "Food9.png", "Cat Food", "", "30", "Available"},
        {"3", "Food", "Food3.png", "Dog Food", "", "150", "Out of stock"},
        {"4", "Food", "Food4.png", "Dog Food", "", "100", "Out of Stock"},
        {"5", "Food", "Food5.png", "Cat Food", "", "400", "Available"},
        {"6", "Food", "Food2.png", "Dog Food", "", "300", "Available"}, 
        {"7", "Food", "Food6.png", "Cat Food", "", "200", "Available"},
        {"8", "Food", "Food7.png", "Cat Food", "", "350", "Available"},
        {"9", "Food", "Food8.png", "Cat Food", "", "360", "Available"},


        // Toys
        {"12", "Toys", "Toy1.png", "Squeaky Dog Balls", "", "500", "Available"},
        {"13", "Toys", "Toy2.png", "Vitscan Upgraded Goose Indestructible Dog Toys ", "", "1500", "Out of Stock"},
        {"13", "Toys", "Toy3.png", "Chew Toys", "", "300", "Out of Stock"},
        {"13", "Toys", "Toy4.png", "Tiny dolls", "1 piece", "400", "Available"},
        {"13", "Toys", "Toy5.png", "Cheerble Ball toy", "", "200", "Available"},
        {"13", "Toys", "Toy6.png", "SnakeToys for cat", "", "300", "Available"},
        {"13", "Toys", "Toy7.png", "Soft Toys", "", "100", "Available"},
        {"13", "Toys", "Toy8.png", "Balance Swing Car Puzzles", "", "2000", "Available"},
        {"13", "Toys", "Toy9.png", "Remote Controll lightup Dogball", "", "1000", "Available"},

    };

    public UserSupplies(JFrame frame,String loggedInUsername) {
        // Initialize the panel
        supplyPanel = new JPanel();
        supplyPanel.setBackground(new Color(240, 240, 240));
        supplyPanel.setBounds(260, 90, 600, 400);
        supplyPanel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Supplies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        supplyPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel to hold buttons for categories like Food, Toys, Accessories
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(1, 3, 20, 20));

        // Button for Food
        JButton foodButton = new JButton("Food");
        foodButton.setBackground(Color.WHITE);
        foodButton.setFont(new Font("Serif", Font.PLAIN, 16));
        categoryPanel.add(foodButton);

        // Add ImageIcon to Food button
        ImageIcon foodIcon = new ImageIcon(new ImageIcon("FOODSLOGO.png").getImage().getScaledInstance(300, 313, Image.SCALE_SMOOTH));
        foodButton.setIcon(foodIcon);
        foodButton.setHorizontalTextPosition(SwingConstants.CENTER);
        foodButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        categoryPanel.add(foodButton);

        // Button for Toys
        JButton toysButton = new JButton("Toys");
        toysButton.setBackground(Color.WHITE);
        toysButton.setFont(new Font("Serif", Font.PLAIN, 16));
        categoryPanel.add(toysButton);
                
        // Add ImageIcon to Toy button
        ImageIcon toyIcon = new ImageIcon(new ImageIcon("TOYLOGO.png").getImage().getScaledInstance(300, 313, Image.SCALE_SMOOTH));
        toysButton.setIcon(toyIcon);
        toysButton.setHorizontalTextPosition(SwingConstants.CENTER);
        toysButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        categoryPanel.add(toysButton);

        supplyPanel.add(categoryPanel, BorderLayout.CENTER);

        // Add a Back button at the bottom
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 177, 76));
        backButton.setForeground(Color.WHITE);
        supplyPanel.add(backButton, BorderLayout.SOUTH);

        // Action listeners for category buttons
        foodButton.addActionListener(_ -> displaySupplyCategory(frame, "Food", loggedInUsername));
        toysButton.addActionListener(_ -> displaySupplyCategory(frame, "Toys", loggedInUsername));

        backButton.addActionListener(_ -> {
            frame.dispose(); // Close the dashboard
            UserDashboard.main(new String[]{loggedInUsername});  // Reinitialize the UserDashboard with the logged-in username
        });
    }

    public JPanel getSuppliesPanel() {
        return supplyPanel;
    }

    private void displaySupplyCategory(JFrame frame, String category, String loggedInUsername ) {
        // Clear the frame and set up the layout
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        // Create a new panel for the selected category
        JPanel categoryView = new JPanel();
        categoryView.setBackground(new Color(240, 240, 240));
        categoryView.setLayout(new GridLayout(0, 2, 20, 20)); // Flexible rows
        categoryView.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (String[] supply : supplies) {
            if (supply[1].equals(category)) {
                String id = supply[0];
                String imagePath = supply[2];
                String description = supply[3];
                String quantity = supply[4];
                String price = supply[5];
                String availability = supply[6];

                // Create a button for each supply
                JButton supplyButton = new JButton(description);
                supplyButton.setBackground(Color.WHITE);

                // Scale image based on button size to ensure it fits
                ImageIcon supplyImageIcon = new ImageIcon(new ImageIcon(imagePath).getImage()
                        .getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                supplyButton.setIcon(supplyImageIcon);
                supplyButton.setHorizontalAlignment(SwingConstants.CENTER);
                supplyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                supplyButton.setHorizontalTextPosition(SwingConstants.CENTER);
                categoryView.add(supplyButton);

                // Action listener for each supply button to show supply details
                supplyButton.addActionListener(_ -> showSupplyDetails(frame, id, imagePath, description, quantity, price,  availability, loggedInUsername));
            }
        }

        // Wrap the category view in a JScrollPane for scrolling functionality
        JScrollPane scrollPane = new JScrollPane(categoryView);
        scrollPane.setPreferredSize(new Dimension(600, 400));  // Set the size of the scroll pane
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Add a Back button at the bottom
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 177, 76));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(supplyPanel);
            frame.revalidate();
            frame.repaint();
        });

        frame.add(backButton, BorderLayout.SOUTH);

        // Refresh the frame
        frame.revalidate();
        frame.repaint();
    }

    private void showSupplyDetails(JFrame frame, String id, String imagePath, String description, String quantity, String price, String availability,String loggedInUsername) {
        // Clear the frame and set up the layout to display supply details
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        // Panel for displaying the supply details
        JPanel supplyDetailsPanel = new JPanel();
        supplyDetailsPanel.setLayout(new BorderLayout());

        // Create a panel for the image on the left
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        ImageIcon supplyImageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
        JLabel supplyImageLabel = new JLabel(supplyImageIcon);
        imagePanel.add(supplyImageLabel, BorderLayout.CENTER);

        // Create a panel for the description and details on the right
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        // Add supply description (ID, Description, Quantity, Price, Availability)
        JTextArea supplyDetailsArea = new JTextArea();
        supplyDetailsArea.setEditable(false);
        supplyDetailsArea.setBackground(new Color(240, 240, 240));
        supplyDetailsArea.setText("ID: " + id + "\nDescription: " + description + "\nQuantity: " + quantity + "\nPrice: Rs." + price + "\nAvailability: " + availability);
        supplyDetailsArea.setFont(new Font("Serif", Font.PLAIN, 16));
        supplyDetailsArea.setWrapStyleWord(true);
        supplyDetailsArea.setLineWrap(true);
        supplyDetailsArea.setPreferredSize(new Dimension(300, 150));
        detailsPanel.add(supplyDetailsArea);

        // Add "Add to Cart" button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Serif", Font.PLAIN, 16));
        addToCartButton.setBackground(new Color(34, 177, 76));
        addToCartButton.setForeground(Color.WHITE);
        detailsPanel.add(addToCartButton);

        // Action listener for "Add to Cart" button
        addToCartButton.addActionListener(e -> {
            // Passing supply details to CartPage when user clicks "Add to Cart"
            String[] supplyDetails = {id, description, imagePath, quantity, price, availability};
            CartPage cartPage = new CartPage(supplyDetails);  // Pass supply details array to CartPage
            cartPage.showCartPage(frame, loggedInUsername);  // Show cart page with details
        });

        // Create a main panel to hold image and details side by side
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(imagePanel);  // Add the image panel
        mainPanel.add(detailsPanel);  // Add the details panel

        supplyDetailsPanel.add(mainPanel, BorderLayout.CENTER);

        // Add a Back button to go back to the list of supplies
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 177, 76));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> displaySupplyCategory(frame, supplies[Integer.parseInt(id) - 1][1], loggedInUsername));
        supplyDetailsPanel.add(backButton, BorderLayout.SOUTH);

        frame.add(supplyDetailsPanel);
        frame.revalidate();
        frame.repaint();
    }

    public Component getssupplyPanel() {
        return supplyPanel; // Corrected to return the supply panel
    }
}
