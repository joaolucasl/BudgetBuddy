
package budget_buddy.model;

import budget_buddy.util.DBConn;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.*;

public class User {
  private Integer id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private Date createdAt;
  private Date updatedAt;
  static Connection CurrentConn = new DBConn().getConnection();
  static DSLContext QueryBuilder = DSL.using(CurrentConn);


  public static boolean authenticate(String email, String password) {
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
        )
        .filter(user -> {
          System.out.println(user.toString());
          return user.checkPassword(password);
        })
        .collect(Collectors.toList());

    return matchingUsers.size() >= 1;
  }

  public boolean checkPassword(String AttemptedPwd) {
    System.out.println(BCrypt.hashpw(AttemptedPwd, BCrypt.gensalt()));
    return BCrypt.checkpw(AttemptedPwd, this.password);
  }


  public Integer getId() {
    if (id == null) {
      return 0;
    }
    return id;
  }

  public User setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password, boolean shouldHash) {
    if (shouldHash) {
      this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    } else {
      this.password = password;
    }
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public User setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", createdAt=" + createdAt +
      ", updatedAt=" + updatedAt +
      '}';
  }

  public void getTransactions() {
    QueryBuilder
      .select()
      .from(table("transactions"))
      .where(field("userID").equal(this.getId()))
      .fetch()
      .forEach(rec -> System.out.println(rec.toString()));
  }

}
