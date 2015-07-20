/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.AttendanceCalculator;
import java.sql.*;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    final String COL_DATE       =  " date ";
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
        // Create the new database and open it
        try {
            Connection conn = getDBConnection();
            Statement stmt = conn.createStatement();

            //creating the table students
            String sql = "CREATE TABLE " + TABLE_STUDENTS +
                         "(" + 
                            COL_REG_NO + " INT PRIMARY KEY NOT NULL, " +
                            COL_FIRSTNAME + " TEXT NOT NULL, " + 
                            COL_LASTNAME + " TEXT NOT NULL, " + 
                            COL_STAFF_ID + " INT NOT NULL "+
                         ");"; 
            stmt.executeUpdate(sql);

            //creating table Attendance 
            sql = "CREATE TABLE " + TABLE_ATTENDANCE +
                         "(" + 
                            COL_ATTENDANCE_ID + " INT PRIMARY KEY NOT NULL, " +
                            COL_REG_NO + " INT NOT NULL, " + 
                            COL_ABSENTHRS + " INT NOT NULL, " + 
                            COL_DATE + "INT NOT NULL, " +
                            COL_STAFF_ID + " INT NOT NULL "+
                         ");";
             stmt.executeUpdate(sql);

              //creating table staffs
              sql = "CREATE TABLE " + TABLE_STAFFS +
                         "(" + 
                            COL_STAFF_ID + " INT PRIMARY KEY NOT NULL, " +
                            COL_STAFF_FIRSTNAME + " INT NOT NULL, " + 
                            COL_STAFF_LASTNAME + " INT NOT NULL " + 

                            ");"; 
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        
        System.out.println("Table created successfully");
 
        return true;
    }
    
    /**
     * Check if the database file exists
     */
    private boolean isDBFileAvailable(){
        File f = new File(DB_FILENAME);
        return (f.exists() && !f.isDirectory());
    }
    
    /**
     *  Connect to the database
     */
    private Connection getDBConnection(){
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILENAME);
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return conn;
    }
   
    /**
     * Check if the staff has valid credentials
     */
    public boolean isStaffValid(Staff staff){
        // Connect to the database
        Connection conn = getDBConnection();
        
        try {
            Statement stmt = conn.createStatement();
            
            // Query the DB to confirm the staff's details
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //
        return false;
    }
   
}
