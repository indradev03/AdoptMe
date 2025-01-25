package src.controller;


import javax.swing.*;

import src.model.ManagePetModel;
import src.view.ManagePetView;

import java.sql.*;

public class ManagePetController {
    private ManagePetView view;
    private ManagePetModel model;

    public ManagePetController(ManagePetView view, ManagePetModel model) {
        this.view = view;
        this.model = model;
        
        // Add action listeners for buttons
        view.getAddButton().addActionListener(e -> addPet());
        view.getEditButton().addActionListener(e -> editPet());
        view.getRemoveButton().addActionListener(e -> removePet());
        view.getRetrieveButton().addActionListener(e -> retrievePets());
        view.getClearButton().addActionListener(e -> clearFields());
    }

    private void addPet() {
        String name = view.getNameField().getText();
        String type = view.getTypeField().getText();
        String species = view.getSpeciesField().getText();
        String price = view.getPriceField().getText();
        String age = view.getAgeField().getText();
        String adminID = view.getAdminIDField().getText();

        if (name.isEmpty() || type.isEmpty() || species.isEmpty() || price.isEmpty() || age.isEmpty() || adminID.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double parsedPrice = Double.parseDouble(price);
            int parsedAge = Integer.parseInt(age);

            // Call Model to add pet
            boolean success = model.addPet(name, type, species, parsedPrice, parsedAge, adminID, "");
            if (success) {
                JOptionPane.showMessageDialog(view, "Pet added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                retrievePets();  // Refresh table after adding
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Price and Age must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error adding pet: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editPet() {
        int selectedRow = view.getPetTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a pet to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int petID = (int) view.getTableModel().getValueAt(selectedRow, 0);
        try {
            String name = view.getNameField().getText();
            String type = view.getTypeField().getText();
            String species = view.getSpeciesField().getText();
            String price = view.getPriceField().getText();
            String age = view.getAgeField().getText();
            String adminID = view.getAdminIDField().getText();
            
            double parsedPrice = Double.parseDouble(price);
            int parsedAge = Integer.parseInt(age);

            boolean success = model.editPet(petID, name, type, species, parsedPrice, parsedAge, adminID, "");
            if (success) {
                JOptionPane.showMessageDialog(view, "Pet updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                retrievePets();  // Refresh table after editing
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Price and Age must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error updating pet: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removePet() {
        int selectedRow = view.getPetTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a pet to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int petID = (int) view.getTableModel().getValueAt(selectedRow, 0);
        try {
            boolean success = model.removePet(petID);
            if (success) {
                JOptionPane.showMessageDialog(view, "Pet removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                retrievePets();  // Refresh table after removal
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error removing pet: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retrievePets() {
        try {
            ResultSet rs = model.getAllPets();
            view.getTableModel().setRowCount(0);  // Clear existing data

            while (rs.next()) {
                Object[] row = new Object[] {
                    rs.getInt("pet_id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("species"),
                    rs.getDouble("price"),
                    rs.getString("type"),
                    rs.getString("admin_id")
                };
                view.getTableModel().addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error retrieving pets: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        view.getNameField().setText("");
        view.getTypeField().setText("");
        view.getSpeciesField().setText("");
        view.getPriceField().setText("");
        view.getAgeField().setText("");
        view.getAdminIDField().setText("");
        view.getImageLabel().setIcon(null);
    }
}
