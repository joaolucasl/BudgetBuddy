package budget_buddy.controller;

import budget_buddy.classes.Transaction;
import budget_buddy.classes.User;
import budget_buddy.model.TransactionModel;
import budget_buddy.model.UserModel;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
  @FXML
  HBox gridSection;
  @FXML
  AnchorPane sidebarSection;

  @FXML
  TableView transactionsTable;

  private ObservableList<Transaction> transactionList;

  @Override
  public void initialize(URL location, ResourceBundle resources){
    sidebarSection.prefWidthProperty().bind(gridSection.widthProperty().multiply(0.35));

    User loggedUser = LoginController.getInstance().getLoggedUser();

    // Defining what the columns will look like

    //  Description column
    TableColumn<Transaction, String> columnDescription = new TableColumn<>("Descrição");
    columnDescription.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescription()));

    //  Value Column
    TableColumn<Transaction, Float> columnValue = new TableColumn<>("Valor");
    columnValue.setCellValueFactory(c -> new SimpleFloatProperty(c.getValue().getValue()).asObject());

    //  Due Date Column
    TableColumn<Transaction, String> columnDueDate = new TableColumn<>("Data");
    columnDueDate.setCellValueFactory(c -> new SimpleStringProperty(
      new SimpleDateFormat("dd/MM/yy").format(c.getValue().getDueDate())
    ));
    // joao.lucas.lucchetta@gmail.com

    //  Due Date Column
    TableColumn<Transaction, String> columnType = new TableColumn<>("Tipo ");
    columnType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType().toUpperCase()));

    //  And finally adding them all to the table
    transactionsTable
      .getColumns()
      .addAll(columnDescription, columnValue, columnDueDate, columnType);

    transactionsTable.setRowFactory((tableView) -> {
      TableRow<Transaction> row = new TableRow<Transaction>() {
        @Override
        protected void updateItem(Transaction transaction, boolean empty) {
          super.updateItem(transaction, empty);

          if (!empty) {
            if (transaction.getType().toLowerCase() == "saida") {
              this.setStyle("-fx-background-color:  linear-gradient(to bottom, rgba(230,185,184,1) 0%, rgba(178,143,142,1) 100%);");
            } else if (transaction.getType().toLowerCase() == "entrada") {
              this.setStyle("-fx-background-color:  linear-gradient(to bottom, rgba(164,179,87,1) 0%, rgba(117,137,12,1) 100%);");
            }
          }
        }
      };

      return row;
    });

    ArrayList<Transaction> originalTransactionList = (ArrayList<Transaction>) TransactionModel.findByUser(loggedUser.getId());
    transactionList = FXCollections.observableArrayList(originalTransactionList);
    transactionsTable.setItems(transactionList);

  }
}
