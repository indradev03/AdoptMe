package src.view;

// PetView.java - This will handle the UI and display the components.

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ManagePetView extends JPanel {
    private JTextField nameField, typeField, speciesField, priceField, ageField, adminIDField;
    private JLabel imageLabel;
    private JTable petTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, removeButton, retrieveButton, clearButton, browseImageButton;

    public ManagePetView() {
        setLayout(null);
        setBackground(new Color(230, 230, 250));
        setBounds(0, 0, 900, 550);

        // UI Components initialization
        JLabel titleLabel = new JLabel("Manage Pets");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(350, 10, 300, 40);
        titleLabel.setForeground(new Color(72, 61, 139));
        add(titleLabel);

        // Labels and fields
        addComponent(new JLabel("Name:"), 100, 110);
        nameField = new JTextField();
        addField(nameField, 210, 110);

        addComponent(new JLabel("Type:"), 100, 150);
        typeField = new JTextField();
        addField(typeField, 210, 150);

        addComponent(new JLabel("Species:"), 450, 110);
        speciesField = new JTextField();
        addField(speciesField, 570, 110);

        addComponent(new JLabel("Price:"), 450, 150);
        priceField = new JTextField();
        addField(priceField, 570, 150);

        addComponent(new JLabel("Age:"), 450, 190);
        ageField = new JTextField();
        addField(ageField, 570, 190);

        addComponent(new JLabel("Admin ID:"), 100, 190);
        adminIDField = new JTextField();
        addField(adminIDField, 210, 190);

        imageLabel = new JLabel();
        imageLabel.setBounds(450, 190, 100, 100);
        add(imageLabel);

        browseImageButton = new JButton("Browse Image");
        browseImageButton.setBounds(570, 230, 150, 25);
        add(browseImageButton);

        addButton = new JButton("ADD");
        addButton.setBounds(50, 270, 100, 30);
        add(addButton);

        editButton = new JButton("EDIT");
        editButton.setBounds(200, 270, 100, 30);
        add(editButton);

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(350, 270, 100, 30);
        add(removeButton);

        retrieveButton = new JButton("RETRIEVE");
        retrieveButton.setBounds(500, 270, 100, 30);
        add(retrieveButton);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(650, 270, 100, 30);
        add(clearButton);

        String[] tableColumns = {"PET ID", "PET NAME", "AGE", "SPECIES", "PRICE", "PET TYPE", "ADMIN ID"};
        tableModel = new DefaultTableModel(null, tableColumns);
        petTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(petTable);
        scrollPane.setBounds(20, 320, 850, 150);
        add(scrollPane);
    }

    private void addComponent(JComponent component, int x, int y) {
        component.setBounds(x, y, 100, 25);
        add(component);
    }

    private void addField(JTextField field, int x, int y) {
        field.setBounds(x, y, 150, 25);
        add(field);
    }

    // Getter methods for the fields and buttons
    public JTextField getNameField() { return nameField; }
    public JTextField getTypeField() { return typeField; }
    public JTextField getSpeciesField() { return speciesField; }
    public JTextField getPriceField() { return priceField; }
    public JTextField getAgeField() { return ageField; }
    public JTextField getAdminIDField() { return adminIDField; }
    public JTable getPetTable() { return petTable; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getAddButton() { return addButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getRemoveButton() { return removeButton; }
    public JButton getRetrieveButton() { return retrieveButton; }
    public JButton getClearButton() { return clearButton; }
    public JButton getBrowseImageButton() { return browseImageButton; }
    public JLabel getImageLabel() { return imageLabel; }
}

