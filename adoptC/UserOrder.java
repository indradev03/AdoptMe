import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserOrder {

    // List to hold order history
    private static final List<String[]> orderHistoryItems = new ArrayList<>();
    public static List<String[]> orderHistory = orderHistoryItems; // Ensure public orderHistory reflects orderHistoryItems

    // Method to add order details to the order history
    public static void add(String orderDetails) {
        // Generate order number (e.g., using current timestamp)
        String orderNumber = "ORD-" + System.currentTimeMillis();
        
        // Get current date for order placement
        String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        // Assume that an order starts with "Pending" status
        String orderStatus = "Pending";
        
        // Add order with additional info (order number, status, date)
        orderHistoryItems.add(new String[]{orderNumber, orderDetails, orderStatus, orderDate});
    }

    // Method to return the panel that displays the order history
    public static JPanel addToOrderHistory(String orderDetails) {
        JPanel orderHistoryPanel = new JPanel();
        orderHistoryPanel.setLayout(new BoxLayout(orderHistoryPanel, BoxLayout.Y_AXIS));
        orderHistoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        orderHistoryPanel.setBounds(260, 70, 600, 450);

        JLabel titleLabel = new JLabel("Order History");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        orderHistoryPanel.add(titleLabel);

        if (orderHistoryItems.isEmpty()) {
            JLabel emptyLabel = new JLabel("No orders placed yet.");
            emptyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            orderHistoryPanel.add(emptyLabel);
        } else {
            // Table column names for order history
            String[] columnNames = {"Order Number", "Order Details", "Status", "Date"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            // Populate the table with order history items
            for (String[] order : orderHistoryItems) {
                tableModel.addRow(order);
            }

            JTable orderTable = new JTable(tableModel) {
                // Override to adjust row height based on content
                public boolean isCellEditable(int row, int column) {
                    return false; // Make the table non-editable
                }

                @Override
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component comp = super.prepareRenderer(renderer, row, column);

                    // Adjust row height for order details
                    if (column == 1) {  // Order Details column
                        String cellValue = (String) getValueAt(row, column);
                        int lines = cellValue.split("\n").length;
                        int rowHeight = getFontMetrics(getFont()).getHeight() * lines;
                        setRowHeight(row, rowHeight); // Adjust height of the row
                    }
                    return comp;
                }
            };

            // Set up the table properties
            orderTable.setFont(new Font("Serif", Font.PLAIN, 16));
            orderTable.setRowHeight(25);
            orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN); // Allow last column to resize
            orderTable.setFillsViewportHeight(true);
            orderTable.setDefaultRenderer(Object.class, new WordWrapCellRenderer());

            // Create a scroll pane for the table to handle scrolling
            JScrollPane tableScrollPane = new JScrollPane(orderTable);
            tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            tableScrollPane.setPreferredSize(new Dimension(600, 400)); // Specify preferred size for the scrollable area
            orderHistoryPanel.add(tableScrollPane);
        }

        return orderHistoryPanel;
    }

    // Custom cell renderer to wrap text for order details column
    private static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        public WordWrapCellRenderer() {
            setWrapStyleWord(true);
            setLineWrap(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            int width = table.getColumnModel().getColumn(column).getWidth();
            setSize(width, getPreferredSize().height); // Ensure the text area size adjusts to content
            return this;
        }
    }
}
