package src.main;

import src.controller.EmployeeDashboardController;
import src.model.EmployeeDashboardModel;
import src.view.EmployeeDashboardView;

public class EmployeeDashboardPage {
    public static void main(String[] args) {
        String username = args.length > 0 ? args[0] : "Guest"; // Default to "Guest" if no username is passed
        EmployeeDashboardModel model = new EmployeeDashboardModel(username);
        EmployeeDashboardView view = new EmployeeDashboardView();
        new EmployeeDashboardController (model, view);
        view.showView();
    }
}

