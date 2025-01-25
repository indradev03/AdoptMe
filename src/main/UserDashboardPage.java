package src.main;

import src.controller.UserDashboardController;
import src.model.User;
import src.view.UserDashboardView;

public class UserDashboardPage {
    public static void main(String[] args) {
        User user = new User("Guest", null, null, null, null, null);
        UserDashboardView view = new UserDashboardView();
        UserDashboardController controller = new UserDashboardController(user, view);
        controller.show();
    }
}
