package CZ2002;

/**
 * This is an entity class which is a user with admin privileges
 * @author Zach
 */
public class Employee extends User{
    //constructor for employee
    /**
    * Constructs an instance of class Employee.
    * @param user
    * @param pw
    * @param name
    * @param email
    * @param number
    */
    public Employee(String username, String password, String name, String email, String mobileno){
        super(username,password,name,email,mobileno);    
    }
} 
    
