package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.List.*;
import java.awt.event.*;

public class ManageOrderView extends JPanel {
    public JTable ordersTable;
    private DefaultTableModel tableModel;

    public ManageOrderView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBounds(250, 70, 650, 400);

        JLabel titleLabel = new JLabel("Manage Orders");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Table for displaying orders
        String[] columnNames = {"Receipt Number", "Item Name", "Quantity", "Total Price", "Payment Method", "Payment Amount", "Purchase Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ordersTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };

        ordersTable.setFont(new Font("Serif", Font.PLAIN, 16));
        ordersTable.setRowHeight(25);
        ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ordersTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        this.add(scrollPane);
    }

    // Method to populate the table with order data
    public void populateOrderTable(List<String[]> orderHistory) {
        for (String[] order : orderHistory) {
            tableModel.addRow(order);
        }
    }

    // Get selected order row
    public int getSelectedOrderRow() {
        return ordersTable.getSelectedRow();
    }

    // Clear the order table
    public void clearOrderTable() {
        tableModel.setRowCount(0);
    }

    public void populateOrderTable(List<String[]> orderHistory) {

    }

    public void populateOrderTable(List<String[]> orderHistory) {

    }
}
