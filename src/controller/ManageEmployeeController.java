package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane.*;
import src.model.Employee;
import src.model.ManageEmployeeModel;
import src.view.ManageEmployeeView;

public class ManageEmployeeController {

    private ManageEmployeeView view;
    private ManageEmployeeModel model;

    public ManageEmployeeController(ManageEmployeeView view, ManageEmployeeModel model) {
        this.view = view;
        this.model = model;

        // Setup button listeners
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        // Add listener for ADD button
        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee(
                        view.getIdField().getText(),
                        view.getFirstNameField().getText(),
                        view.getLastNameField().getText(),
                        Integer.parseInt(view.getAgeField().getText()),
                        (String) view.getGenderComboBox().getSelectedItem(),
                        view.getUsernameField().getText(),
                        view.getPasswordField().getText(),
                        view.getPhoneField().getText(),
                        Integer.parseInt(view.getWorkingHoursField().getText()),
                        Double.parseDouble(view.getSalaryField().getText()),
                        view.getAdminIdField().getText(),
                        view.getEmailField().getText()
                );
                try {
                    if (model.addEmployee(employee)) {
                        JOptionPane.showMessageDialog(null, "Employee added successfully.");
                        refreshEmployeeTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error adding employee.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            }
        });

        // Edit listener for EDIT button
        view.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getEmployeeTable().getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Retrieve employee details from the table and populate the fields for editing
                view.getIdField().setText(view.getEmployeeTable().getValueAt(selectedRow, 0).toString());
                view.getFirstNameField().setText(view.getEmployeeTable().getValueAt(selectedRow, 1).toString());
                view.getLastNameField().setText(view.getEmployeeTable().getValueAt(selectedRow, 2).toString());
                view.getAgeField().setText(view.getEmployeeTable().getValueAt(selectedRow, 3).toString());
                view.getGenderComboBox().setSelectedItem(view.getEmployeeTable().getValueAt(selectedRow, 4).toString());
                view.getUsernameField().setText(view.getEmployeeTable().getValueAt(selectedRow, 5).toString());
                view.getPasswordField().setText(view.getEmployeeTable().getValueAt(selectedRow, 6).toString());
                view.getPhoneField().setText(view.getEmployeeTable().getValueAt(selectedRow, 7).toString());
                view.getWorkingHoursField().setText(view.getEmployeeTable().getValueAt(selectedRow, 8).toString());
                view.getSalaryField().setText(view.getEmployeeTable().getValueAt(selectedRow, 9).toString());
                view.getAdminIdField().setText(view.getEmployeeTable().getValueAt(selectedRow, 10).toString());
                view.getEmailField().setText(view.getEmployeeTable().getValueAt(selectedRow, 11).toString());

                // Now, update the employee in the database
                Employee updatedEmployee = new Employee(
                        view.getIdField().getText(),
                        view.getFirstNameField().getText(),
                        view.getLastNameField().getText(),
                        Integer.parseInt(view.getAgeField().getText()),
                        (String) view.getGenderComboBox().getSelectedItem(),
                        view.getUsernameField().getText(),
                        view.getPasswordField().getText(),
                        view.getPhoneField().getText(),
                        Integer.parseInt(view.getWorkingHoursField().getText()),
                        Double.parseDouble(view.getSalaryField().getText()),
                        view.getAdminIdField().getText(),
                        view.getEmailField().getText()
                );

                try {
                    if (model.updateEmployee(updatedEmployee)) {
                        JOptionPane.showMessageDialog(view, "Employee updated successfully.");
                        refreshEmployeeTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Error updating employee.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage());
                }
            }
        });

        // Remove listener for REMOVE button
        view.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getEmployeeTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String employeeId = view.getEmployeeTable().getValueAt(selectedRow, 0).toString();
                try {
                    if (model.removeEmployee(employeeId)) {
                        JOptionPane.showMessageDialog(view, "Employee deleted successfully.");
                        refreshEmployeeTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Error deleting employee.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage());
                }
            }
        });

        // Retrieve button action listener
        view.getRetrieveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshEmployeeTable();
            }
        });
    }

    private void refreshEmployeeTable() {
        try {
            List<Employee> employees = model.getAllEmployees();
            DefaultTableModel tableModel = (DefaultTableModel) view.getEmployeeTable().getModel();
            tableModel.setRowCount(0);  // Clear the existing data in the table
            for (Employee employee : employees) {
                tableModel.addRow(new Object[]{
                        employee.getEmployeeId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getAge(),
                        employee.getGender(),
                        employee.getUsername(),
                        employee.getPassword(),
                        employee.getPhone(),
                        employee.getWorkingHours(),
                        employee.getSalary(),
                        employee.getAdminId(),
                        employee.getEmail()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error retrieving employees: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
