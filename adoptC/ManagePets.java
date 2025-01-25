import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ManagePets {

    private JPanel petPanel;
    private JLabel imageLabel;

    // Database connection class
    static class DatabaseConnection {
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

    public ManagePets(JFrame frame, String AdminUsername) {
        petPanel = new JPanel();
        petPanel.setLayout(null);
        petPanel.setBackground(new Color(230, 230, 250));
        petPanel.setBounds(0, 0, 900, 550);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setBackground(new Color(187, 187, 255));
        backButton.addActionListener(e -> {
            frame.dispose();
            AdminDashboard.main(new String[]{AdminUsername});
        });
        petPanel.add(backButton);

        JLabel titleLabel = new JLabel("Manage Pets");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(350, 10, 300, 40);
        titleLabel.setForeground(new Color(72, 61, 139));
        petPanel.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 110, 100, 25);
        petPanel.add(nameLabel);

        JFormattedTextField nameField = new JFormattedTextField();
        nameField.setBounds(210, 110, 150, 25);
        petPanel.add(nameField);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(100, 150, 100, 25);
        petPanel.add(typeLabel);

        JFormattedTextField typeField = new JFormattedTextField();
        typeField.setBounds(210, 150, 150, 25);
        petPanel.add(typeField);

        JLabel speciesLabel = new JLabel("Species:");
        speciesLabel.setBounds(450, 110, 100, 25);
        petPanel.add(speciesLabel);

        JFormattedTextField speciesField = new JFormattedTextField();
        speciesField.setBounds(570, 110, 150, 25);
        petPanel.add(speciesField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(450, 150, 100, 25);
        petPanel.add(priceLabel);

        JFormattedTextField priceField = new JFormattedTextField();
        priceField.setBounds(570, 150, 150, 25);
        petPanel.add(priceField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(450, 190, 100, 25);
        petPanel.add(ageLabel);

        JFormattedTextField ageField = new JFormattedTextField();
        ageField.setBounds(570, 190, 150, 25);
        petPanel.add(ageField);

        JLabel adminIDLabel = new JLabel("Admin ID:");
        adminIDLabel.setBounds(100, 190, 100, 25);
        petPanel.add(adminIDLabel);

        JFormattedTextField adminIDField = new JFormattedTextField();
        adminIDField.setBounds(210, 190, 150, 25);
        petPanel.add(adminIDField);

        imageLabel = new JLabel();
        imageLabel.setBounds(450, 190, 100, 100);
        petPanel.add(imageLabel);

        JButton browseImageButton = new JButton("Browse Image");
        browseImageButton.setBounds(570, 230, 150, 25);
        petPanel.add(browseImageButton);

        JButton addButton = new JButton("ADD");
        addButton.setBounds(50, 270, 100, 30);
        addButton.setBackground(new Color(123, 104, 238));
        addButton.setForeground(Color.WHITE);
        petPanel.add(addButton);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(200, 270, 100, 30);
        editButton.setBackground(new Color(123, 104, 238));
        editButton.setForeground(Color.WHITE);
        petPanel.add(editButton);

        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(350, 270, 100, 30);
        removeButton.setBackground(new Color(123, 104, 238));
        removeButton.setForeground(Color.WHITE);
        petPanel.add(removeButton);

        JButton retrieveButton = new JButton("RETRIEVE");
        retrieveButton.setBounds(500, 270, 100, 30);
        retrieveButton.setBackground(new Color(123, 104, 238));
        retrieveButton.setForeground(Color.WHITE);
        petPanel.add(retrieveButton);

        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(650, 270, 100, 30);
        clearButton.setBackground(new Color(123, 104, 238));
        clearButton.setForeground(Color.WHITE);
        petPanel.add(clearButton);

        String[] tableColumns = {"PET ID", "PET NAME", "AGE", "SPECIES", "PRICE", "PET TYPE", "ADMIN ID"};
        DefaultTableModel tableModel = new DefaultTableModel(null, tableColumns);

        JTable petTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(petTable);
        scrollPane.setBounds(20, 320, 850, 150);
        petPanel.add(scrollPane);


        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String type = typeField.getText();
            String species = speciesField.getText();
            String price = priceField.getText();
            String age = ageField.getText();
            String adminID = adminIDField.getText();
            String imagePath = "";  // Placeholder for image path, to be set later if necessary

            // Check if any field is empty
            if (name.isEmpty() || type.isEmpty() || species.isEmpty() || price.isEmpty() || age.isEmpty() || adminID.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Parse price and age to validate they are numbers
                double parsedPrice = Double.parseDouble(price);
                int parsedAge = Integer.parseInt(age);

                // Check if pet with the same name, type, and species already exists
                try (Connection connection = DatabaseConnection.getConnection()) {
                    // Debugging: Check if connection is successful
                    if (connection == null) {
                        JOptionPane.showMessageDialog(frame, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String checkQuery = "SELECT COUNT(*) FROM ManagePets WHERE name = ? AND type = ? AND species = ?";
                    try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                        checkStmt.setString(1, name);
                        checkStmt.setString(2, type);
                        checkStmt.setString(3, species);

                        ResultSet rs = checkStmt.executeQuery();
                        rs.next();
                        if (rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(frame, "A pet with this name, type, and species already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Insert new pet into the database
                        String query = "INSERT INTO ManagePets (name, type, species, price, age, admin_id, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                            stmt.setString(1, name);
                            stmt.setString(2, type);
                            stmt.setString(3, species);
                            stmt.setDouble(4, parsedPrice);
                            stmt.setInt(5, parsedAge);
                            stmt.setString(6, adminID);
                            stmt.setString(7, imagePath); // Handle image path separately if required
                            stmt.executeUpdate();
                        }

                        // Success message
                        JOptionPane.showMessageDialog(frame, "Pet added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Add the pet to the table if necessary
                        tableModel.addRow(new Object[]{
                            name, type, species, price, age, adminID, imagePath
                        });

                        // Clear all the fields after adding the pet
                        nameField.setText("");
                        typeField.setText("");
                        speciesField.setText("");
                        priceField.setText("");
                        ageField.setText("");
                        adminIDField.setText("");

                    } catch (SQLException ex) {
                        // Improved error handling with exception stack trace
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error adding pet: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                // Specific error handling for price and age fields
                JOptionPane.showMessageDialog(frame, "Price and age must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                // General SQL Exception handling
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        editButton.addActionListener(e -> {
            // Step 1: Ensure a row is selected
            int selectedRow = petTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Step 2: Fetch the pet details from the selected row
            int petID = (int) tableModel.getValueAt(selectedRow, 0);
        
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM ManagePets WHERE pet_id=?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, petID);
                    ResultSet rs = stmt.executeQuery();
        
                    if (rs.next()) {
                        // Populate the text fields with the existing pet details
                        nameField.setText(rs.getString("name"));
                        typeField.setText(rs.getString("type"));
                        speciesField.setText(rs.getString("species"));
                        priceField.setText(String.valueOf(rs.getDouble("price")));
                        ageField.setText(String.valueOf(rs.getInt("age")));
                        adminIDField.setText(rs.getString("admin_id"));
                    } else {
                        JOptionPane.showMessageDialog(frame, "Pet not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error fetching pet details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // No update action is performed here, just populating fields with existing data.
        });
        
        

        removeButton.addActionListener(e -> {
            int selectedRow = petTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a pet to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Confirm with the user before removal
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to remove this pet?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    int petID = (int) tableModel.getValueAt(selectedRow, 0);
                    try (Connection conn = DatabaseConnection.getConnection()) {
                        String query = "DELETE FROM ManagePets WHERE pet_id=?";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setInt(1, petID);
                            stmt.executeUpdate();
                        }
                    }
        
                    // Remove the row from the table after deletion
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(frame, "Pet removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error removing pet: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        retrieveButton.addActionListener(e -> {
            // Clear existing data in the table
            tableModel.setRowCount(0);
        
            // Fetch data from database
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM ManagePets";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    ResultSet rs = stmt.executeQuery();
        
                    // Process the result set and populate the table
                    while (rs.next()) {
                        Object[] row = new Object[]{
                            rs.getInt("pet_id"), 
                            rs.getString("name"), 
                            rs.getInt("age"),
                            rs.getString("species"), 
                            rs.getDouble("price"), 
                            rs.getString("type"),
                            rs.getString("admin_id")
                        };
                        tableModel.addRow(row);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Clear functionality
        clearButton.addActionListener(e -> {
            nameField.setText("");
            typeField.setText("");
            speciesField.setText("");
            priceField.setText("");
            ageField.setText("");
            adminIDField.setText("");
            imageLabel.setIcon(null);
        });

        browseImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a Pet Image");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                ImageIcon icon = new ImageIcon(filePath);
                Image image = icon.getImage();
                Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                imageLabel.setIcon(resizedIcon);
            }
        });

        frame.add(petPanel);
        frame.setSize(900, 550);
        frame.setVisible(true);
    }

    public Component getPetPanel() {
            return imageLabel;

    }
}
