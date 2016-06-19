
package budget_buddy.classes;


import budget_buddy.model.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;

public class User {
  private Integer id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private Date createdAt;
  private Date updatedAt;


  public static User authenticate(String email, String password) {
    return UserModel.authenticate(email, password);
  }

  public boolean checkPassword(String AttemptedPwd) {
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
}
