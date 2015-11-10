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
public class Payment {
    private String transactionID;
    
    public Payment(String cinemaName, String movieDate, String movieTime) {
        
    }
    
    private String generateDate(String date){
        char[] charArr = date.toCharArray();
        String str = "", str2 = "";
        int i = 2;
        for (char c : charArr){
            if (c >= '0' || c <= '9'){
                str += String.valueOf(c);
            }
            else {
                str2 = str + str2;
                i--;
                if (i == 0)
                    break;
                str = "";
            }
        }
        return str2;
    }
    
    private String generateTime(String time){
        int size = time.length();
        char[] charArr = time.toCharArray();
        int hours = Integer.valueOf(String.valueOf(charArr[0]));
    }
}
