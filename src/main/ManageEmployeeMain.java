package src.main;

import javax.swing.JFrame;

import src.controller.ManageEmployeeController;
import src.model.ManageEmployeeModel;
import src.view.ManageEmployeeView;

public class ManageEmployeeMain {
    public static void main(String[] args) {
        ManageEmployeeView view = new ManageEmployeeView();
        ManageEmployeeModel model = new ManageEmployeeModel();
        @SuppressWarnings("unused")
        ManageEmployeeController controller = new ManageEmployeeController(view, model);

        JFrame frame = new JFrame("Employee Management");
        frame.setContentPane(view.getEmployeePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setVisible(true);
    }
}

