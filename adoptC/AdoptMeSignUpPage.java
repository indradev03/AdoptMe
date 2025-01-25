import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdoptMeSignUpPage {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("AdoptMe - Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLayout(null);

        
        // Add Sign-Up Section
        JLabel signUpLabel = new JLabel("SIGN UP");
        signUpLabel.setFont(new Font("Serif", Font.BOLD, 24));
        signUpLabel.setBounds(580, 125, 150, 30);
        frame.add(signUpLabel);

        // Set the background color of the frame to white
        frame.getContentPane().setBackground(Color.WHITE);

        // Add image (replace the path with your actual image path)
        ImageIcon originalIcon = new ImageIcon("AdoptmePicture.png"); // Change this to your image path

        // Resize the image to fit within the window 
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH); // Resize image to 200x200

        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create new ImageIcon with resized image
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(0, 110, resizedIcon.getIconWidth(), resizedIcon.getIconHeight());
        frame.add(logoLabel);  

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

        // First Name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        firstNameLabel.setBounds(350, 200, 100, 25);
        frame.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(430, 200, 150, 25);
        frame.add(firstNameField);

        // Last Name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        lastNameLabel.setBounds(600, 200, 100, 25);
        frame.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(680, 200, 150, 25);
        frame.add(lastNameField);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameLabel.setBounds(350, 250, 100, 25);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(430, 250, 150, 25);
        frame.add(usernameField);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        emailLabel.setBounds(600, 250, 100, 25);
        frame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(680, 250, 150, 25);
        frame.add(emailField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        passwordLabel.setBounds(350, 300, 100, 25);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(430, 300, 150, 25);
        frame.add(passwordField);

        // Phone label and text field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        phoneLabel.setBounds(600, 300, 100, 25);
        frame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(680, 300, 150, 25);
        frame.add(phoneField);

        // Sign Up button
        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.setBounds(400, 370, 400, 30);
        signUpButton.setBackground(new Color(34, 177, 76));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Serif", Font.BOLD, 16));
        frame.add(signUpButton);

        // Sign In link
        JLabel signInLabel = new JLabel("Sign In");
        signInLabel.setFont(new Font("Serif", Font.BOLD, 14));
        signInLabel.setForeground(Color.BLUE);
        signInLabel.setBounds(670, 435, 300, 20);
        frame.add(signInLabel);

        JLabel signInLabe2 = new JLabel("Already Have an Account?");
        signInLabe2.setFont(new Font("Serif", Font.BOLD, 14));
        signInLabe2.setBounds(490, 435, 300, 20);
        frame.add(signInLabe2);

        // Add action listener to Sign-In label
        signInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Open the Login Page
                frame.dispose(); // Close the current Sign-Up frame
                AdoptMeLoginPage.main(null); // Open Login Page
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String phone = phoneField.getText();
        
                if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                if (registerUser(firstName, lastName, username, email, password, phone)) {
                    JOptionPane.showMessageDialog(frame, "Sign-Up Successful! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Close the Sign-Up frame
                    AdoptMeLoginPage.main(null); // Open Login Page
                } else {
                    JOptionPane.showMessageDialog(frame, "Sign-Up Failed! Username might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
                       // Show the frame
            frame.setVisible(true);
        }

        public static boolean registerUser(String firstName, String lastName, String username, String email, String password, String phone) {
            String Databsename = "Signup";
            String url = "jdbc:mysql://127.0.0.1:3306/" + Databsename;
            String dbUsername = "root";
            String dbPassword = "sarojkreya369#";
        
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                String query = "INSERT INTO users (first_name, last_name, username, email, password, phone, role) VALUES (?, ?, ?, ?, ?, ?, 'User')";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, username);
                stmt.setString(4, email);
                stmt.setString(5, password);
                stmt.setString(6, phone);
        
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    return true;
                } else {
                    System.out.println("Failed to insert data.");
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
