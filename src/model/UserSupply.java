package src.model;

public class UserSupply {
    private String id;
    private String category;
    private String imagePath;
    private String description;
    private String quantity;
    private String price;
    private String availability;

    public UserSupply(String id, String category, String imagePath, String description, String quantity, String price, String availability) {
        this.id = id;
        this.category = category;
        this.imagePath = imagePath;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.availability = availability;
    }

    // Getters and setters for each field
    public String getId() { return id; }
    public String getCategory() { return category; }
    public String getImagePath() { return imagePath; }
    public String getDescription() { return description; }
    public String getQuantity() { return quantity; }
    public String getPrice() { return price; }
    public String getAvailability() { return availability; }
}
