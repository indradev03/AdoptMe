package src.main;

import javax.swing.*;

import src.controller.CartPageController;
import src.model.CartPageModel;
import src.view.CartPageView;

public class CartPageMain {
    public static void main(String[] args) {

        // Initialize the MVC components
        CartPageModel model = new CartPageModel(petDetails[1], Double.parseDouble(petDetails[4].replace("Rs.", "")), 0);
        CartPageView view = new CartPageView();
        CartPageController controller = new CartPageController(model, view);

        // Create JFrame for displaying the cart page
        JFrame frame = new JFrame("Cart Page");
        controller.initialize(frame, petDetails, loggedInUsername); // Initialize the controller
    }
}

