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
    final String TABLE_STUDENTS    = "students";
    final String TABLE_ATTENDANCE  = "attendances";
    final String TABLE_STAFFS      = "staffs";
    
    final String COL_REG_NO     = "reg_no";
    final String COL_FIRSTNAME  = "firstname";
    final String COL_LASTNAME   = "lastname";
    final String COL_STAFF_ID   = "staff_id";
    final String COL_ABSENT_HRS = "absent_hours";
    final String COL_DATE       =  "date";
    final String COL_STAFF_FIRSTNAME = "firstname";
    final String COL_STAFF_LASTNAME  = "lastname";
    final String COL_STAFF_PASSWORD  = "password";
    final String COL_ATTENDANCE_ID   =  "attendance_id";
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
            String sql;
            
            //creating table staffs
            sql = "CREATE TABLE " + TABLE_STAFFS +
                    "(" +
                    COL_STAFF_ID + " INT PRIMARY KEY NOT NULL, " +
                    COL_STAFF_FIRSTNAME + " TEXT NOT NULL, " +
                    COL_STAFF_LASTNAME + " TEXT NOT NULL, " +
                    COL_STAFF_PASSWORD + " TEXT NOT NULL " +
                    ");";
            stmt.executeUpdate(sql);
            
            //creating the table students
            sql = "CREATE TABLE " + TABLE_STUDENTS +
                    "(" +
                    COL_REG_NO + " INT PRIMARY KEY NOT NULL, " +
                    COL_FIRSTNAME + " TEXT NOT NULL, " +
                    COL_LASTNAME + " TEXT NOT NULL, " +
                    COL_STAFF_ID + " INT NOT NULL, "+
                    "FOREIGN KEY ("+ COL_STAFF_ID+ ") REFERENCES "+
                    TABLE_STAFFS +"("+COL_STAFF_ID+")" +
                    ");";
            stmt.executeUpdate(sql);
            
            //creating table Attendance
            sql = "CREATE TABLE " + TABLE_ATTENDANCE +
                    "(" +
                    COL_ATTENDANCE_ID + " INT PRIMARY KEY NOT NULL, " +
                    COL_REG_NO + " INT NOT NULL, " +
                    COL_ABSENT_HRS + " INT NOT NULL, " +
                    COL_DATE + " TEXT NOT NULL, " +
                    COL_STAFF_ID + " INT NOT NULL, "+
                    "FOREIGN KEY ("+ COL_REG_NO+ ") REFERENCES "+
                    TABLE_STUDENTS +"("+COL_REG_NO+"), " +
                    "FOREIGN KEY ("+ COL_STAFF_ID+ ") REFERENCES "+
                    TABLE_STAFFS +"("+COL_STAFF_ID+")" +
                    ");";
            stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
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
     * @param staff -- staff to be authenticated
     * @return true if the credentials are correct and false otherwise
     */
    public boolean isStaffValid(Staff staff){
        // Connect to the database
        Connection conn = getDBConnection();
        
        try {
            // Query the DB to confirm the staff's details
            String sql = "SELECT COUNT(*) AS result FROM "+ TABLE_STAFFS +
                    " WHERE " + COL_STAFF_ID + "=? AND "
                    + COL_STAFF_PASSWORD + "=?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, staff.getID());
            stmt.setString(2, staff.getPassword());
            
            
            ResultSet rs = stmt.executeQuery();
            conn.close();
            
            return (rs.next() && rs.getInt("result") > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    public void addStaff(Staff staff){
        //connecting to database
        Connection conn= getDBConnection();
        
        try{
            //switching to auto commit
            conn.setAutoCommit(false);
            
            String sql= "INSERT INTO" + TABLE_STAFFS +" (" + COL_STAFF_ID +
                    ", " +COL_STAFF_FIRSTNAME+", "+COL_STAFF_LASTNAME+" ,"
                    + COL_STAFF_PASSWORD+") " + "VALUES(?,?,?,? );";
            
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setInt(1,staff.getID());
            stmt.setString(2,staff.getFirstName());
            stmt.setString(3,staff.getLastName());
            stmt.setString(4,staff.getPassword());
            
            
            stmt.executeUpdate();
            conn.commit();
            stmt.close();
            
            conn.close();
            
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() +":" + e.getMessage());
            System.exit(0);
        }
        System.out.println("added a new staff successfully");
    }
}


