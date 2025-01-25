import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeProfile {

    private JPanel profilePanel;

    public EmployeeProfile(JFrame frame, String username) {
        // Initialize the profile panel
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setBounds(200, 90, 600, 400);
        profilePanel.setLayout(null);  // No layout manager

        // Create profile edit form with no layout manager
        JPanel profileForm = new JPanel();
        profileForm.setLayout(null);  // No layout manager
        profileForm.setBounds(50, 30, 500, 300); // Positioning and size of the form panel

        // First Name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        firstNameLabel.setBounds(10, 10, 100, 25);
        profileForm.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(120, 10, 150, 25);
        profileForm.add(firstNameField);

        // Last Name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        lastNameLabel.setBounds(10, 50, 100, 25);
        profileForm.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(120, 50, 150, 25);
        profileForm.add(lastNameField);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameLabel.setBounds(10, 90, 100, 25);
        profileForm.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(120, 90, 150, 25);
        profileForm.add(usernameField);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        emailLabel.setBounds(10, 130, 100, 25);
        profileForm.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(120, 130, 150, 25);
        profileForm.add(emailField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        passwordLabel.setBounds(10, 170, 100, 25);
        profileForm.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 170, 150, 25);
        profileForm.add(passwordField);

        // Phone label and text field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        phoneLabel.setBounds(10, 210, 100, 25);
        profileForm.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(120, 210, 150, 25);
        profileForm.add(phoneField);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 250, 100, 30);
        profileForm.add(saveButton);

        // Add form panel to the main profile panel
        profilePanel.add(profileForm);

        // Fetch employee details from the database
        Employee employee = getEmployeeDetails(username);
        if (employee != null) {
            firstNameField.setText(employee.getFirstName());
            lastNameField.setText(employee.getLastName());
            usernameField.setText(employee.getUsername());
            emailField.setText(employee.getEmail());
            passwordField.setText(employee.getPassword());
            phoneField.setText(employee.getPhone());
        } else {
            JOptionPane.showMessageDialog(null, "No employee found with that username.");
        }

        // Save button listener
        saveButton.addActionListener(_ -> {
            String updatedFirstName = firstNameField.getText();
            String updatedLastName = lastNameField.getText();
            String updatedUsername = usernameField.getText();  
            String updatedEmail = emailField.getText();
            String updatedPassword = new String(passwordField.getPassword());
            String updatedPhone = phoneField.getText();

            // Input validation
            if (updatedFirstName.isEmpty() || updatedLastName.isEmpty() || updatedUsername.isEmpty() || updatedEmail.isEmpty() || updatedPassword.isEmpty() || updatedPhone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                return;
            }

            // Example: simple email validation
            if (!updatedEmail.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                JOptionPane.showMessageDialog(null, "Invalid email format.");
                return;
            }

            // // Hash the password before updating (consider using a stronger hashing method in production)
            // String hashedPassword = hashPassword(updatedPassword);
            
            updateEmployeeDetails(updatedFirstName, updatedLastName, updatedUsername, updatedEmail, updatedPassword, updatedPhone);
        });
    }

    public JPanel getProfilePanel() {
        return profilePanel;
    }

    public static Employee getEmployeeDetails(String username) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";  // Adjust the database URL if needed
        String dbUsername = "root";
        String dbPassword = "sarojkreya369#";  // Replace with actual password

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String query = "SELECT * FROM managEmployee WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateEmployeeDetails(String firstName, String lastName, String username, String email, String password, String phone) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";  // Adjust the database URL if needed
        String dbUsername = "root";
        String dbPassword = "sarojkreya369#";  // Replace with actual password

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String query = "UPDATE managEmployee SET first_name = ?, last_name = ?, email = ?, password = ?, phone = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, phone);
            stmt.setString(6, username);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Profile updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update profile. Please check the username.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
