/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CZ2002;

/**
 *
 * @author calvinlee
 */
public class Booking {
    private String custName;
    private String custMobileNumber;
    private String custEmailAddr;
    private int custAge;
    
    public Booking(String custName, String custMobileNumber, 
            String custEmailAddr, int custAge) {
        this.custName = custName;
        this.custMobileNumber = custMobileNumber;
        this.custEmailAddr = custEmailAddr;
        this.custAge = custAge;
    }
}
