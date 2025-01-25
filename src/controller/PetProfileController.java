package src.controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.ImageIcon;

import src.model.Pet;
import src.model.PetModel;
import src.view.PetProfileView;

public class PetProfileController {
    private PetProfileView view;
    private PetModel model;
    
    public PetProfileController(PetProfileView view, PetModel model) {
        this.view = view;
        this.model = model;

        view.addDogsButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPetsByCategory("Dogs");
            }
        });

        view.addCatsButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPetsByCategory("Cats");
            }
        });

        view.addBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous page or dashboard
            }
        });
    }

    private void showPetsByCategory(String category) {
        List<Pet> pets = model.getPetsByCategory(category);
        view.updatePetList(pets);
    }

    private void showPetDetails(String petId) {
        Pet pet = model.getPetById(petId);
        if (pet != null) {
            // Update pet details in view
            ImageIcon petImageIcon = new ImageIcon(new ImageIcon(pet.getImagePath()).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
            view.showPetDetails(pet.getId(), pet.getDescription(), pet.getAge(), pet.getPrice(), pet.getHealth(), petImageIcon);
        }
    }
}
