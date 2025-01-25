import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdoptMeLoginPage {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("AdoptMe - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLayout(null);
        
        // Set the background color of the frame to white
        frame.getContentPane().setBackground(Color.WHITE);
    
        // Add Login section
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 24));
        loginLabel.setBounds(610, 120, 150, 30);
        frame.add(loginLabel);

        // Add image (replace the path with your actual image path)
        ImageIcon originalIcon = new ImageIcon("AdoptmePicture.png"); // largerPicture

        // Resize the image to fit within the window (e.g., 200x200)
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Resize image to 200x200

        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create new ImageIcon with resized image
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(40, 100, resizedIcon.getIconWidth(), resizedIcon.getIconHeight());
        frame.add(logoLabel);

        // AdoptmeSmallLogo
        ImageIcon logoIcon1 = new ImageIcon("AdoptMeLogo.png"); // Change this to your image path
        Image logoIcon1Image = logoIcon1.getImage();
        Image resizedImage1 = logoIcon1Image.getScaledInstance(230, 90, Image.SCALE_SMOOTH); // Resize image to 200x200
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage1); // Create new ImageIcon with resized image
        JLabel logoLabel2 = new JLabel(resizedIcon2);
        logoLabel2.setBounds(370, 20, resizedIcon2.getIconWidth(), resizedIcon2.getIconHeight());
        frame.add(logoLabel2);

        ImageIcon logoIcon2 = new ImageIcon("AdoptMeLogo.png"); // Change this to your image path
        Image logoIcon2Image = logoIcon2.getImage();
        Image resizedImage2 = logoIcon2Image.getScaledInstance(100, 40, Image.SCALE_SMOOTH); // Resize image to 200x200
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage2); // Create new ImageIcon with resized image
        JLabel logoLabel3 = new JLabel(resizedIcon3);
        logoLabel3.setBounds(10, 20, resizedIcon3.getIconWidth(), resizedIcon3.getIconHeight());
        frame.add(logoLabel3);


        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameLabel.setBounds(450, 170, 100, 25);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(550, 170, 200, 25);
        frame.add(usernameField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        passwordLabel.setBounds(450, 210, 100, 25);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(550, 210, 200, 25);
        frame.add(passwordField);

        // Add show/hide password toggle button
        JCheckBox showPasswordCheckBox = new JCheckBox();
        showPasswordCheckBox.setFont(new Font("Serif", Font.PLAIN, 14));
        showPasswordCheckBox.setBounds(725, 240, 20, 20);
        frame.add(showPasswordCheckBox);
        showPasswordCheckBox.setBackground(Color.WHITE);

        // Add action listener to show/hide password checkbox
        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });

        // Login As dropdown
        JLabel loginAsLabel = new JLabel("Login As:");
        loginAsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        loginAsLabel.setBounds(450, 270, 100, 25);
        frame.add(loginAsLabel);

        String[] loginOptions = {"User", "Admin", "Employee"};
        JComboBox<String> loginAsComboBox = new JComboBox<>(loginOptions);
        loginAsComboBox.setBounds(550, 270, 200, 25);
        frame.add(loginAsComboBox);
        loginAsComboBox.setBackground(Color.WHITE);

        // Login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(450, 335, 300, 25);
        loginButton.setBackground(new Color(34, 177, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Serif", Font.BOLD, 16));
        frame.add(loginButton);

        // Forget Password label
        JLabel forgotPasswordLabel = new JLabel("Forget Password?");
        forgotPasswordLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        forgotPasswordLabel.setBounds(550, 380, 200, 20);
        frame.add(forgotPasswordLabel);

        // Sign-Up Link
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setFont(new Font("Serif", Font.BOLD, 14));
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setBounds(650, 430, 300, 20);
        frame.add(signUpLabel);

        JLabel signUpLabe2 = new JLabel("Don’t have an account? ");
        signUpLabe2.setFont(new Font("Serif", Font.BOLD, 14));
        signUpLabe2.setBounds(490, 430, 300, 20);
        frame.add(signUpLabe2);        


        // Add action listener to Sign-Up label
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose(); // Close the current Login frame
                AdoptMeSignUpPage.main(null); // Open Sign-Up Page
            }
        });


        loginButton.addActionListener(new ActionListener() {
            private String loggedInUsername;
                        private String employeeUsername;
                        
                        @Override
                            public void actionPerformed(ActionEvent e) {
                                String username = usernameField.getText();
                                String password = new String(passwordField.getPassword());
                                String role = (String) loginAsComboBox.getSelectedItem();
                        
                                    
                                if (username.isEmpty() || password.isEmpty()) {
                                    JOptionPane.showMessageDialog(frame, "Username and Password cannot be empty.", "Login Failure", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                    
                                if (validateLogin(username, password, role)) {
                                    frame.dispose(); // Close the login page
                                            
                                    if ("Admin".equals(role)) {
                                        // Open the Admin Dashboard
                                        AdminDashboard.main(new String[] { username }); // Pass username to the dashboard
                                        } else if ("User".equals(role)) {
                        
                                        // Open the User Dashboard, passing the logged-in username
                                            loggedInUsername = username; // Store the logged-in username
                                            UserDashboard.main(new String[] { loggedInUsername });
                                        } else if ("Employee".equals(role)) {
                                            employeeUsername= username;
                                            EmployeeDashboard.main(new String[] { employeeUsername }); // Open employee dashboard
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame, "Invalid Username, Password, or Role. Please try again.", "Login Failure", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    // Show the frame
                    frame.setVisible(true);
                }
        
    public static boolean validateLogin(String username, String password, String role) {
        String Databsename = "Signup";
        String url = "jdbc:mysql://127.0.0.1:3306/" + Databsename;
        String dbUsername = "root";
        String dbPassword = "sarojkreya369#";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "";
            if ("Admin".equalsIgnoreCase(role)) {
                query = "SELECT * FROM Admintable2 WHERE username = ? AND password = ?";
            } else if ("User".equalsIgnoreCase(role)) {
                query = "SELECT * FROM users WHERE username = ? AND password = ?";
            } else if ("Employee".equalsIgnoreCase(role)) {
                query = "SELECT * FROM managEmployee WHERE username = ? AND password = ?";
            } else {
                System.out.println("Invalid role: " + role);
                return false;  // Invalid role
            }
    
            System.out.println("Executing query: " + query); // Debugging query
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
    
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("Login successful for username: " + username); // Debugging
                return true; // If a record exists, login is successful
            } else {
                System.out.println("Login failed for username: " + username); // Debugging
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
