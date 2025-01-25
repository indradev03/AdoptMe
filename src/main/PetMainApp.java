package src.main;

import javax.swing.*;

import src.controller.PetProfileController;
import src.model.PetModel;
import src.view.PetProfileView;

public class PetMainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        PetModel petModel = new PetModel();
        PetProfileView petProfileView = new PetProfileView(frame);
        PetProfileController petProfileController = new PetProfileController(petProfileView, petModel);
        
        frame.add(petProfileView.getPetPanel());
        frame.setVisible(true);
    }
}
