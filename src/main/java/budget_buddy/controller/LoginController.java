package budget_buddy.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import budget_buddy.model.User;

public class LoginController {
  @FXML
  TextField userEmail;
  @FXML
  PasswordField userPwd;

  public void handleSubmit() {
    String attPassword = userPwd.getText();
    String attEmail = userEmail.getText();

    if(User.authenticate(attEmail, attPassword)){
      System.out.println("Authenticated");
    }else{
      System.out.println("Auth failed");
    }
  }

}
