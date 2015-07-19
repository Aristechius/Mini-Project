/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.AttendanceCalculator;

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
        return true;
    }
    
    /**
     * Check if the database file exists
     */
    private boolean isDBFileAvailable(){
        return true;
    }
   
   
}
