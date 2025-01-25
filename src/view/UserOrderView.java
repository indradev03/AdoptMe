package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import src.model.UserOrderModel;

import java.awt.*;

public class UserOrderView {
    private JPanel orderHistoryPanel;

    public UserOrderView() {
        orderHistoryPanel = new JPanel();
        orderHistoryPanel.setLayout(new BoxLayout(orderHistoryPanel, BoxLayout.Y_AXIS));
        orderHistoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    // Method to create and return the order history panel
    public JPanel createOrderHistoryPanel() {
        orderHistoryPanel.removeAll();
        
        JLabel titleLabel = new JLabel("Order History");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        orderHistoryPanel.add(titleLabel);

        if (UserOrderModel.getOrderHistory().isEmpty()) {
            JLabel emptyLabel = new JLabel("No orders placed yet.");
            emptyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            orderHistoryPanel.add(emptyLabel);
        } else {
            // Table column names for order history
            String[] columnNames = {"Order Number", "Order Details", "Status", "Date"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            // Populate the table with order history items
            for (String[] order : UserOrderModel.getOrderHistory()) {
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

