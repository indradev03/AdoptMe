package src.main;

import src.controller.AdminDashboardController;
import src.model.AdminDashboardModel;
import src.view.AdminDashboardView;

public class AdminDashboardApp {
    public static void main(String[] args) {
        String adminUsername = args.length > 0 ? args[0] : "Guest";
        AdminDashboardModel model = new AdminDashboardModel(adminUsername);
        AdminDashboardView view = new AdminDashboardView();
        new AdminDashboardController(model, view);
    }
}
