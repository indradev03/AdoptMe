import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ManageCustomers {

    private JPanel panel;
    private JTable customersTable;
    private DefaultTableModel tableModel;

    public ManageCustomers(JFrame frame, String employeeUsername) {
        // Initialize the main panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBounds(260, 70, 600, 450);

        // Add a title label
        JLabel label = new JLabel("Customer Details");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        // Add spacing between components
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create sample data for the table
        String[] columnNames = {"Customer ID", "Name", "Email", "Phone", "Address"};
        Object[][] data = {
            {"C011", "Saroj Ayer", "sarojayer@example.com", "9811657564", "Dhangadhi"},
            {"C002", "Kritan Nepal", "kritan@example.com", "9876543210", "Jhapa"},
            {"C063", "Anmol Shrestha", "anmol@example.com", "9876587210", "Dhadhing"},
            {"C045", "Prajita Shrestha", "prajita@example.com", "9876589870", "Kathmandu"},
            {"C055", "Joshna Thapa", "joshna@example.com", "9788987210", "Kathmandu"}
        };

        // Create a table model to make it easier to manipulate the data
        tableModel = new DefaultTableModel(data, columnNames);
        customersTable = new JTable(tableModel);
        customersTable.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(customersTable);
        panel.add(scrollPane);

        // Create and add a delete button
        JButton deleteButton = new JButton("Delete Customer");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing
        panel.add(deleteButton);

        // Add action listener to the delete button
        deleteButton.addActionListener(_ -> deleteSelectedCustomer());

        // Add a selection listener to the table (optional for extra functionality)
        customersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    customersTable.getSelectedRow();
                }
            }
        });
    }

    // Method to delete the selected customer
    private void deleteSelectedCustomer() {
        int selectedRow = customersTable.getSelectedRow();
        if (selectedRow != -1) {
            // Remove the selected row from the table model
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(panel, "No customer selected. Please select a row to delete.");
        }
    }

    public JPanel getCustomerPanel() {
        return panel;
    }
}
