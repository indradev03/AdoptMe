package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import src.main.AdminDashboardApp;
import java.awt.*;

public class ManageSuppliesView {
    private JPanel suppliesPanel;
    private JTable suppliesTable;
    private DefaultTableModel tableModel;
    private JTextField supplyIDField, nameField, typeField, quantityField, priceField, adminIDField;
    private JLabel lblImage;

    public ManageSuppliesView() {
        // Create the main panel
        suppliesPanel = new JPanel();
        suppliesPanel.setLayout(null);
        suppliesPanel.setBackground(new Color(230, 230, 250)); // Light purple background
        suppliesPanel.setBounds(0, 0, 900, 550);

        // Back button
        JButton backButton = new JButton("BACK");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setBackground(new Color(187, 187, 255));
        backButton.addActionListener(_ -> {
            Frame.dispose(); // Close the dashboard
            AdminDashboardApp.main(new String[]{AdminUsername});  // Reinitialize the UserDashboard with the logged-in username
        });
        suppliesPanel.add(backButton);

        // Title label
        JLabel titleLabel = new JLabel("Manage Supplies");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(350, 10, 300, 40);
        titleLabel.setForeground(new Color(72, 61, 139));
        suppliesPanel.add(titleLabel);

        // Labels and individual text fields
        JLabel supplyIDLabel = new JLabel("Supply ID:");
        supplyIDLabel.setBounds(100, 70, 100, 25);
        suppliesPanel.add(supplyIDLabel);

        supplyIDField = new JTextField();
        supplyIDField.setBounds(210, 70, 150, 25);
        suppliesPanel.add(supplyIDField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 110, 100, 25);
        suppliesPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(210, 110, 150, 25);
        suppliesPanel.add(nameField);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(100, 150, 100, 25);
        suppliesPanel.add(typeLabel);

        typeField = new JTextField();
        typeField.setBounds(210, 150, 150, 25);
        suppliesPanel.add(typeField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(450, 70, 100, 25);
        suppliesPanel.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(570, 70, 150, 25);
        suppliesPanel.add(quantityField);

        JLabel priceLabel = new JLabel("Price/unit:");
        priceLabel.setBounds(450, 110, 100, 25);
        suppliesPanel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(570, 110, 150, 25);
        suppliesPanel.add(priceField);

        JLabel adminIDLabel = new JLabel("Admin ID:");
        adminIDLabel.setBounds(100, 190, 100, 25);
        suppliesPanel.add(adminIDLabel);

        adminIDField = new JTextField();
        adminIDField.setBounds(210, 190, 150, 25);
        suppliesPanel.add(adminIDField);

        // Image (Initially empty or placeholder)
        lblImage = new JLabel();
        lblImage.setBounds(450, 190, 100, 100);
        suppliesPanel.add(lblImage);

        JButton browseImageButton = new JButton("Browse Image");
        browseImageButton.setBounds(570, 220, 150, 25);
        suppliesPanel.add(browseImageButton);

        // Buttons
        JButton addButton = new JButton("ADD");
        addButton.setBounds(50, 270, 100, 30);
        addButton.setBackground(new Color(123, 104, 238));
        addButton.setForeground(Color.WHITE);
        suppliesPanel.add(addButton);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(200, 270, 100, 30);
        editButton.setBackground(new Color(123, 104, 238));
        editButton.setForeground(Color.WHITE);
        suppliesPanel.add(editButton);

        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(350, 270, 100, 30);
        removeButton.setBackground(new Color(123, 104, 238));
        removeButton.setForeground(Color.WHITE);
        suppliesPanel.add(removeButton);

        JButton retrieveButton = new JButton("RETRIEVE");
        retrieveButton.setBounds(500, 270, 100, 30);
        retrieveButton.setBackground(new Color(123, 104, 238));
        retrieveButton.setForeground(Color.WHITE);
        suppliesPanel.add(retrieveButton);

        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(650, 270, 100, 30);
        clearButton.setBackground(new Color(123, 104, 238));
        clearButton.setForeground(Color.WHITE);
        suppliesPanel.add(clearButton);

        // Initialize the table model
        String[] tableColumns = {"SUPPLY ID", "NAME", "TYPE", "QUANTITY", "PRICE", "ADMIN ID"};
        tableModel = new DefaultTableModel(null, tableColumns);

        suppliesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(suppliesTable);
        scrollPane.setBounds(20, 350, 850, 150);
        suppliesPanel.add(scrollPane);
    }

    // Getters for fields and buttons
    public JTextField getSupplyIDField() { return supplyIDField; }
    public JTextField getNameField() { return nameField; }
    public JTextField getTypeField() { return typeField; }
    public JTextField getQuantityField() { return quantityField; }
    public JTextField getPriceField() { return priceField; }
    public JTextField getAdminIDField() { return adminIDField; }
    public JTable getSuppliesTable() { return suppliesTable; }
    public JPanel getSuppliesPanel() { return suppliesPanel; }
    public JLabel getLblImage() { return lblImage; }

    public JTextComponent getSearchField() {
    }
    public JTextField getAddButton() {
    }
    public JTextField getEditButton() {
    }
    public JTextField getRemoveButton() {
    }
    public JTextField getRetrieveButton() {
    }

    public JTextField getClearButton() {
    }

    public JTextField getSearchButton() {
    }
}
