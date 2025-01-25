package src.main;

import src.controller.LoginController;
import src.model.LoginModel;
import src.view.LoginView;

public class AdoptMeLoginPage {
        public static void main(String[] args) {
        LoginModel model = new LoginModel();
        LoginView view = new LoginView();
        new LoginController(model, view);
        }
    }