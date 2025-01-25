package src.main;

import javax.swing.JFrame;

import src.controller.ManageSuppliesController;
import src.model.SupplyDAO;
import src.view.ManageSuppliesView;

public class SupplyMain {
    public static void main(String[] args) {
        ManageSuppliesView view = new ManageSuppliesView();
        SupplyDAO model = new SupplyDAO();
        new ManageSuppliesController(view, model);

        JFrame frame = new JFrame("Manage Supplies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view.getSuppliesPanel());
        frame.setSize(900, 550);
        frame.setVisible(true);
    }
}

