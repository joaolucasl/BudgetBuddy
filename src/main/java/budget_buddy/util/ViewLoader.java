package budget_buddy.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewLoader {
  static public Pane LoadView(String view){
    Pane root = new Pane();
    try {
      FXMLLoader loader = new FXMLLoader(
        ViewLoader.class.getResource(view)
      );
      root = (Pane) loader.load();
    }catch (IOException ex){
      Logger.getLogger(ViewLoader.class.getName()).log(Level.SEVERE, null, ex);
    }
    return root;
  }
}
