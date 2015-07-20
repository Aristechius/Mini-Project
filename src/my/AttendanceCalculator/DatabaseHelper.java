/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.AttendanceCalculator;
import java.sql.*;

import java.io.File;

/**
 *
 * @author aristechius
 */
//this class is defined specially for handling the database
public class DatabaseHelper {
    
    final String DB_FILENAME = "record.db"; // Database file name
    
    // Tables and columns
    final String TABLE_STUDENTS = "students";
    final String TABLE_ATTENDANCE= "attendance";
    final String TABLE_STAFFS = "staffs";
    
    final String COL_REG_NO     = "reg_no"; 
    final String COL_FIRSTNAME  = "fistname";
    final String COL_LASTNAME   = "lastname";
    final String COL_STAFF_ID   = "staff_id";
    final String COL_ABSENTHRS  = "absent_hours";
    final String COL_DATE       =    "date";
    final String COL_STAFF_FIRSTNAME = "staff_first_name";
    final String COL_STAFF_LASTNAME = "Staff_Last_Name";
    final String COL_ATTENDANCE_ID =  "attendance_id";
    /**
     * Constructor
     */
    public DatabaseHelper(){
        // Initialize a new database if one does not exist
        if (!isDBFileAvailable()){
            initialize();
        }
    }
    
    
    /**
     * Create a new database file if it does not exist and populates it with the 
     * needed tables
     */
    private boolean initialize(){
        // If database file doesn't exist:
          
        // (1) Create the new database and open it
        
    Connection conn = null;
       
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILENAME);
      System.out.println("Opened database successfully");

      stmt = conn.createStatement();
      //creating the table students
      String sql = "CREATE TABLE " + TABLE_STUDENTS +
                   "(" + 
                      COL_REG_NO + " INT PRIMARY KEY NOT NULL, " +
                      COL_FIRSTNAME + " TEXT NOT NULL, " + 
                      COL_LASTNAME + " TEXT NOT NULL, " + 
                      COL_STAFF_ID + " INT NOT NULL "+
                   ");"; 
      //creating table Attendance 
      sql = "CREATE TABLE " + TABLE_ATTENDANCE +
                   "(" + 
                      COL_ATTENDANCE_ID + " INT PRIMARY KEY NOT NULL, " +
                      COL_REG_NO + " INT NOT NULL, " + 
                      COL_ABSENTHRS + " INT NOT NULL, " + 
                      COL_DATE + "INT NOT NULL, " +
                      COL_STAFF_ID + " INT NOT NULL "+
                   ");";
       
        //creating table staffs
        sql = "CREATE TABLE " + TABLE_STAFFS +
                   "(" + 
                      COL_STAFF_ID + " INT PRIMARY KEY NOT NULL, " +
                      COL_STAFF_FIRSTNAME + " INT NOT NULL, " + 
                      COL_STAFF_LASTNAME + " INT NOT NULL, " + 
                      COL_STAFF_ID + " INT NOT NULL "+
                   ");"; 
      
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
    } catch ( ClassNotFoundException | SQLException e ) {
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
        File f = new File(DB_FILENAME);
        return (f.exists() && !f.isDirectory());
    }
   
   
}
