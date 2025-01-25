package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.model.EmployeeDashboardModel;
import src.view.EmployeeDashboardView;
import javax.swing.*;

public class EmployeeDashboardController {
    private EmployeeDashboardModel model;
    private EmployeeDashboardView view;

    // Panels to manage the dashboard layout
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel mainPanel;

    // Frame reference from the view
    private JFrame frame;

    public EmployeeDashboardController(EmployeeDashboardModel model, EmployeeDashboardView view) {
        this.model = model;
        this.view = view;

        // Initialize frame and panels from the view
        this.frame = view.getFrame();
        this.topPanel = view.getTopPanel();
        this.leftPanel = view.getLeftPanel();
        this.mainPanel = view.getMainPanel();

        // Update the view with employee username
        view.setUsername(model.getEmployeeUsername());

        // Setup action listeners
        setupActionListeners();
    }

    private void setupActionListeners() {
        view.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileView();
            }
        });

        view.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        view.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeView();
            }
        });

        view.getCustomersButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomersView();
            }
        });

        view.getOrdersButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOrdersView();
            }
        });
    }

    public void showProfileView() {
        // Assuming `employeeProfile` is another view or component that you manage
        JPanel profilePanel = view.getEmployeeProfilePanel();
        switchPanel(profilePanel);
    }

    public void showHomeView() {
        // Show the main/home panel
        switchPanel(mainPanel);
    }

    public void showCustomersView() {
        // ManageCustomers should have a method to return its panel
        ManageCustomers manageCustomers = new ManageCustomers(frame, model.getEmployeeUsername());
        JPanel customerPanel = manageCustomers.getCustomerPanel();
        switchPanel(customerPanel);
    }

    public void showOrdersView() {
        // ManageOrders should have a method to return its panel
        ManageOrders manageOrders = new ManageOrders(frame, model.getEmployeeUsername());
        JPanel ordersPanel = manageOrders.getOrdersPanel();
        switchPanel(ordersPanel);
    }

    public void logout() {
        // Close the current dashboard frame and navigate to login
        frame.dispose();
        // Add logic to navigate to the login page if necessary
        JOptionPane.showMessageDialog(null, "Logged out successfully.");
    }

    private void switchPanel(JPanel newPanel) {
        // Clear the current content and add new panel along with top and left panels
        frame.getContentPane().removeAll();
        frame.add(topPanel);  // Re-add top panel
        frame.add(leftPanel); // Re-add left panel
        frame.add(newPanel);  // Add the new panel
        frame.revalidate();
        frame.repaint();
    }
}
