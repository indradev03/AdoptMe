package src.controller;
import javax.swing.*;

import src.model.SupplyDAO.Supply;
import src.model.UserSupply;
import src.view.UserSuppliesView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserSuppliesController {
    private UserSuppliesView view;
    private List<UserSupply> allSupplies;
    private JFrame frame;
    public UserSuppliesController(JFrame frame, String loggedInUsername) {
        this.frame = frame;
        this.view = new UserSuppliesView(frame);

        // Example supplies
        allSupplies = new ArrayList<>();
        allSupplies.add(new UserSupply("1", "Food", "food_image1.png", "Dog Food", "10 kg", "50", "Available"));
        allSupplies.add(new UserSupply("2", "Food", "food_image2.png", "Cat Food", "5 kg", "30", "Available"));
        allSupplies.add(new UserSupply("3", "Toys", "toy_image1.png", "Chew Toy", "1 piece", "15", "Available"));
        // Add more supplies...

        initialize();
    }

    private void initialize() {
        List<String> categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Toys");
        categories.add("Accessories");

        // Set up the initial view with category buttons
        JPanel categoryPanel = view.createCategoryButtons(categories);
        view.getSupplyPanel().add(categoryPanel, BorderLayout.CENTER);

        // Button listeners for category buttons
        for (Component component : categoryPanel.getComponents()) {
            JButton button = (JButton) component;
            button.addActionListener(e -> showSuppliesForCategory(button.getText()));
        }

        frame.add(view.getSupplyPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void showSuppliesForCategory(String category) {
        List<UserSupply> filteredSupplies = new ArrayList<>();
        for (UserSupply supply : allSupplies) {
            if (supply.getCategory().equals(category)) {
                filteredSupplies.add(supply);
            }
        }

        JScrollPane supplyCategoryView = view.displaySupplyCategory(filteredSupplies);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(supplyCategoryView, BorderLayout.CENTER);

        JButton backButton = view.createBackButton();
        backButton.addActionListener(e -> initialize());
        frame.add(backButton, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    public void showSupplyDetails(Supply supply) {
        JPanel supplyDetailsPanel = view.displaySupplyDetails(supply);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(supplyDetailsPanel, BorderLayout.CENTER);

        JButton backButton = view.createBackButton();
        backButton.addActionListener(e -> showSuppliesForCategory(supply.getCategory()));
        frame.add(backButton, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }
}
