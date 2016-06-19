package budget_buddy.model;

import budget_buddy.classes.Transaction;
import budget_buddy.util.DBConn;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class TransactionModel {
  static Connection CurrentConn = new DBConn().getConnection();
  static DSLContext QueryBuilder = DSL.using(CurrentConn);

  public static List<Transaction> findByUser(Integer userID) {
    return QueryBuilder
      .select()
      .from(table("transactions"))
      .where(field("userID").equal(userID))
      .fetch()
      .stream()
      .map(r -> new Transaction()
        .setId((Integer) r.getValue("id"))
        .setValue((Float) r.getValue("value"))
        .setDescription((String) r.getValue("description"))
        .setType((String) r.getValue("type"))
        .setDueDate((String) r.getValue("dueDate"))
        .setOwnerUser(UserModel.findById((Integer) r.getValue("userID")))
        .setCreatedAt((String) r.getValue("created_at"))
        .setUpdatedAt((String) r.getValue("update_at"))
      )
      .collect(Collectors.toList());
  }
}
