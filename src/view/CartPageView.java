package src.view;

import javax.swing.*;
import java.awt.*;

public class CartPageView {
    private JTextField quantityField;
    private JLabel totalLabel;

    public JPanel createCartPanel(String[] petDetails) {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components with specific positions and sizes
        JLabel itemLabel = new JLabel("ItemName: " + petDetails[1]);
        itemLabel.setBounds(20, 20, 200, 30);
        cartPanel.add(itemLabel);

        JLabel priceLabel = new JLabel("Price: " + petDetails[4]);
        priceLabel.setBounds(20, 60, 200, 30);
        cartPanel.add(priceLabel);

        JLabel quantities = new JLabel("Quantity");
        quantities.setBounds(20, 100, 200, 30);
        cartPanel.add(quantities);

        quantityField = new JTextField();
        quantityField.setBounds(80, 100, 150, 30);
        cartPanel.add(quantityField);

        totalLabel = new JLabel("Total Price: Rs.0");
        totalLabel.setBounds(20, 140, 200, 30);
        cartPanel.add(totalLabel);

        JButton calculateButton = new JButton("Calculate price");
        calculateButton.setBounds(20, 180, 150, 30);
        cartPanel.add(calculateButton);

        JButton confirmButton = new JButton("ADD TO CART");
        confirmButton.setBounds(20, 220, 150, 30);
        cartPanel.add(confirmButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(20, 260, 100, 30); 
        cartPanel.add(backButton);

        // Return the panel for the controller to manage
        return cartPanel;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayMessage(String string, String string2, int errorMessage) {

        JOptionPane.showMessageDialog(null, errorMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
