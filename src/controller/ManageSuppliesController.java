package src.controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.main.SupplyMain;
import src.model.SupplyDAO;
import src.model.SupplyDAO.Supply;
import src.view.ManageSuppliesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageSuppliesController {
    private static final Logger LOGGER = Logger.getLogger(ManageSuppliesController.class.getName());
    private final ManageSuppliesView view;
    private final SupplyDAO model;

    public ManageSuppliesController(ManageSuppliesView view, SupplyDAO model) {
        this.view = view;
        this.model = model;

        // Attach action listeners
        view.getAddButton().addActionListener(new AddButtonListener());
        view.getEditButton().addActionListener(new EditButtonListener());
        view.getRemoveButton().addActionListener(new RemoveButtonListener());
        view.getRetrieveButton().addActionListener(new RetrieveButtonListener());
        view.getClearButton().addActionListener(e -> clearFields());
        view.getSearchButton().addActionListener(new SearchButtonListener());
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Supply supply = retrieveSupplyFromFields();
                validateSupply(supply);
                model.addSupply(supply);
                JOptionPane.showMessageDialog(view, "Supply added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
                clearFields();
            } catch (SQLException ex) {
                showError("Error adding supply: " + ex.getMessage());
                LOGGER.log(Level.SEVERE, "Error adding supply", ex);
            } catch (IllegalArgumentException ex) {
                showError("Invalid input: " + ex.getMessage());
            }
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSuppliesTable().getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a row to edit.");
                return;
            }

            try {
                if (fieldsAreEmpty()) {
                    populateFieldsFromTable(selectedRow);
                                        JOptionPane.showMessageDialog(view, "Fields populated. Make changes and click 'Edit' again to save.", "Info", JOptionPane.INFORMATION_MESSAGE);
                                        return;
                                    }
                    
                                    Supply supply = retrieveSupplyFromFields();
                                    validateSupply(supply);
                                    model.updateSupply(supply);
                                    JOptionPane.showMessageDialog(view, "Supply updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    refreshTable();
                                    clearFields();
                                } catch (SQLException ex) {
                                    showError("Error updating supply: " + ex.getMessage());
                                    LOGGER.log(Level.SEVERE, "Error updating supply", ex);
                                } catch (IllegalArgumentException ex) {
                                    showError("Invalid input: " + ex.getMessage());
                                }
                            }
                        }
                    
                        private class RemoveButtonListener implements ActionListener {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int selectedRow = view.getSuppliesTable().getSelectedRow();
                                if (selectedRow == -1) {
                                    showError("Please select a row to delete.");
                                    return;
                                }
                    
                                String supplyId = view.getSuppliesTable().getValueAt(selectedRow, 0).toString();
                                try {
                                    model.deleteSupply(supplyId);
                                    JOptionPane.showMessageDialog(view, "Supply deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    refreshTable();
                                } catch (SQLException ex) {
                                    showError("Error deleting supply: " + ex.getMessage());
                                    LOGGER.log(Level.SEVERE, "Error deleting supply", ex);
                                }
                            }
                        }
                    
                        private class RetrieveButtonListener implements ActionListener {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                refreshTable();
                            }
                        }
                    
                        private class SearchButtonListener implements ActionListener {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String searchTerm = view.getSearchField().getText().trim();
                                if (searchTerm.isEmpty()) {
                                    showError("Please enter a search term.");
                                    return;
                                }
                    
                                try {
                                    List<Supply> supplies = model.searchSupplies(searchTerm);
                                    updateTable(supplies);
                                    if (supplies.isEmpty()) {
                                        JOptionPane.showMessageDialog(view, "No supplies found matching the search term.", "Info", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } catch (SQLException ex) {
                                    showError("Error searching supplies: " + ex.getMessage());
                                    LOGGER.log(Level.SEVERE, "Error searching supplies", ex);
                                }
                            }
                        }
                    
                        // Helper Methods
                    
                        private void refreshTable() {
                            try {
                                List<Supply> supplies = model.getAllSupplies();
                                updateTable(supplies);
                            } catch (SQLException ex) {
                                showError("Error retrieving supplies: " + ex.getMessage());
                                LOGGER.log(Level.SEVERE, "Error retrieving supplies", ex);
                            }
                        }
                    
                        public void populateFieldsFromTable(int selectedRow) {
                            // TODO Auto-generated method stub
                            throw new UnsupportedOperationException("Unimplemented method 'populateFieldsFromTable'");
                        }
                    
                        private void updateTable(List<Supply> supplies) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getSuppliesTable().getModel();
        tableModel.setRowCount(0); // Clear table

        for (Supply supply : supplies) {
            tableModel.addRow(new Object[]{
                    supply.getSupplyId(),
                    supply.getName(),
                    supply.getType(),
                    supply.getQuantity(),
                    supply.getPrice(),
                    supply.getAdminId()
            });
        }
    }

    private void validateSupply(Supply supply) {
        if (supply.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (supply.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }

    private Supply retrieveSupplyFromFields() {
        String supplyId = view.getSupplyIDField().getText();
        String name = view.getNameField().getText();
        String type = view.getTypeField().getText();
        String quantityStr = view.getQuantityField().getText();
        String priceStr = view.getPriceField().getText();
        String adminId = view.getAdminIDField().getText();

        if (supplyId.isEmpty() || name.isEmpty() || type.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty() || adminId.isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled in.");
        }

        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Quantity must be a valid integer.");
        }

        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Price must be a valid decimal number.");
        }

        Supply supply = new Supply();
        supply.setSupplyId(supplyId);
        supply.setName(name);
        supply.setType(type);
        supply.setQuantity(quantity);
        supply.setPrice(price);
        supply.setAdminId(adminId);

        return supply;
    }

    private boolean fieldsAreEmpty() {
        return view.getSupplyIDField().getText().isEmpty() &&
            view.getNameField().getText().isEmpty() &&
            view.getTypeField().getText().isEmpty() &&
            view.getQuantityField().getText().isEmpty() &&
            view.getPriceField().getText().isEmpty() &&
            view.getAdminIDField().getText().isEmpty();
    }

    private void clearFields() {
        view.getSupplyIDField().setText("");
        view.getNameField().setText("");
        view.getTypeField().setText("");
        view.getQuantityField().setText("");
        view.getPriceField().setText("");
        view.getAdminIDField().setText("");
        view.getSearchField().setText("");
    }

    private void showError(String message) {
    }
}
