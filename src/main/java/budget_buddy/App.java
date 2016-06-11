package budget_buddy;


import java.io.IOException;
import java.util.logging.*;


import budget_buddy.util.ViewLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * This is the main class for BudgetBuddy. It implements the JavaFX's abstract Application class, and is the basis for
 * an MVC-like structure. At first it launches the LoginScreen, and the controllers define the subsequent steps.
 *
 * @author João Lucas Lucchetta
 * @version 0.0.1
 */
public class App extends Application {
  /**
   * Launches the standalone JavaFX application starting from the scene defined in the {@link #start(Stage) start()}
   * method.
   *
   * @param args The arguments passed to the application
   */
  public static void main(String[] args) {
    Application.launch(args);
  }

  /**
   * The main entry point for our application. The start method is called after the init method has returned, and after
   * the system is ready for the application to begin running.
   *
   * @param primaryStage the primary stage which will be shown once the application is run.
   */
  @Override
  public void start(Stage primaryStage) throws IOException {
    Pane loginPane = ViewLoader.LoadView("/budget_buddy/view/LoginScreen.fxml");
    Scene loginScene = new Scene(loginPane);

    primaryStage.setScene(loginScene);
    primaryStage.setTitle("Budget Buddy - Login");
    primaryStage.setResizable(false);
    primaryStage.minHeightProperty().set(600);
    primaryStage.minWidthProperty().set(800);
    primaryStage.show();
  }
}