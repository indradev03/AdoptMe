import javax.swing.*;
import java.awt.*;


public class PetProfile {
    public JPanel petPanel;
    public final String[][] pets = {
        // {ID, Category, ImagePath, Description, Age, Price, Health}

        // Dogs
        {"1", "Dogs", "Dog1.png", "German Shepard Double Coated Female Puppy", "4 Years", "60000", "Healthy"},
        {"2", "Dogs", "Dog2.png", "Lasha Apso Female Puppy", "2 Years", "15000", "Healthy"},
        {"3", "Dogs", "Dog3.png", "Labrador Retriever Female Puppy", "2 Years", "15000", "Healthy"},
        {"4", "Dogs", "Dog4.png", "Japanese Spitz Female Puppy", "3 Years", "7000", "Healthy"},
        {"5", "Dogs", "Dog5.png", "Golden Retriever Male Puppy", "3 Year", "22000", "Healthy"},
        {"6", "Dogs", "Dog6.png", "Saint Bernard Female Puppy", "7 Years", "180000", "Healthy"},
        {"7", "Dogs", "Dog7.png", "Siberian Husky", "7 Years", "50000", "Healthy"},
        {"9", "Dogs", "Dog8.png", "Samoyed", "4 Years", "45000", "Eye issues,Heart Problem"},
        {"9", "Dogs", "Dog9.png", "English BullDog Male", "6 Years", "105000", "Healthy"},
        {"10", "Dogs", "Dog10.png", "American BullDog Male", "7 Years", "140000", "Healthy"},

        // Cats
        {"11", "Cats", "Cat1.png", "British Shorthair", "1 Year", "20000", "Healthy"},
        {"12", "Cats", "Cat2.png", "American Bobtail", "2 Years", "55000", "Healthy"},
        {"13", "Cats", "Cat3.png", "Siamese Cat", "1 Year", "4000", "Healthy"},
        {"14", "Cats", "Cat4.png", "Abyssinian cat", "3 Year", "11000", "Healthy"},
        {"15", "Cats", "Cat5.png", "sphynx cat ", "4 Year", "40000", "Healthy"},
        {"16", "Cats", "Cat6.png", "Bengal Cat", "1 Year", "50000", "Healthy"},
        {"17", "Cats", "Cat7.png", "Siberian Cat", "3 Year", "42000", "Healthy"}   
        
    };


