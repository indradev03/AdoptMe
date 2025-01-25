package src.controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import src.view.ManageCustomersView;

public class ManageCustomersController {
    private ManageCustomersView view;
    private DefaultTableModel tableModel;

    private Object[][] customerData = {
        {"C011", "Saroj Ayer", "sarojayer@example.com", "9811657564", "Dhangadhi"},
        {"C002", "Kritan Nepal", "kritan@example.com", "9876543210", "Jhapa"},
        {"C063", "Anmol Shrestha", "anmol@example.com", "9876587210", "Dhadhing"},
        {"C045", "Prajita Shrestha", "prajita@example.com", "9876589870", "Kathmandu"},
        {"C055", "Joshna Thapa", "joshna@example.com", "9788987210", "Kathmandu"}
    };

    public ManageCustomersController(ManageCustomersView view) {
        this.view = view;

        // Update the table with customer data
        view.updateCustomerTable(customerData);

        // Add action listener for the delete button
        view.getDeleteButton().addActionListener(e -> deleteSelectedCustomer());

        // Add selection listener to the table
        view.getCustomersTable().getSelectionModel().addListSelectionListener(this::onTableSelection);
    }

    // Method to delete the selected customer
    private void deleteSelectedCustomer() {
        int selectedRow = view.getCustomersTable().getSelectedRow();
        if (selectedRow != -1) {
            // Remove the selected customer from the model data
            Object[][] updatedData = new Object[customerData.length - 1][5];
            int rowIndex = 0;
            for (int i = 0; i < customerData.length; i++) {
                if (i != selectedRow) {
                    updatedData[rowIndex++] = customerData[i];
                }
            }

            customerData = updatedData;
            view.updateCustomerTable(customerData);
            view.showSuccess("Customer deleted successfully!");
        } else {
            view.showError("No customer selected. Please select a customer to delete.");
        }
    }

    // Method to handle table row selection (if needed for additional functionality)
    private void onTableSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = view.getCustomersTable().getSelectedRow();
            // You can add additional behavior when a row is selected, like enabling editing options.
        }
    }
}
