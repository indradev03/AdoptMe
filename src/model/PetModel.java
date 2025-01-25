package src.model;

import java.util.List;
import java.util.ArrayList;

public class PetModel {
    private List<Pet> pets;

    public PetModel() {
        this.pets = new ArrayList<>();
        // Add pet data here as in your original array
        pets.add(new Pet("1", "Dogs", "Screenshot 2024-12-05 204629.png", "Labrador Retriever", "3 Years", "5000", "Healthy"));
        pets.add(new Pet("2", "Dogs", "dog2.png", "Bulldog", "2 Years", "600", "Healthy"));
        // Add other pets similarly
    }

    public List<Pet> getPetsByCategory(String category) {
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getCategory().equals(category)) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    public Pet getPetById(String id) {
        for (Pet pet : pets) {
            if (pet.getId().equals(id)) {
                return pet;
            }
        }
        return null;
    }
}
