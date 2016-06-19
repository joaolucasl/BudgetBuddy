package budget_buddy.model;

import budget_buddy.classes.User;
import budget_buddy.util.DBConn;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class UserModel {
  static Connection CurrentConn = new DBConn().getConnection();
  static DSLContext QueryBuilder = DSL.using(CurrentConn);


  public static User authenticate(String email, String password) {
    List<User> matchingUsers =
      QueryBuilder
        .select()
        .from(table("users"))
        .where(field("users.email").equal(email))
        .fetch()
        .stream()
        .map(r -> new User()
          .setId((Integer) r.getValue("id"))
          .setEmail((String) r.getValue("email"))
          .setPassword((String) r.getValue("password"), false)
          .setFirstName((String) r.getValue("first_name"))
          .setLastName((String) r.getValue("last_name"))
        )
        .filter(user -> user.checkPassword(password))
        .collect(Collectors.toList());

    if (matchingUsers.size() >= 1) {
      return matchingUsers.get(0);
    }
    return null;
  }

  public static User findByEmail(String email) {
    return QueryBuilder
      .select()
      .from(table("users"))
      .where(field("email").equal(email))
      .fetch()
      .stream()
      .map(r -> new User()
        .setId((Integer) r.getValue("id"))
        .setEmail((String) r.getValue("email"))
        .setPassword((String) r.getValue("password"), false)
        .setFirstName((String) r.getValue("first_name"))
        .setLastName((String) r.getValue("last_name"))
      )
      .collect(Collectors.toList())
      .get(0);
  }
  public static User findById(Integer id) {
    return QueryBuilder
      .select()
      .from(table("users"))
      .where(field("id").equal(id))
      .fetch()
      .stream()
      .map(r -> new User()
        .setId((Integer) r.getValue("id"))
        .setEmail((String) r.getValue("email"))
        .setPassword((String) r.getValue("password"), false)
      )
      .collect(Collectors.toList())
      .get(0);
  }

  public static void getTransactions(int userID) {
    QueryBuilder
      .select()
      .from(table("transactions"))
      .where(field("userID").equal(userID))
      .fetch()
      .forEach(rec -> System.out.println(rec.toString()));
  }
}

