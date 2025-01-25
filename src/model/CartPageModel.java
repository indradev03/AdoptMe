package src.model;

public class CartPageModel {
    private String itemName;
    private double unitPrice;
    private int quantity;

    public CartPageModel(String itemName, double unitPrice, int quantity) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    // Method to calculate the total price based on quantity and unit price
    public double calculateTotalPrice() {
        return quantity * unitPrice;
    }

    // Getters for itemName and unitPrice
    public String getItemName() {
        return itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
