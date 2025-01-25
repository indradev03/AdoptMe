package src.main;

import src.controller.SignUpController;
import src.model.UserModel;
import src.view.SignUpView;

public class AdoptMeSignUpPage {
    public static void main(String[] args) {
        UserModel model = new UserModel();
        SignUpView view = new SignUpView();
        new SignUpController(model, view);

    }
}