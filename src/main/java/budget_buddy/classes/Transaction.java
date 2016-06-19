package budget_buddy.classes;

import java.time.Instant;
import java.util.Date;

public class Transaction {
  private Integer id;
  private Float value;
  private String description;
  private String type;
  private Date dueDate;
  private User ownerUser;
  private String createdAt;
  private String updatedAt;

  public String getUpdatedAt() {
    return updatedAt;
  }

  public Transaction setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public Transaction setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }


  public Integer getId() {
    return id;
  }

  public Transaction setId(Integer id) {
    this.id = id;
    return this;
  }

  public Float getValue() {
    return value;
  }

  public Transaction setValue(Float value) {
    this.value = value;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Transaction setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getType() {
    return type;
  }

  public Transaction setType(String type) {
    this.type = type;
    return this;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public Transaction setDueDate(String dueDate) {
    this.dueDate = Date.from(Instant.parse(dueDate));
    return this;
  }

  public Transaction setDueDate(Date dueDate) {
    this.dueDate = dueDate;
    return this;
  }



  public User getOwnerUser() {
    return ownerUser;
  }

  public Transaction setOwnerUser(User ownerUser) {
    this.ownerUser = ownerUser;
    return this;
  }

}
