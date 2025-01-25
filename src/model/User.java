package src.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.*;

public class User {
    private String username;
    private List<String[]> cartItems;

    public User(String username, String updatedLastName, String updatedUsername, String updatedEmail, String updatedPassword, String updatedPhone) {
        this.username = username;
        this.cartItems = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<String[]> getCartItems() {
        return cartItems;
    }

    public void addToCart(String item) {
        cartItems.add(new String[] { item });
    }

    public void clearCart() {
        cartItems.clear();
    }

    public String getFirstName() {
    }

    public String getLastName() {
;
    }

    public String getEmail() {

    }

    public String getPassword() {

    }

    public String getPhone() {

    }

}

