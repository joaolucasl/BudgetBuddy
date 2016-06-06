package budget_buddy.util;


import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConn {
  protected Connection conn;

  public DBConn() {
    String dbFilePath = getDbPath().toString();
    try {
      Class.forName("org.sqlite.JDBC");
      setupDBFile();
      this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath, "budget_buddy", "budget_buddy");
    } catch (Exception ex) {
      Logger.getLogger("DBConn").log(Level.SEVERE, "It was not possible to connect to the database. \n" + ex.getLocalizedMessage());
    }
  }

  public Connection getConnection() {
    return this.conn;
  }

  private boolean dbFileExists() {
    File dbFile = new File(getDbPath().toString());
    return dbFile.exists();
  }

  private Path getDbPath() {
    String homePath = System.getProperty("user.home");
    Path dbFilePath = Paths.get(homePath, "BudgetBuddyFiles", "budget_buddy.db");
    System.out.println(dbFilePath.toString());
    return dbFilePath;
  }

  private boolean setupDBFile() {
    //  If the DB doesn't exist yet in the filesystem we copy the one present in the jar before using the DB
    Path dbFilePath = getDbPath();
    Path dbFolder = dbFilePath.getParent();
    if (!dbFileExists()) {
      try {
        //  The DB file from the jar
        InputStream dbFileFromJar = getClass().getResourceAsStream("/budget_buddy.db");

        // If there's no BudgetBuddy folder, we need to create it first
        if(!Files.isDirectory(dbFolder)){
          Files.createDirectories(dbFolder);
        }

        Files.copy(dbFileFromJar, getDbPath());
      } catch (Exception ex) {
        ex.printStackTrace();
        return false;
      }
    }
    return true;
  }
}
