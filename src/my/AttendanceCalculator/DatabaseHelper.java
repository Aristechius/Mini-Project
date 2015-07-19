/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.AttendanceCalculator;
import java.sql.*;
/**
 *
 * @author aristechius
 */
//this class is defined specially for handling the database
public class DatabaseHelper {
    
    final String DB_FILENAME = "record.db"; // Database file name
    
    /**
     * Constructor
     */
    public DatabaseHelper(){

    }
    
    
    /**
     * Create a new database file if it does not exist and populates it with the 
     * needed tables
     */
    private boolean initialize(){
        // If database file doesn't exist:
          
        // (1) Create the new database and open it
        
    Connection c = null;
       
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:record.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE STUDENTS " +
                   "(REGNO INT PRIMARY KEY     NOT NULL," +
                   " FIRSTNAME         TEXT    NOT NULL, " + 
                   " LASTNAME          TEXT     NOT NULL, " + 
                   " STAFFID           INT NOT NULL, "; 
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  
    
        // (3) Create the Staffs table
        
        // (4) Create the Attendees table

        return true;
    }
    
    /**
     * Check if the database file exists
     */
    private boolean isDBFileAvailable(){
        return true;
    }
   
   
}
