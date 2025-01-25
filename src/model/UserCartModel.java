package src.model;

import java.util.ArrayList;
import java.util.List;

public class UserCartModel {
    public static final List<String[]> cartItems = new ArrayList<>(); // Shared cart items list

    public static void addItemToCart(String itemName, int quantity, double unitPrice) {
        double totalPrice = quantity * unitPrice;
        String[] cartItem = {itemName, String.valueOf(quantity), String.valueOf(unitPrice), String.format("Rs.%.2f", totalPrice)};
        cartItems.add(cartItem);
    }

    public void removeItemsFromCart(int[] selectedRows) {
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            cartItems.remove(selectedRows[i]);
        }
    }

    public List<String[]> getCartItems() {
        return cartItems;
    }

    public void savePurchaseToDatabase(String receiptNumber, String paymentMethod, double totalAmount, double paymentAmount, double change, int[] selectedRows) {
        // Similar to your previous database logic for saving purchase details.
    }
}
