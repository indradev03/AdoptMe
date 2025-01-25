import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCart {
    public static final List<String[]> cartItems = new ArrayList<>(); // Shared cart items list
    private static int receiptCounter = 1000; // Initial receipt number, incremented for each purchase
    private JTable cartTable;
    
    // Method to add items to the cart
    public static void addToCart(String itemName, int quantity, double unitPrice) {
        double totalPrice = quantity * unitPrice;
        String[] cartItem = {itemName, String.valueOf(quantity), String.valueOf(unitPrice), String.format("Rs.%.2f", totalPrice)};
        cartItems.add(cartItem);
    }

    // Method to refresh the table model with the updated cart items
    private void refreshTable() {
        DefaultTableModel tableModel = (DefaultTableModel) cartTable.getModel();
        tableModel.setRowCount(0); // Clear the existing rows

        for (String[] item : cartItems) {
            tableModel.addRow(item); // Add updated cart items to the table
        }
    }

    public Component getCartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cartPanel.setBounds(260, 70, 600, 450);

        JLabel titleLabel = new JLabel("Cart Items");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        cartPanel.add(titleLabel);

        if (cartItems.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty.");
            emptyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            cartPanel.add(emptyLabel);
        } else {
            // Create table model and JTable for displaying cart items
            String[] columnNames = {"ItemName", "Quantity", "Price", "Total Price"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (String[] item : cartItems) {
                tableModel.addRow(item);
            }

            cartTable = new JTable(tableModel);
            cartTable.setFont(new Font("Serif", Font.PLAIN, 16));
            cartTable.setRowHeight(25);
            cartTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selection
            JScrollPane tableScrollPane = new JScrollPane(cartTable);
            cartPanel.add(tableScrollPane);

            // Add a "Purchase" button
            JButton purchaseButton = new JButton("Purchase");
            purchaseButton.setFont(new Font("Serif", Font.PLAIN, 16));
            purchaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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

                            // Add to total amount (remove "Rs." prefix to parse as a number)
                            totalAmount += Double.parseDouble(totalPrice.replace("Rs.", "").trim());
                        }

                        billDetails.append("\nTotal Amount: Rs.").append(String.format("%.2f", totalAmount));

                        // Show bill details
                        JOptionPane.showMessageDialog(cartPanel, billDetails.toString(), "Bill Summary", JOptionPane.INFORMATION_MESSAGE);

                        // Now, process the payment with the bill details included
                        processPayment(totalAmount, billDetails.toString(), selectedRows);
                    } else {
                        JOptionPane.showMessageDialog(cartPanel, "Please select at least one item to purchase.");
                    }
                }
            });

            cartPanel.add(purchaseButton);
        }

        return cartPanel;  // Return the cart panel
    }

    private void processPayment(double totalAmount, String billDetails, int[] selectedRows) {
        // Payment method selection (unchanged)
        String[] paymentMethods = { "Credit Card", "Digital Payment"};
        String paymentMethod = (String) JOptionPane.showInputDialog(null, 
                "Select a payment method:", "Payment Method",
                JOptionPane.QUESTION_MESSAGE, null, paymentMethods, paymentMethods[0]);
    
        if (paymentMethod != null) {
            String paymentSummary = billDetails + "\n\nTotal Amount to Pay: Rs." + String.format("%.2f", totalAmount)
                    + "\n\nSelected Payment Method: " + paymentMethod;
    
            int option = JOptionPane.showConfirmDialog(null, paymentSummary + "\n\nProceed with payment?", 
                    "Confirm Payment", JOptionPane.YES_NO_OPTION);
    
            if (option == JOptionPane.YES_OPTION) {
                String paymentAmountString = JOptionPane.showInputDialog(null, 
                        "Enter amount to pay (Rs.):", "Payment Amount", JOptionPane.PLAIN_MESSAGE);
    
                if (paymentAmountString != null && !paymentAmountString.isEmpty()) {
                    try {
                        double paymentAmount = Double.parseDouble(paymentAmountString);
    
                        if (paymentAmount >= totalAmount) {
                            double change = paymentAmount - totalAmount;
                            String receiptNumber = "REC-" + receiptCounter++;
    
                            // Save to database
                            savePurchaseToDatabase(receiptNumber, paymentMethod, totalAmount, paymentAmount, change, selectedRows);
    
                            // Display payment success details
                            JOptionPane.showMessageDialog(null, "Payment Successful.\nReceipt Number: " + receiptNumber, 
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
    
                            // Save the purchase to the order history (Add this line)
                            String orderDetails = "Order Details:\n" + billDetails + "\nPayment Method: " + paymentMethod;
                            UserOrder.add(orderDetails);  // Add the order to history
    
                            // Remove items from the cart
                            for (int i = selectedRows.length - 1; i >= 0; i--) {
                                cartItems.remove(selectedRows[i]);
                            }
    
                            refreshTable();
                        } else {
                            JOptionPane.showMessageDialog(null, 
                                    "Insufficient funds. Please enter a valid amount.", 
                                    "Payment Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, 
                                "Invalid amount entered. Please enter a valid number.", 
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    
    
    private void savePurchaseToDatabase(String receiptNumber, String paymentMethod, double totalAmount, double paymentAmount, double change, int[] selectedRows) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";
        String dbUser = "root";
        String dbPassword = "sarojkreya369#"; // Replace with your database password
    
        String insertQuery = "INSERT INTO order_history (receipt_number, item_name, quantity, total_price, payment_method, payment_amount, change_returned, purchase_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            
            // If the connection isn't set to auto-commit, you need to manually commit it.
            connection.setAutoCommit(false);
    
            for (int rowIndex : selectedRows) {
                String itemName = (String) cartTable.getValueAt(rowIndex, 0);
                int quantity = Integer.parseInt((String) cartTable.getValueAt(rowIndex, 1));
                double totalPrice = Double.parseDouble(((String) cartTable.getValueAt(rowIndex, 3)).replace("Rs.", "").trim());
    
                preparedStatement.setString(1, receiptNumber);
                preparedStatement.setString(2, itemName);
                preparedStatement.setInt(3, quantity);
                preparedStatement.setDouble(4, totalPrice);
                preparedStatement.setString(5, paymentMethod);
                preparedStatement.setDouble(6, paymentAmount);
                preparedStatement.setDouble(7, change);
                preparedStatement.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis())); // Current timestamp
    
                preparedStatement.addBatch();
            }
    
            // Execute the batch
            int[] rowsAffected = preparedStatement.executeBatch();
            connection.commit();  // Commit the transaction
    
            System.out.println("Rows affected: " + rowsAffected.length);  // For debugging
    
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save order details to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}    