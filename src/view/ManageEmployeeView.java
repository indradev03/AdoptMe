package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import src.model.Employee;

import java.awt.*;
import java.util.List;

public class ManageEmployeeView {

    private JPanel employeePanel;
    private JTextField idField, firstNameField, lastNameField, ageField, usernameField, passwordField, phoneField, 
                        workingHoursField, salaryField, adminIdField, emailField;
    private JComboBox<String> genderComboBox;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public ManageEmployeeView() {
        // Initialize the view components
        employeePanel = new JPanel();
        employeePanel.setLayout(null);
        employeePanel.setBackground(new Color(230, 230, 250));

        JLabel titleLabel = new JLabel("Manage Employee");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(350, 10, 300, 40);
        titleLabel.setForeground(new Color(72, 61, 139));
        employeePanel.add(titleLabel);

        // Other UI components like labels, text fields, buttons, and table
        // Setup employee table
        String[] columns = {"Employee ID", "First Name", "Last Name", "Age", "Gender", "Username", "Password", 
                            "Phone", "Working Hours", "Salary", "Admin ID", "Email"};
        tableModel = new DefaultTableModel(null, columns);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBounds(20, 350, 850, 150);
        employeePanel.add(scrollPane);

        // Add other UI components like text fields and buttons...
    }

    public JPanel getEmployeePanel() {
        return employeePanel;
    }

    public void setEmployeeTable(List<Employee> employees) {
        // Populate the table with the employee data
        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{
                employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getAge(),
                employee.getGender(), employee.getUsername(), employee.getPassword(), employee.getPhone(),
                employee.getWorkingHours(), employee.getSalary(), employee.getAdminId(), employee.getEmail()
            });
        }
    }

    // Getter methods for text fields
    public JTextField getIdField() {
        return idField;
    }

    public JTextComponent getFirstNameField() {
            return firstNameField;
    }

    public JTextComponent getLastNameField() {
            return lastNameField;

    }

    public JTextField getAddButton() {
            return null;
    }

    public JTextComponent getAgeField() {
            return ageField;

    }

    public Object getGenderComboBox() {
            return genderComboBox;
    }

    public JTextComponent getUsernameField() {
            return usernameField;
    }

    public JTextComponent getPasswordField() {
            return passwordField;
    }

    public JTextComponent getPhoneField() {
            return phoneField;
    }

    public JTextComponent getWorkingHoursField() {
            return workingHoursField;
    }

    public JTextComponent getSalaryField() {
            return salaryField;
    }

    public JTextComponent getAdminIdField() {
            return adminIdField;
    }

    public JTextComponent getEmailField() {
            return emailField;
    }

    public JTextField getEditButton() {
            return null;   
    }

    public Object getEmployeeTable() {
            return employeeTable;
    }

    public JTextField getRemoveButton() {
            return null;
    }
    public JTextField getRetrieveButton() {
            return null;
    }
}


