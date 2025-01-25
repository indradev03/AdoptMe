package src.controller;

import javax.swing.*;

import src.main.UserDashboardPage;
import src.model.CartPageModel;
import src.model.UserCartModel;
import src.view.CartPageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartPageController {
    private CartPageModel model;
    private CartPageView view;

    public CartPageController(CartPageModel model, CartPageView view) {
        this.model = model;
        this.view = view;
    }

    public void initialize(JFrame frame, String[] petDetails, String loggedInUsername) {
        JPanel cartPanel = view.createCartPanel(petDetails);
        
        // Set initial values for the model based on pet details
        double unitPrice = Double.parseDouble(petDetails[4].replace("Rs.", ""));
        model = new CartPageModel(petDetails[1], unitPrice, 0); // Initialize model with item details

        // Calculate button action listener
        JButton calculateButton = (JButton) cartPanel.getComponent(5);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantity = Integer.parseInt(view.getQuantityField().getText());
                    model = new CartPageModel(petDetails[1], unitPrice, quantity);
                    double total = model.calculateTotalPrice();
                    view.getTotalLabel().setText("Total Price: Rs." + total);
                } catch (NumberFormatException ex) {
                    view.displayMessage("Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Confirm button action listener
        JButton confirmButton = (JButton) cartPanel.getComponent(6);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantity = Integer.parseInt(view.getQuantityField().getText());
                    model = new CartPageModel(petDetails[1], unitPrice, quantity);
                    @SuppressWarnings("unused")
                    double totalPrice = model.calculateTotalPrice();
                    UserCartModel.addItemToCart(petDetails[1], quantity, unitPrice);
                    view.displayMessage("Item added to cart successfully!");
                    frame.revalidate();
                    frame.repaint();
                } catch (NumberFormatException ex) {
                    view.displayMessage("Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back button action listener
        JButton backButton = (JButton) cartPanel.getComponent(7);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current page
                UserDashboardPage.main(new String[]{loggedInUsername}); // Go back to User Dashboard
            }
        });

        // Add the cart panel to the frame
        frame.add(cartPanel);
        frame.setSize(400, 350); // Set frame size to fit the components
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }
}