        public PetProfile(JFrame frame, String loggedInUsername) {
                // Initialize the panel
                petPanel = new JPanel();
                petPanel.setBackground(new Color(240, 240, 240));
                petPanel.setBounds(260, 90, 600, 400);
                petPanel.setLayout(new BorderLayout());
        
                // Title Label
                JLabel titleLabel = new JLabel("Pet Profiles", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
                petPanel.add(titleLabel, BorderLayout.NORTH);
        
                // Panel to hold buttons for Dogs and Cats
                JPanel categoryPanel = new JPanel();
                categoryPanel.setLayout(new GridLayout(1, 2, 20, 20));
        
                // Button for Dogs
                JButton dogsButton = new JButton("Dogs");
                dogsButton.setBackground(Color.WHITE);
                dogsButton.setFont(new Font("Serif", Font.PLAIN, 16));
                categoryPanel.add(dogsButton);
        
                // Add ImageIcon to Dogs button
                ImageIcon dogIcon = new ImageIcon(new ImageIcon("DogLogo.png").getImage().getScaledInstance(300, 313, Image.SCALE_SMOOTH));
                dogsButton.setIcon(dogIcon);
                dogsButton.setHorizontalTextPosition(SwingConstants.CENTER);
                dogsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                categoryPanel.add(dogsButton);
        
                // Button for Cats
                JButton catsButton = new JButton("Cats");
                catsButton.setBackground(Color.WHITE);
                catsButton.setFont(new Font("Serif", Font.PLAIN, 16));
                categoryPanel.add(catsButton);

                // Add ImageIcon to Cats button
                ImageIcon catIcon = new ImageIcon(new ImageIcon("CatsLogo.png").getImage().getScaledInstance(300, 313, Image.SCALE_SMOOTH));
                catsButton.setIcon(catIcon);
                catsButton.setHorizontalTextPosition(SwingConstants.CENTER);
                catsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                categoryPanel.add(catsButton);
        
                petPanel.add(categoryPanel, BorderLayout.CENTER);
        
                // Add a Back button at the bottom
                JButton backButton = new JButton("Back");
                backButton.setFont(new Font("Serif", Font.PLAIN, 16));
                backButton.setBackground(new Color(34, 177, 76));
                backButton.setForeground(Color.WHITE);
                petPanel.add(backButton, BorderLayout.SOUTH);
        
                // Action listeners for category buttons
                dogsButton.addActionListener(_ -> displayPetCategory(frame, "Dogs", loggedInUsername));
                catsButton.addActionListener(_ -> displayPetCategory(frame, "Cats", loggedInUsername));
        
                backButton.addActionListener(_ -> {
                    frame.dispose(); // Close the dashboard
                    UserDashboard.main(new String[]{loggedInUsername});  // Reinitialize the UserDashboard with the logged-in username
                });
            }
        
            public JPanel getPetsPanel() {
                return petPanel;
            }
        
            private void displayPetCategory(JFrame frame, String category, String loggedInUsername) {
                // Clear the frame and set up the layout
                frame.getContentPane().removeAll();
                frame.setLayout(new BorderLayout());
        
                // Create a new panel for the selected category
                JPanel categoryView = new JPanel();
                categoryView.setBackground(new Color(240, 240, 240));
                categoryView.setLayout(new GridLayout(0, 2, 20, 20)); // Flexible rows
                categoryView.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
                for (String[] pet : pets) {
                    if (pet[1].equals(category)) {
                        String id = pet[0];
                        String imagePath = pet[2];
                        String description = pet[3];
                        String age = pet[4];
                        String price = pet[5];
                        String health = pet[6];
        
                        // Create a button for each pet
                        JButton petButton = new JButton(description);
                        petButton.setBackground(Color.WHITE);
        
                        // Scale image based on button size to ensure it fits
                        ImageIcon petImageIcon = new ImageIcon(new ImageIcon(imagePath).getImage()
                                .getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                        petButton.setIcon(petImageIcon);
                        petButton.setHorizontalAlignment(SwingConstants.CENTER);
                        petButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                        petButton.setHorizontalTextPosition(SwingConstants.CENTER);
                        categoryView.add(petButton);
        
                        // Action listener for each pet button to show pet details
                        petButton.addActionListener(_ -> showPetDetails(frame, id, imagePath, description, age, price, health, loggedInUsername));
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
                    frame.add(petPanel);
                    frame.revalidate();
                    frame.repaint();
        
                    // frame.dispose(); // Close the dashboard
                    // UserDashboard.main(new String[]{loggedInUsername});  // Reinitialize the UserDashboard with the logged-in username
            
        });

        frame.add(backButton, BorderLayout.SOUTH);

        // Refresh the frame
        frame.revalidate();
        frame.repaint();
    }
    private void showPetDetails(JFrame frame, String id, String imagePath, String description, String age, String price, String health,String loggedInUsername) {
        // Clear the frame and set up the layout to display pet details
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
    
        // Panel for displaying the pet details
        JPanel petDetailsPanel = new JPanel();
        petDetailsPanel.setLayout(new BorderLayout());
    
        // Create a panel for the image on the left
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        ImageIcon petImageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
        JLabel petImageLabel = new JLabel(petImageIcon);
        imagePanel.add(petImageLabel, BorderLayout.CENTER);
    
        // Create a panel for the description and details on the right
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    
        // Add pet description (Name, Age, Price, Health Status)
        JTextArea petDetailsArea = new JTextArea();
        petDetailsArea.setEditable(false);
        petDetailsArea.setBackground(new Color(240, 240, 240));
        petDetailsArea.setText("ID: " + id + "\nName: " + description + "\nAge: " + age + "\nPrice: Rs." + price + "\nHealth: " + health);
        petDetailsArea.setFont(new Font("Serif", Font.PLAIN, 16));
        petDetailsArea.setWrapStyleWord(true);
        petDetailsArea.setLineWrap(true);
        petDetailsArea.setPreferredSize(new Dimension(300, 150));
        detailsPanel.add(petDetailsArea);
    
        // Add "Add to Cart" button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Serif", Font.PLAIN, 16));
        addToCartButton.setBackground(new Color(34, 177, 76));
        addToCartButton.setForeground(Color.WHITE);
        detailsPanel.add(addToCartButton);
    
        // Action listener for "Add to Cart" button
        addToCartButton.addActionListener(e -> {
            // Passing pet details to CartPage when user clicks "Add to Cart"
            String[] petDetails = {id, description, imagePath, age, price, health};
            CartPage cartPage = new CartPage(petDetails);  // Pass pet details array to CartPage
            cartPage.showCartPage(frame, loggedInUsername);  // Show cart page with details
        });
    
        // Create a main panel to hold image and details side by side
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(imagePanel);  // Add the image panel
        mainPanel.add(detailsPanel);  // Add the details panel
    
        petDetailsPanel.add(mainPanel, BorderLayout.CENTER);
    
        // Add a Back button to go back to the list of pets
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 177, 76));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> displayPetCategory(frame, pets[Integer.parseInt(id) - 1][1], health));
        petDetailsPanel.add(backButton, BorderLayout.SOUTH);
        
        frame.add(petDetailsPanel);
        frame.revalidate();
        frame.repaint();
    }
}    
