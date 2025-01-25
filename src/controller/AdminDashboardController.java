package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.model.AdminDashboardModel;
import src.view.AdminDashboardView;

public class AdminDashboardController {
    private AdminDashboardModel model;
    private AdminDashboardView view;

    public AdminDashboardController(AdminDashboardModel model, AdminDashboardView view) {
        this.model = model;
        this.view = view;

        // Attach action listeners to buttons
        view.getProfileButton().addActionListener(new ProfileButtonListener());
        view.getLogoutButton().addActionListener(new LogoutButtonListener());
        view.getHomeButton().addActionListener(new HomeButtonListener());
        view.getManageEmployeeButton().addActionListener(new ManageEmployeeButtonListener());
        view.getManagePetButton().addActionListener(new ManagePetButtonListener());
        view.getManageSuppliesButton().addActionListener(new ManageSuppliesButtonListener());
    }

    // Action listener for the Profile button
    private class ProfileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel mainPanel = view.getMainPanel();
            JPanel profilePanel = view.createProfilePanel(model.getAdminUsername());
            mainPanel.removeAll();
            mainPanel.add(profilePanel);
            mainPanel.revalidate();
            mainPanel.repaint();
            System.out.println("Switched to Profile page for " + model.getAdminUsername());
        }
    }

    // Action listener for the Logout button
    private class LogoutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getFrame().dispose(); // Close the current frame
            // Redirect to login page (replace with your login page initialization logic)
        }
    }

    // Action listener for the Home button
    private class HomeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel mainPanel = view.getMainPanel();
            JButton homePanel = view.getHomeButton();
            mainPanel.removeAll();
            mainPanel.add(homePanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    // Action listener for Manage Employee button
    private class ManageEmployeeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel mainPanel = view.getMainPanel();
            JButton employeePanel = view.getManageEmployeeButton();
            mainPanel.removeAll();
            mainPanel.add(employeePanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    // Action listener for Manage Pet button
    private class ManagePetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel mainPanel = view.getMainPanel();
            JButton petPanel = view.getManagePetButton();
            mainPanel.removeAll();
            mainPanel.add(petPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    // Action listener for Manage Supplies button
    private class ManageSuppliesButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel mainPanel = view.getMainPanel();
            JButton suppliesPanel = view.getManageSuppliesButton();
            mainPanel.removeAll();
            mainPanel.add(suppliesPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
}
