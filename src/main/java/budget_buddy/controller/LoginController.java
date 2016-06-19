package budget_buddy.controller;


import budget_buddy.model.UserModel;
import budget_buddy.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import budget_buddy.classes.User;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
  @FXML
  TextField userEmail;
  @FXML
  PasswordField userPwd;
  @FXML
  Label errorLabel;

  private static LoginController instance;
  private User loggedUser;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    instance = this;
  }

  public static LoginController getInstance() {
    return instance;
  }

  public void handleSubmit(ActionEvent event) {
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    String attPassword = userPwd.getText();
    String attEmail = userEmail.getText();

    loggedUser = UserModel.authenticate(attEmail, attPassword);
    if (loggedUser != null) {
      //  Transition to new the HomeView
      Pane homeScreen = ViewLoader.LoadView("/budget_buddy/view/HomeScreen.fxml");
      window.setScene(new Scene(homeScreen));
      window.setResizable(true);
    } else {
      errorLabel.textProperty().set("Credenciais inválidas.");
    }
  }

  public User getLoggedUser() {
    return loggedUser;
  }
}
