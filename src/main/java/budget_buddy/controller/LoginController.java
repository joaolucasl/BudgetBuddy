package budget_buddy.controller;


import budget_buddy.util.ViewLoader;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import budget_buddy.model.User;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
  @FXML
  TextField userEmail;
  @FXML
  PasswordField userPwd;
  @FXML
  Label errorLabel;

  public void initialize(URL location, ResourceBundle resources){

  }

  public void handleSubmit(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    String attPassword = userPwd.getText();
    String attEmail = userEmail.getText();

    if(User.authenticate(attEmail, attPassword)){
      //  Transition to new the HomeView
      Pane homeScreen = ViewLoader.LoadView("/budget_buddy/view/HomeScreen.fxml");
      window.setScene(new Scene(homeScreen));
      window.setResizable(true);
    }else{
      errorLabel.textProperty().set("Credenciais inválidas.");
    }
  }

}
