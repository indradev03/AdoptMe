import javax.swing.*;
import java.awt.*;

public class CartPage {
    private final String[] petDetails;

    
        public CartPage(String[] petDetails) {
            this.petDetails = petDetails; // Pet details passed here
        }
    
        public void showCartPage(JFrame frame,String loggedInUsername) {
            frame.getContentPane().removeAll();
            
            // Set JPanel to use null layout for manual positioning
            JPanel cartPanel = new JPanel();
            cartPanel.setLayout(null);
            cartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Create components with specific positions and sizes
            JLabel itemLabel = new JLabel("ItemName: " + petDetails[1]);
            itemLabel.setBounds(20, 20, 200, 30); // x, y, width, height
            cartPanel.add(itemLabel);
    
            JLabel priceLabel = new JLabel("Price: " + petDetails[4]);
            priceLabel.setBounds(20, 60, 200, 30);
            cartPanel.add(priceLabel);
            
            JLabel quantities = new JLabel("Quantity" );
            quantities.setBounds(20, 100, 200, 30);
            cartPanel.add(quantities);
            
            JTextField quantityField = new JTextField();
            quantityField.setBounds(80, 100, 150, 30); // Positioned after price
            cartPanel.add(quantityField);
    
            JLabel totalLabel = new JLabel("Total Price: Rs.0");
            totalLabel.setBounds(20, 140, 200, 30);
            cartPanel.add(totalLabel);
    
            JButton calculateButton = new JButton("Calculate price");
            calculateButton.setBounds(20, 180, 150, 30);
            cartPanel.add(calculateButton);
    
            JButton confirmButton = new JButton("ADD TO CART");
            confirmButton.setBounds(20, 220, 150, 30);
            cartPanel.add(confirmButton);
    
            // Calculate button action listener
            calculateButton.addActionListener(_ -> {
                try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    double unitPrice = Double.parseDouble(petDetails[4].replace("Rs.", ""));
                    double total = quantity * unitPrice;
                    totalLabel.setText("Total Price: Rs." + total);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
    
            // Confirm button action listener
            confirmButton.addActionListener(_ -> {
                try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    double unitPrice = Double.parseDouble(petDetails[4].replace("Rs.", ""));
                    UserCart.addToCart(petDetails[1], quantity, unitPrice);
                JOptionPane.showMessageDialog(frame, "Item added to cart successfully!");
                frame.revalidate();
                frame.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(20, 260, 100, 30); // Positioned at the bottom
        backButton.addActionListener(_ -> {
            frame.dispose(); // Close the dashboard
            UserDashboard.main(new String[]{loggedInUsername});  // Reinitialize the UserDashboard with the logged-in username
        });
        cartPanel.add(backButton);

        // Add the cart panel to the frame
        frame.add(cartPanel);
        frame.setSize(400, 350); // Set frame size to fit the components
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }
}
