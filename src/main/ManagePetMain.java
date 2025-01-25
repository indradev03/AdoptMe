package src.main;

// Main.java - Starting point for running the application.

import javax.swing.*;

import src.model.ManagePetModel;

import src.view.ManagePetView;

public class ManagePetMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pet Management");
        ManagePetView view = new ManagePetView();
        ManagePetModel model = new ManagePetModel();
        PetController controller = new PetController(view, model);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.add(view);
        frame.setVisible(true);
    }
}

