package budget_buddy.controller;

import budget_buddy.classes.User;
import budget_buddy.model.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
  @FXML
  HBox gridSection;
  @FXML
  AnchorPane sidebarSection;

  @Override
  public void initialize(URL location, ResourceBundle resources){
    sidebarSection.prefWidthProperty().bind(gridSection.widthProperty().multiply(0.25));

    User loggedUser = LoginController.getInstance().getLoggedUser();
    System.out.println(UserModel.getTransactions(loggedUser.getId()).toString());
  }
}
