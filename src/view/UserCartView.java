package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.model.UserCartModel;

import java.awt.*;

public class UserCartView {
    private JTable cartTable;

    public JPanel createCartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cartPanel.setBounds(260, 70, 600, 450);

        JLabel titleLabel = new JLabel("Cart Items");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        cartPanel.add(titleLabel);

        if (UserCartModel.cartItems.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty.");
            emptyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            cartPanel.add(emptyLabel);
        } else {
            // Create table model and JTable for displaying cart items
            String[] columnNames = {"ItemName", "Quantity", "Price", "Total Price"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (String[] item : UserCartModel.cartItems) {
                tableModel.addRow(item);
            }

            cartTable = new JTable(tableModel);
            cartTable.setFont(new Font("Serif", Font.PLAIN, 16));
            cartTable.setRowHeight(25);
            cartTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selection
            JScrollPane tableScrollPane = new JScrollPane(cartTable);
            cartPanel.add(tableScrollPane);
        }

        return cartPanel;  // Return the cart panel
    }

    public JTable getCartTable() {
        return cartTable;
    }

    public void displayBillDetails(String billDetails) {
        JOptionPane.showMessageDialog(null, billDetails, "Bill Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getPaymentMethodSelection() {
        String[] paymentMethods = {"Credit Card", "Digital Payment"};
        return (String) JOptionPane.showInputDialog(null,
                "Select a payment method:", "Payment Method",
                JOptionPane.QUESTION_MESSAGE, null, paymentMethods, paymentMethods[0]);
    }

    public double getPaymentAmount() {
        String paymentAmountString = JOptionPane.showInputDialog(null,
                "Enter amount to pay (Rs.):", "Payment Amount", JOptionPane.PLAIN_MESSAGE);
        return Double.parseDouble(paymentAmountString);
    }
}

