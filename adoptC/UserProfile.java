import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class UserProfile {
    private JPanel profilePanel;

    public UserProfile(JFrame frame, String username) {
        profilePanel = new JPanel();
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setBounds(200, 90, 600, 400);
        profilePanel.setLayout(null);

        JPanel profileForm = new JPanel();
        profileForm.setLayout(null);
        profileForm.setBounds(50, 30, 500, 300);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        firstNameLabel.setBounds(10, 10, 100, 25);
        profileForm.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(120, 10, 150, 25);
        profileForm.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        lastNameLabel.setBounds(10, 50, 100, 25);
        profileForm.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(120, 50, 150, 25);
        profileForm.add(lastNameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameLabel.setBounds(10, 90, 100, 25);
        profileForm.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(120, 90, 150, 25);
        profileForm.add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        emailLabel.setBounds(10, 130, 100, 25);
        profileForm.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(120, 130, 150, 25);
        profileForm.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        passwordLabel.setBounds(10, 170, 100, 25);
        profileForm.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 170, 150, 25);
        profileForm.add(passwordField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        phoneLabel.setBounds(10, 210, 100, 25);
        profileForm.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(120, 210, 150, 25);
        profileForm.add(phoneField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 250, 100, 30);
        profileForm.add(saveButton);

        profilePanel.add(profileForm);

        User user = getUserDetails(username);
        if (user != null) {
            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
            usernameField.setText(user.getUsername());
            emailField.setText(user.getEmail());
            passwordField.setText(user.getPassword());
            phoneField.setText(user.getPhone());
        } else {
            JOptionPane.showMessageDialog(null, "No user found with that username.");
        }

        saveButton.addActionListener(e -> {
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
            
            updateUserDetails(updatedFirstName, updatedLastName, updatedUsername, updatedEmail, updatedPassword, updatedPhone);
        });
    }

    // private String hashPassword(String password) {
    //     // Example hashing method (consider using stronger algorithms like bcrypt in production)
    //     return Integer.toHexString(password.hashCode());
    // }

    public JPanel getProfilePanel() {
        return profilePanel;
    }

    public static User getUserDetails(String username) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";
        String dbUsername = "root";
        String dbPassword = "sarojkreya369#";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
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

    public static void updateUserDetails(String firstName, String lastName, String username, String email, String password, String phone) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/Signup";
        String dbUsername = "root";
        String dbPassword = "sarojkreya369#";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, phone = ? WHERE username = ?";
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
