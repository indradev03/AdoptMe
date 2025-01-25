import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageOrders {

    private final JPanel ordersPanel;
    public static List<String[]> orderHistory = new ArrayList<>(); // Store order history
    private DefaultTableModel tableModel; // Declare tableModel here

    public ManageOrders(JFrame frame, String employeeUsername) {
        // Initialize the main panel
        ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        ordersPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ordersPanel.setBounds(250, 70, 650, 400);

        // Add a title label
        JLabel titleLabel = new JLabel("Manage Orders");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ordersPanel.add(titleLabel);

        // Add spacing between components
        ordersPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Fetch the order history from the database
        fetchOrderHistory();

        // Check if there are any orders to display
        if (orderHistory.isEmpty()) {
            JLabel emptyLabel = new JLabel("No orders available.");
            emptyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            ordersPanel.add(emptyLabel);
        } else {
            // Define table column names
            String[] columnNames = {"Receipt Number", "Item Name", "Quantity", "Total Price", "Payment Method", "Payment Amount", "Purchase Date"};

            // Create the table model and populate it with order data
            tableModel = new DefaultTableModel(columnNames, 0);
            for (String[] order : orderHistory) {
                tableModel.addRow(order);
            }

            // Create the table
            JTable ordersTable = new JTable(tableModel) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make the table non-editable
                }
            };

            // Set up table appearance
            ordersTable.setFont(new Font("Serif", Font.PLAIN, 16));
            ordersTable.setRowHeight(25);
            ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            ordersTable.setFillsViewportHeight(true);

            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(ordersTable);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            ordersPanel.add(scrollPane);

            // Add a listener for row selection
            ordersTable.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = ordersTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get selected order data
                        String receiptNumber = (String) ordersTable.getValueAt(selectedRow, 0);
                        String itemName = (String) ordersTable.getValueAt(selectedRow, 1);
                        String quantity = (String) ordersTable.getValueAt(selectedRow, 2);
                        String totalPrice = (String) ordersTable.getValueAt(selectedRow, 3);
                        String paymentMethod = (String) ordersTable.getValueAt(selectedRow, 4);
                        String paymentAmount = (String) ordersTable.getValueAt(selectedRow, 5);
                        String purchaseDate = (String) ordersTable.getValueAt(selectedRow, 6);

                        // Show confirmation dialog to manage the order
                        int response = JOptionPane.showConfirmDialog(frame, 
                            "Do you want to manage the selected order?\nReceipt Number: " + receiptNumber + "\nItem: " + itemName + "\nQuantity: " + quantity, 
                            "Manage Order", 
                            JOptionPane.YES_NO_OPTION);

                        if (response == JOptionPane.YES_OPTION) {
                            // Here you would implement the logic for placing or managing the order
                            // For now, simulate placing the order
                            JOptionPane.showMessageDialog(frame, "Order placed successfully!", "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

                            // Remove the placed order from the history list and table
                            removePlacedOrder(selectedRow);
                        }
                    }
                }
            });
        }
    }

    // Method to fetch order history from the database
    private void fetchOrderHistory() {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";
        String dbUser = "root";
        String dbPassword = "sarojkreya369#"; // Replace with your database password

        String selectQuery = "SELECT receipt_number, item_name, quantity, total_price, payment_method, payment_amount, purchase_date FROM order_history";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery)) {

            orderHistory.clear();  // Clear the current history before fetching new data

            while (resultSet.next()) {
                String receiptNumber = resultSet.getString("receipt_number");
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                double totalPrice = resultSet.getDouble("total_price");
                String paymentMethod = resultSet.getString("payment_method");
                double paymentAmount = resultSet.getDouble("payment_amount");
                Timestamp purchaseDate = resultSet.getTimestamp("purchase_date");

                // Add the order data to the orderHistory list
                orderHistory.add(new String[]{
                        receiptNumber,
                        itemName,
                        String.valueOf(quantity),
                        String.format("Rs.%.2f", totalPrice),
                        paymentMethod,
                        String.format("Rs.%.2f", paymentAmount),
                        purchaseDate.toString()
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately (e.g., show a message to the user)
            JOptionPane.showMessageDialog(null, "Error fetching order history from database", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to remove the placed order from the table and history list
    private void removePlacedOrder(int rowIndex) {
        // Remove the order from the orderHistory list
        orderHistory.remove(rowIndex);

        // Remove the order row from the table model
        tableModel.removeRow(rowIndex);
    }

    // Method to retrieve the orders panel
    public JPanel getOrdersPanel() {
        return ordersPanel;
    }
}
