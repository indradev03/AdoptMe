package src.main;

import javax.swing.*;

import src.controller.EmployeeProfileController;
import src.model.EmployeeDatabase;
import src.view.EmployeeProfileView;

public class EmployeeProfileMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Profile");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EmployeeProfileView view = new EmployeeProfileView();
        EmployeeDatabase model = new EmployeeDatabase();
        EmployeeProfileController controller = new EmployeeProfileController(view, model);

        frame.setContentPane(view.getProfilePanel());
        frame.setVisible(true);
    }
}

