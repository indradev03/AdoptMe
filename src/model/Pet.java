package src.model;

public class Pet {
    private String id;
    private String category;
    private String imagePath;
    private String description;
    private String age;
    private String price;
    private String health;

    public Pet(String id, String category, String imagePath, String description, String age, String price, String health) {
        this.id = id;
        this.category = category;
        this.imagePath = imagePath;
        this.description = description;
        this.age = age;
        this.price = price;
        this.health = health;
    }

    // Getters and Setters for each field
    public String getId() { return id; }
    public String getCategory() { return category; }
    public String getImagePath() { return imagePath; }
    public String getDescription() { return description; }
    public String getAge() { return age; }
    public String getPrice() { return price; }
    public String getHealth() { return health; }
}

