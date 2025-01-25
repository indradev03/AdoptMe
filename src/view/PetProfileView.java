package src.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import src.model.Pet;



public class PetProfileView {
    private JFrame frame;
    private JPanel petPanel;
    private JButton dogsButton;
    private JButton catsButton;
    private JButton backButton;
    private JButton addToCartButton;

    public PetProfileView(JFrame frame) {
        this.frame = frame;
        this.petPanel = new JPanel();
        this.petPanel.setLayout(new BorderLayout());
        
        // Title Label
        JLabel titleLabel = new JLabel("Pet Profiles", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        petPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Button Panel for Dogs and Cats
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(1, 2, 20, 20));

        dogsButton = new JButton("Dogs");
        categoryPanel.add(dogsButton);

        catsButton = new JButton("Cats");
        categoryPanel.add(catsButton);

        petPanel.add(categoryPanel, BorderLayout.CENTER);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 177, 76));
        backButton.setForeground(Color.WHITE);
        petPanel.add(backButton, BorderLayout.SOUTH);
    }

    public JPanel getPetPanel() {
        return petPanel;
    }

    public void updatePetList(List<Pet> pets) {
        // Create a new panel to show the pet list
        JPanel petListPanel = new JPanel();
        petListPanel.setLayout(new GridLayout(0, 2, 20, 20));

        for (Pet pet : pets) {
            JButton petButton = new JButton(pet.getDescription());
            ImageIcon petIcon = new ImageIcon(new ImageIcon(pet.getImagePath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            petButton.setIcon(petIcon);
            petButton.setHorizontalAlignment(SwingConstants.CENTER);
            petButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            petListPanel.add(petButton);
        }

        JScrollPane scrollPane = new JScrollPane(petListPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        frame.getContentPane().removeAll();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showPetDetails(String petId, String description, String age, String price, String health, ImageIcon petImageIcon) {
        // Setup UI components to show pet details (ID, Description, Price, etc.)
    }

    // Add listeners for buttons
    public void addDogsButtonListener(ActionListener listener) {
        dogsButton.addActionListener(listener);
    }

    public void addCatsButtonListener(ActionListener listener) {
        catsButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addAddToCartButtonListener(ActionListener listener) {
        addToCartButton.addActionListener(listener);
    }
}
