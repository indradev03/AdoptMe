package src.view;

import javax.swing.*;

import src.model.SupplyDAO.Supply;
import src.model.UserSupply;

import java.awt.*;
import java.util.List;

public class UserSuppliesView {
    private JPanel supplyPanel;
    private JFrame frame;

    public UserSuppliesView(JFrame frame) {
        this.frame = frame;
        supplyPanel = new JPanel();
        supplyPanel.setLayout(new BorderLayout());
    }

    public JPanel getSupplyPanel() {
        return supplyPanel;
    }

    // Method to create the initial category buttons
    public JPanel createCategoryButtons(List<String> categories) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(1, categories.size(), 20, 20));

        for (String category : categories) {
            JButton button = new JButton(category);
            button.setFont(new Font("Serif", Font.PLAIN, 16));
            categoryPanel.add(button);
        }

        return categoryPanel;
    }

    // Method to display the supplies based on the category
    public JScrollPane displaySupplyCategory(List<UserSupply> supplies) {
        JPanel categoryView = new JPanel();
        categoryView.setLayout(new GridLayout(0, 2, 20, 20));

        for (UserSupply supply : supplies) {
            JButton supplyButton = new JButton(supply.getDescription());
            ImageIcon supplyImageIcon = new ImageIcon(new ImageIcon(supply.getImagePath()).getImage()
                    .getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            supplyButton.setIcon(supplyImageIcon);
            supplyButton.setHorizontalAlignment(SwingConstants.CENTER);
            supplyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            categoryView.add(supplyButton);
        }

        JScrollPane scrollPane = new JScrollPane(categoryView);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        return scrollPane;
    }

    // Method to display the supply details
    public JPanel displaySupplyDetails(Supply supply) {
        JPanel supplyDetailsPanel = new JPanel();
        supplyDetailsPanel.setLayout(new BorderLayout());

        // Image panel
        ImageIcon supplyImageIcon = new ImageIcon(new ImageIcon(supply.getImagePath()).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
        JLabel supplyImageLabel = new JLabel(supplyImageIcon);

        // Details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        JTextArea supplyDetailsArea = new JTextArea();
        supplyDetailsArea.setEditable(false);
        supplyDetailsArea.setBackground(new Color(240, 240, 240));
        supplyDetailsArea.setText("ID: " + supply.getId() + "\nDescription: " + supply.getDescription() +
                "\nQuantity: " + supply.getQuantity() + "\nPrice: Rs." + supply.getPrice() +
                "\nAvailability: " + supply.getAvailability());
        detailsPanel.add(supplyDetailsArea);

        JButton addToCartButton = new JButton("Add to Cart");
        detailsPanel.add(addToCartButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(new JScrollPane(supplyImageLabel));
        mainPanel.add(detailsPanel);

        supplyDetailsPanel.add(mainPanel, BorderLayout.CENTER);
        return supplyDetailsPanel;
    }

    // Method to set up the Back button for each screen
    public JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 177, 76));
        backButton.setForeground(Color.WHITE);
        return backButton;
    }
}

