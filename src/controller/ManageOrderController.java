package src.controller;

import javax.swing.*;

import src.model.ManageOrderModel;
import src.view.ManageOrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageOrderController {
    private ManageOrderModel model;
    private ManageOrderView view;

    public ManageOrderController(ManageOrderModel model, ManageOrderView view) {
        this.model = model;
        this.view = view;

        // Fetch order history from the model
        model.fetchOrderHistory();
        view.populateOrderTable(model.getOrderHistory());

        // Add listener to the table row selection for order management
        view.ordersTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getSelectedOrderRow();
                if (selectedRow != -1) {
                    String receiptNumber = (String) view.ordersTable.getValueAt(selectedRow, 0);
                    String itemName = (String) view.ordersTable.getValueAt(selectedRow, 1);
                    String quantity = (String) view.ordersTable.getValueAt(selectedRow, 2);

                    int response = JOptionPane.showConfirmDialog(null,
                            "Do you want to manage the selected order?\nReceipt Number: " + receiptNumber + "\nItem: " + itemName + "\nQuantity: " + quantity,
                            "Manage Order",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        // Simulate placing the order and then removing it from history
                        JOptionPane.showMessageDialog(null, "Order placed successfully!", "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        model.removeOrder(selectedRow);
                        view.clearOrderTable();
                        view.populateOrderTable(model.getOrderHistory());
                    }
                }
            }
        });
    }
}

