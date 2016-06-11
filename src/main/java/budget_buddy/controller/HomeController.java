package budget_buddy.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class HomeController {
  @FXML
  HBox gridSection;
  @FXML
  AnchorPane sidebarSection;

  public void initialize(){
    sidebarSection.prefWidthProperty().bind(gridSection.widthProperty().multiply(0.25));

  }
}
