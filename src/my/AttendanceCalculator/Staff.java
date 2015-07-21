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
public class Staff {
    private String firstName;
    private String lastName;
    private String password;
    private int  id;
    
    /*
     * Constructor
     */
    public Staff(){
        firstName = null;
        lastName = null;
        password = null;
        id = -1;
    }
    
    public Staff(int id, String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.id = id;
    }
    
    /*
     * Getters
     */
    public String getFirstName(){ return this.firstName; }
    public String getLastName(){ return this.lastName; }
    public String getPassword(){ return this.password; }
    public int getID(){ return this.id; }
    
    /*
     * Setters
     */
    public void setFirstName(String firstName){ this.firstName = firstName; }
    public void setLastName(String lastName){ this.lastName = lastName; }
    public void setPassword(String password){ this.password = password; }
    public void setID(int id){ this.id = id; }
}
