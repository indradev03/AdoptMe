package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ManageCustomersView {
    private JPanel panel;
    private JTable customersTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;

    public ManageCustomersView() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBounds(260, 70, 600, 450);

        JLabel label = new JLabel("Customer Details");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] columnNames = {"Customer ID", "Name", "Email", "Phone", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        customersTable = new JTable(tableModel);
        customersTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(customersTable);
        panel.add(scrollPane);

        deleteButton = new JButton("Delete Customer");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(deleteButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTable getCustomersTable() {
        return customersTable;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void updateCustomerTable(Object[][] data) {
        tableModel.setRowCount(0);  // Clear the existing rows
        for (Object[] row : data) {
            tableModel.addRow(row);  // Add each row of data
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(panel, message);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(panel, message);
    }
}

