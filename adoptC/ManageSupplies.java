import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageSupplies {

    private JPanel suppliesPanel;
    private JLabel lblImage;
    private DefaultTableModel tableModel;

    // Database connection class
    class DatabaseConnection {
        private static final String URL = "jdbc:mysql://127.0.0.1:3306/Signup"; // Modify with your database URL
        private static final String USER = "root"; // Your MySQL username
        private static final String PASSWORD = "sarojkreya369#"; // Your MySQL password

        public static Connection getConnection() throws SQLException {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Failed to connect to the database.", e);
            }
        }
    }


    public ManageSupplies(JFrame frame, String AdminUsername) {
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
            frame.dispose(); // Close the dashboard
            AdminDashboard.main(new String[]{AdminUsername});  // Reinitialize the UserDashboard with the logged-in username
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

        JFormattedTextField supplyIDField = new JFormattedTextField();
        supplyIDField.setBounds(210, 70, 150, 25);
        suppliesPanel.add(supplyIDField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 110, 100, 25);
        suppliesPanel.add(nameLabel);

        JFormattedTextField nameField = new JFormattedTextField();
        nameField.setBounds(210, 110, 150, 25);
        suppliesPanel.add(nameField);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(100, 150, 100, 25);
        suppliesPanel.add(typeLabel);

        JFormattedTextField typeField = new JFormattedTextField();
        typeField.setBounds(210, 150, 150, 25);
        suppliesPanel.add(typeField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(450, 70, 100, 25);
        suppliesPanel.add(quantityLabel);

        JFormattedTextField quantityField = new JFormattedTextField();
        quantityField.setBounds(570, 70, 150, 25);
        suppliesPanel.add(quantityField);

        JLabel priceLabel = new JLabel("Price/unit:");
        priceLabel.setBounds(450, 110, 100, 25);
        suppliesPanel.add(priceLabel);

        JFormattedTextField priceField = new JFormattedTextField();
        priceField.setBounds(570, 110, 150, 25);
        suppliesPanel.add(priceField);

        JLabel adminIDLabel = new JLabel("Admin ID:");
        adminIDLabel.setBounds(100, 190, 100, 25);
        suppliesPanel.add(adminIDLabel);

        JFormattedTextField adminIDField = new JFormattedTextField();
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

        // Supply Table
        String[] tableColumns = {"SUPPLY ID", "NAME", "TYPE", "QUANTITY", "PRICE", "ADMIN ID"};
        tableModel = new DefaultTableModel(null, tableColumns);

        JTable suppliesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(suppliesTable);
        scrollPane.setBounds(20, 350, 850, 150);
        suppliesPanel.add(scrollPane);

        // Button functionality for Add button
        addButton.addActionListener(e -> {
            String supplyId = supplyIDField.getText();
            String name = nameField.getText();
            String type = typeField.getText();
            String quantity = quantityField.getText();
            String price = priceField.getText();
            String adminId = adminIDField.getText();

            // Validate input
            if (supplyId.isEmpty() || name.isEmpty() || type.isEmpty() || quantity.isEmpty() || price.isEmpty() || adminId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled in.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection connection = DatabaseConnection.getConnection()) {
                // Check if Supply ID already exists (optional validation, depending on your requirements)
                String checkQuery = "SELECT COUNT(*) FROM supplies WHERE supply_id = ?";
                PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
                checkStmt.setString(1, supplyId);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(frame, "Supply ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Insert new supply into the database
                String query = "INSERT INTO supplies (supply_id, name, type, quantity, price, admin_id) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, supplyId);
                statement.setString(2, name);
                statement.setString(3, type);
                statement.setInt(4, Integer.parseInt(quantity)); // Assuming quantity is an integer
                statement.setDouble(5, Double.parseDouble(price)); // Assuming price is a double
                statement.setString(6, adminId);
                statement.executeUpdate();

                // Show success message
                JOptionPane.showMessageDialog(frame, "Supply added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Add data to table
                tableModel.addRow(new Object[]{
                        supplyId, name, type, quantity, price, adminId
                });

                // Clear input fields
                supplyIDField.setText("");
                nameField.setText("");
                typeField.setText("");
                quantityField.setText("");
                priceField.setText("");
                adminIDField.setText("");
                lblImage.setIcon(null); // Clear image selection (if applicable)
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error adding supply: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeButton.addActionListener(e -> {
            int selectedRow = suppliesTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String supplyId = suppliesTable.getValueAt(selectedRow, 0).toString(); // Assuming first column contains supply_id
            try (Connection connection = DatabaseConnection.getConnection()) {
                String deleteQuery = "DELETE FROM supplies WHERE supply_id = ?";
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setString(1, supplyId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Supply deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error deleting supply.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error deleting supply: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        

        editButton.addActionListener(e -> {
            int selectedRow = suppliesTable.getSelectedRow();
        
            // Step 1: Ensure a row is selected
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Step 2: Restore row data to text fields if not already populated
            if (supplyIDField.getText().isEmpty()) {
                // Populate text fields with selected row data
                supplyIDField.setText((String) tableModel.getValueAt(selectedRow, 0));
                nameField.setText((String) tableModel.getValueAt(selectedRow, 1));
                typeField.setText((String) tableModel.getValueAt(selectedRow, 2));
                quantityField.setText((String) tableModel.getValueAt(selectedRow, 3));
                priceField.setText((String) tableModel.getValueAt(selectedRow, 4));
                adminIDField.setText((String) tableModel.getValueAt(selectedRow, 5));
                JOptionPane.showMessageDialog(frame, "Data restored to fields. Edit the fields and click 'Edit' again to save.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        
            // Step 3: Save the updates to the database and table
            try (Connection connection = DatabaseConnection.getConnection()) {
                // Retrieve updated values from text fields
                String supplyID = supplyIDField.getText();
                String name = nameField.getText();
                String type = typeField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                String adminID = adminIDField.getText();
        
                // Validate inputs
                if (supplyID.isEmpty() || name.isEmpty() || type.isEmpty() || adminID.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // SQL query to update the record
                String query = "UPDATE supplies SET name = ?, type = ?, quantity = ?, price = ?, admin_id = ? WHERE supply_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
        
                // Set query parameters
                statement.setString(1, name);
                statement.setString(2, type);
                statement.setInt(3, quantity);
                statement.setDouble(4, price);
                statement.setString(5, adminID);
                statement.setString(6, supplyID);
        
                // Execute update
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame, "Supply updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
                    // Update table row with new values
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(type, selectedRow, 2);
                    tableModel.setValueAt(quantity, selectedRow, 3);
                    tableModel.setValueAt(price, selectedRow, 4);
                    tableModel.setValueAt(adminID, selectedRow, 5);
        
                    // Clear fields after editing
                    clearFields(supplyIDField, nameField, typeField, quantityField, priceField, adminIDField);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: No matching supply found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error updating supply: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button functionality for Clear button
        clearButton.addActionListener(e -> {
            clearFields(supplyIDField, nameField, typeField, quantityField, priceField, adminIDField);
        });

        // Button functionality for Retrieve button
        retrieveButton.addActionListener(e -> {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM supplies";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                // Clear existing table data
                tableModel.setRowCount(0);

                // Populate table with database data
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getString("supply_id"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getInt("quantity"),
                            rs.getDouble("price"),
                            rs.getString("admin_id")
                    });
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error retrieving supplies: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Browse image action
        browseImageButton.addActionListener(_ -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a Supply Image");

            // Filter for image files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();

                // Load the image
                ImageIcon icon = new ImageIcon(filePath);

                // Resize the image to fit the JLabel
                Image image = icon.getImage();
                Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);  // Resize to fit the JLabel (100x100)
                ImageIcon resizedIcon = new ImageIcon(resizedImage);

                // Set the resized image as the icon for the JLabel
                lblImage.setIcon(resizedIcon);
            }
        });

        frame.add(suppliesPanel);
        frame.setSize(900, 550);
        frame.setVisible(true);
    }

    // Helper method to clear fields
    private void clearFields(JFormattedTextField supplyIDField, JFormattedTextField nameField, JFormattedTextField typeField,
        JFormattedTextField quantityField, JFormattedTextField priceField, JFormattedTextField adminIDField) {
        supplyIDField.setText("");
        nameField.setText("");
        typeField.setText("");
        quantityField.setText("");
        priceField.setText("");
        adminIDField.setText("");
        lblImage.setIcon(null); // Clear image selection
    }

    public JPanel getSuppliesPanel() {
        return suppliesPanel;
    }
}