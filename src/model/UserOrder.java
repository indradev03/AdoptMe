package src.model;

import java.util.List;

public class UserOrder {
    private String username;
    private List<String[]> orderItems;

    public UserOrder(String username) {
        this.username = username;
        this.orderItems = new ArrayList<>();
    }

    public List<String[]> getOrderHistory() {
        return orderItems;
    }

    public void addOrder(String[] item) {
        orderItems.add(item);
    }
}
