package src.controller;

import javax.swing.*;

import src.model.UserCartModel;
import src.view.UserCartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCartController {
    private UserCartModel model;
    private UserCartView view;

    public UserCartController(UserCartModel model, UserCartView view) {
        this.model = model;
        this.view = view;
    }

    public void initialize() {
        JPanel cartPanel = view.createCartPanel();
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setFont(new Font("Serif", Font.PLAIN, 16));
        
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable cartTable = view.getCartTable();
                int[] selectedRows = cartTable.getSelectedRows();
                
                if (selectedRows.length > 0) {
                    StringBuilder billDetails = new StringBuilder("Bill Details:\n");
                    double totalAmount = 0.0;

                    // Process each selected item and generate the bill
                    for (int rowIndex : selectedRows) {
                        String itemName = (String) cartTable.getValueAt(rowIndex, 0);
                        String quantity = (String) cartTable.getValueAt(rowIndex, 1);
                        String totalPrice = (String) cartTable.getValueAt(rowIndex, 3);
                        billDetails.append(itemName).append(" (Quantity: ").append(quantity)
                                .append(", Total: ").append(totalPrice).append(")\n");

                        totalAmount += Double.parseDouble(totalPrice.replace("Rs.", "").trim());
                    }

                    billDetails.append("\nTotal Amount: Rs.").append(String.format("%.2f", totalAmount));
                    view.displayBillDetails(billDetails.toString());

                    // Now, process the payment with the bill details included
                    processPayment(totalAmount, billDetails.toString(), selectedRows);
                } else {
                    view.displayMessage("Please select at least one item to purchase.");
                }
            }
        });

        cartPanel.add(purchaseButton);
    }

    private void processPayment(double totalAmount, String billDetails, int[] selectedRows) {
        String paymentMethod = view.getPaymentMethodSelection();

        if (paymentMethod != null) {
            String paymentSummary = billDetails + "\n\nTotal Amount to Pay: Rs." + String.format("%.2f", totalAmount)
                    + "\n\nSelected Payment Method: " + paymentMethod;

            int option = JOptionPane.showConfirmDialog(null, paymentSummary + "\n\nProceed with payment?",
                    "Confirm Payment", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                double paymentAmount = view.getPaymentAmount();

                if (paymentAmount >= totalAmount) {
                    double change = paymentAmount - totalAmount;
                    String receiptNumber = "REC-" + (1000); // Increment this as per your receipt logic

                    // Save purchase to the database
                    model.savePurchaseToDatabase(receiptNumber, paymentMethod, totalAmount, paymentAmount, change, selectedRows);

                    view.displayMessage("Payment Successful.\nReceipt Number: " + receiptNumber);

                    // Remove items from the cart
                    model.removeItemsFromCart(selectedRows);
                } else {
                    view.displayMessage("Insufficient funds. Please enter a valid amount.");
                }
            }
        }
    }
}
