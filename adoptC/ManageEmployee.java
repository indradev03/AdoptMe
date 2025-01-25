import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ManageEmployee {

    private JPanel employeePanel;

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

    public ManageEmployee(JFrame frame, String adminUsername) {
        // Create the main panel
        employeePanel = new JPanel();
        employeePanel.setLayout(null);
        employeePanel.setBackground(new Color(230, 230, 250)); // Light purple background
        employeePanel.setBounds(0, 0, 900, 550);

        // Back button
        JButton backButton = new JButton("BACK");
        backButton.setBounds(10, 10, 80, 30);
        backButton.setBackground(new Color(187, 187, 255));
        backButton.addActionListener(_ -> {
            frame.dispose(); // Close the current frame
            AdminDashboard.main(new String[]{adminUsername});  // Return to Admin Dashboard
        });
        employeePanel.add(backButton);

        // Title label
        JLabel titleLabel = new JLabel("Manage Employee");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(350, 10, 300, 40);
        titleLabel.setForeground(new Color(72, 61, 139));
        employeePanel.add(titleLabel);

        // Labels and individual text fields
        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setBounds(100, 70, 100, 25);
        employeePanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(210, 70, 150, 25);
        employeePanel.add(idField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(100, 110, 100, 25);
        employeePanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(210, 110, 150, 25);
        employeePanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(100, 150, 100, 25);
        employeePanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(210, 150, 150, 25);
        employeePanel.add(lastNameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(100, 190, 100, 25);
        employeePanel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(210, 190, 150, 25);
        employeePanel.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(100, 230, 100, 25);
        employeePanel.add(genderLabel);

        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setBounds(210, 230, 150, 25);
        employeePanel.add(genderComboBox);

        JLabel usernameLabel = new JLabel("User Name:");
        usernameLabel.setBounds(450, 70, 100, 25);
        employeePanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(570, 70, 150, 25);
        employeePanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(450, 110, 100, 25);
        employeePanel.add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(570, 110, 150, 25);
        employeePanel.add(passwordField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(450, 150, 100, 25);
        employeePanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(570, 150, 150, 25);
        employeePanel.add(phoneField);

        JLabel workingHoursLabel = new JLabel("Working Hours:");
        workingHoursLabel.setBounds(450, 190, 120, 25);
        employeePanel.add(workingHoursLabel);

        JTextField workingHoursField = new JTextField();
        workingHoursField.setBounds(570, 190, 150, 25);
        employeePanel.add(workingHoursField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(450, 230, 100, 25);
        employeePanel.add(salaryLabel);

        JTextField salaryField = new JTextField();
        salaryField.setBounds(570, 230, 150, 25);
        employeePanel.add(salaryField);

        JLabel adminIdLabel = new JLabel("Admin ID:");
        adminIdLabel.setBounds(100, 270, 100, 25);
        employeePanel.add(adminIdLabel);

        JTextField adminIdField = new JTextField();
        adminIdField.setBounds(210, 270, 150, 25);
        employeePanel.add(adminIdField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(450, 270, 100, 25);
        employeePanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(570, 270, 150, 25);
        employeePanel.add(emailField);

        // Buttons
        JButton addButton = new JButton("ADD");
        addButton.setBounds(50, 310, 100, 30);
        addButton.setBackground(new Color(123, 104, 238));
        addButton.setForeground(Color.WHITE);
        employeePanel.add(addButton);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(200, 310, 100, 30);
        editButton.setBackground(new Color(123, 104, 238));
        editButton.setForeground(Color.WHITE);
        employeePanel.add(editButton);

        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(350, 310, 100, 30);
        removeButton.setBackground(new Color(123, 104, 238));
        removeButton.setForeground(Color.WHITE);
        employeePanel.add(removeButton);

                // Retrieve button
        JButton retrieveButton = new JButton("RETRIEVE");
        retrieveButton.setBounds(500, 310, 100, 30);
        retrieveButton.setBackground(new Color(123, 104, 238));
        retrieveButton.setForeground(Color.WHITE);
        employeePanel.add(retrieveButton);

        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(650, 310, 100, 30);
        clearButton.setBackground(new Color(123, 104, 238));
        clearButton.setForeground(Color.WHITE);
        employeePanel.add(clearButton);



        // Employee table
        String[] tableColumns = {"Employee ID", "First Name", "Last Name", "Age", "Gender",
                "User Name", "Password", "Phone", "Working Hours", "Salary", "Admin ID", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(null, tableColumns);

        JTable employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBounds(20, 350, 850, 150);
        employeePanel.add(scrollPane);

        // Button functionality
        addButton.addActionListener(_ -> {
            String employeeId = idField.getText();
            if (employeeId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Employee ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection connection = DatabaseConnection.getConnection()) {
                // Check if Employee ID already exists using new method (modified employeeId logic)
                String checkQuery = "SELECT COUNT(*) FROM ManageEmployee WHERE employee_id = ?";
                PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
                checkStmt.setString(1, employeeId);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(frame, "Employee ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Insert new employee
                String query = "INSERT INTO managEmployee (employee_id, first_name, last_name, age, gender, username, " +
                        "password, phone, working_hours, salary, admin_id, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, idField.getText());
                statement.setString(2, firstNameField.getText());
                statement.setString(3, lastNameField.getText());
                statement.setInt(4, Integer.parseInt(ageField.getText()));
                statement.setString(5, genderComboBox.getSelectedItem().toString());
                statement.setString(6, usernameField.getText());
                statement.setString(7, passwordField.getText());
                statement.setString(8, phoneField.getText());
                statement.setInt(9, Integer.parseInt(workingHoursField.getText()));
                statement.setDouble(10, Double.parseDouble(salaryField.getText()));
                statement.setString(11, adminIdField.getText());
                statement.setString(12, emailField.getText());
                statement.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                tableModel.addRow(new Object[]{
                    idField.getText(), firstNameField.getText(), lastNameField.getText(), ageField.getText(),
                        genderComboBox.getSelectedItem(), usernameField.getText(), passwordField.getText(), phoneField.getText(),
                        workingHoursField.getText(), salaryField.getText(), adminIdField.getText(), emailField.getText()
                });

                clearFields(idField, firstNameField, lastNameField, ageField, genderComboBox, usernameField,
                        passwordField, phoneField, workingHoursField, salaryField, adminIdField, emailField);
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error adding employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        editButton.addActionListener(_ -> {
            int selectedRow = employeeTable.getSelectedRow();

            // Step 1: Ensure a row is selected
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 2: Restore row data to text fields if not already populated
            if (idField.getText().isEmpty()) {
                // Populate text fields with selected row data
                idField.setText(employeeTable.getValueAt(selectedRow, 0).toString());
                firstNameField.setText(employeeTable.getValueAt(selectedRow, 1).toString());
                lastNameField.setText(employeeTable.getValueAt(selectedRow, 2).toString());
                ageField.setText(employeeTable.getValueAt(selectedRow, 3).toString());
                genderComboBox.setSelectedItem(employeeTable.getValueAt(selectedRow, 4).toString());
                usernameField.setText(employeeTable.getValueAt(selectedRow, 5).toString());
                passwordField.setText(employeeTable.getValueAt(selectedRow, 6).toString());
                phoneField.setText(employeeTable.getValueAt(selectedRow, 7).toString());
                workingHoursField.setText(employeeTable.getValueAt(selectedRow, 8).toString());
                salaryField.setText(employeeTable.getValueAt(selectedRow, 9).toString());
                adminIdField.setText(employeeTable.getValueAt(selectedRow, 10).toString());
                emailField.setText(employeeTable.getValueAt(selectedRow, 11).toString());
                JOptionPane.showMessageDialog(frame, "Data restored to fields. Edit the fields and click 'Edit' again to save.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Step 3: Save the updates to the database and table
            try (Connection connection = DatabaseConnection.getConnection()) {
                // Retrieve updated values from text fields
                String employeeId = idField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderComboBox.getSelectedItem().toString();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String phone = phoneField.getText();
                int workingHours = Integer.parseInt(workingHoursField.getText());
                double salary = Double.parseDouble(salaryField.getText());
                String adminId = adminIdField.getText();
                String email = emailField.getText();

                // Validate inputs
                if (employeeId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // SQL query to update the record
                String query = "UPDATE managEmployee SET first_name = ?, last_name = ?, age = ?, gender = ?, " +
                        "username = ?, password = ?, phone = ?, working_hours = ?, salary = ?, admin_id = ?, email = ? " +
                        "WHERE employee_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);

                // Set query parameters
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, age);
                statement.setString(4, gender);
                statement.setString(5, username);
                statement.setString(6, password);
                statement.setString(7, phone);
                statement.setInt(8, workingHours);
                statement.setDouble(9, salary);
                statement.setString(10, adminId);
                statement.setString(11, email);
                statement.setString(12, employeeId);

                // Execute update
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame, "Employee updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Update table row with new values
                    employeeTable.setValueAt(firstName, selectedRow, 1);
                    employeeTable.setValueAt(lastName, selectedRow, 2);
                    employeeTable.setValueAt(age, selectedRow, 3);
                    employeeTable.setValueAt(gender, selectedRow, 4);
                    employeeTable.setValueAt(username, selectedRow, 5);
                    employeeTable.setValueAt(password, selectedRow, 6);
                    employeeTable.setValueAt(phone, selectedRow, 7);
                    employeeTable.setValueAt(workingHours, selectedRow, 8);
                    employeeTable.setValueAt(salary, selectedRow, 9);
                    employeeTable.setValueAt(adminId, selectedRow, 10);
                    employeeTable.setValueAt(email, selectedRow, 11);

                    // Clear fields after editing
                    clearFields(idField, firstNameField, lastNameField, ageField, genderComboBox, usernameField,
                            passwordField, phoneField, workingHoursField, salaryField, adminIdField, emailField);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: No matching employee found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error updating employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            String employeeId = employeeTable.getValueAt(selectedRow, 0).toString();
            try (Connection connection = DatabaseConnection.getConnection()) {
                String deleteQuery = "DELETE FROM managEmployee WHERE employee_id = ?";
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setString(1, employeeId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error deleting employee.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Action Listener for Retrieve button
        retrieveButton.addActionListener(_ -> {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM managEmployee";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                // Clear existing table data
                tableModel.setRowCount(0);

                // Populate table with database data
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getString("employee_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("phone"),
                            rs.getInt("working_hours"),
                            rs.getDouble("salary"),
                            rs.getString("admin_id"),
                            rs.getString("email")
                    });
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error retrieving employees: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        //  Action listner for clear button
        clearButton.addActionListener(_ -> {
            clearFields(idField, firstNameField, lastNameField, ageField, genderComboBox, usernameField,
                    passwordField, phoneField, workingHoursField, salaryField, adminIdField, emailField);
        });
    }

    // Helper method to clear fields
    private void clearFields(JTextField idField, JTextField firstNameField, JTextField lastNameField, JTextField ageField,
        JComboBox<String> genderComboBox, JTextField usernameField, JTextField passwordField,
        JTextField phoneField, JTextField workingHoursField, JTextField salaryField, JTextField adminIdField,
        JTextField emailField) {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        genderComboBox.setSelectedIndex(0);
        usernameField.setText("");
        passwordField.setText("");
        phoneField.setText("");
        workingHoursField.setText("");
        salaryField.setText("");
        adminIdField.setText("");
        emailField.setText("");
    }

    public Component getEmployeePanel() {
        return employeePanel;
    }
}
