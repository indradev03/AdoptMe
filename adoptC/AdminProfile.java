import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminProfile {
    private JPanel profilePanel;

    public AdminProfile(JFrame frame, String username) {
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setBounds(200, 90, 600, 400);
        profilePanel.setLayout(null);

        JPanel profileForm = new JPanel();
        profileForm.setLayout(null);
        profileForm.setBounds(50, 30, 500, 300);

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

        // Phone label and text field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        phoneLabel.setBounds(10, 170, 100, 25);
        profileForm.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(120, 170, 150, 25);
        profileForm.add(phoneField);

        // Admin specific fields (Optional)
        JLabel adminRoleLabel = new JLabel("Role:");
        adminRoleLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        adminRoleLabel.setBounds(10, 210, 100, 25);
        profileForm.add(adminRoleLabel);

        JTextField adminRoleField = new JTextField();
        adminRoleField.setBounds(120, 210, 150, 25);
        adminRoleField.setText("Admin");
        adminRoleField.setEditable(false);
        profileForm.add(adminRoleField);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 250, 100, 30);
        profileForm.add(saveButton);

        profilePanel.add(profileForm);

        Admin admin = getAdminDetails(username);
        if (admin != null) {
            firstNameField.setText(admin.getFirstName());
            lastNameField.setText(admin.getLastName());
            usernameField.setText(admin.getUsername());
            emailField.setText(admin.getEmail());
            phoneField.setText(admin.getPhone());
        } else {
            JOptionPane.showMessageDialog(null, "No admin found with that username.");
        }

        saveButton.addActionListener(_ -> {
            String updatedFirstName = firstNameField.getText();
            String updatedLastName = lastNameField.getText();
            String updatedUsername = usernameField.getText();
            String updatedEmail = emailField.getText();
            String updatedPhone = phoneField.getText();

            // Input validation
            if (updatedFirstName.isEmpty() || updatedLastName.isEmpty() || updatedUsername.isEmpty() || updatedEmail.isEmpty() || updatedPhone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                return;
            }

            // Example: simple email validation
            if (!updatedEmail.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                JOptionPane.showMessageDialog(null, "Invalid email format.");
                return;
            }

            updateAdminDetails(updatedFirstName, updatedLastName, updatedUsername, updatedEmail, updatedPhone);
        });
    }

    public JPanel getProfilePanel() {
        return profilePanel;
    }

    public static Admin getAdminDetails(String username) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";
        String dbUsername = "root";
        String dbPassword = "sarojkreya369#";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String query = "SELECT * FROM Admintable2 WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
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

    public static void updateAdminDetails(String firstName, String lastName, String username, String email, String phone) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";
        String dbUsername = "root";
        String dbPassword = "sarojkreya369#";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String query = "UPDATE Admintable2 SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, username);

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

    public Component getProfilePanel(String adminname) {
            return profilePanel;

    }
}
