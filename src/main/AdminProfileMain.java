package src.main;

import javax.swing.JFrame;

import src.controller.AdminProfileController;
import src.view.AdminProfileView;

public class AdminProfileMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Profile");
        AdminProfileView view = new AdminProfileView();
        AdminProfileController controller = new AdminProfileController(view, null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.add(view.getProfilePanel());
        frame.setVisible(true);
    }
}

