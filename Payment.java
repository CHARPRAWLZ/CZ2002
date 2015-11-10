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
    private String TID;
    
    public Payment(String cinemaName, String movieDate, String movieTime) {
        TID = cinemaName + generateDate(movieDate) + generateTime(movieTime);
    }
    
    private String generateDate(String date){
        char[] charArr = date.toCharArray();
        String str = "", str2 = "";
        int i = 3;
        for (char c : charArr){
            if (c >= '0' && c <= '9'){
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
        String hours = "", mins = "", afternoon = "";
        int i = 2;
        for (char c : charArr) {
            if (c < '0' || c > '9'){
                i--;
            }
            else {
                switch(i){
                    case 2: hours += String.valueOf(c);
                        break;
                    case 1: mins += String.valueOf(c);
                        break;
                    case 0: afternoon += String.valueOf(c);
                        i--;
                        break;
                }
            }
            System.out.println(hours + ", " + mins + ", " + afternoon);
            if (i == -1)
                break;
        }
        System.out.println(afternoon);
        if (afternoon.equals("P")) {
            int val = Integer.valueOf(hours);
            val += 12;
            hours = String.valueOf(val);
        }
        return hours + mins;
    }
    
    public String getTID() {
        return TID;
    }
    
    public static void main(String[] args){
        Payment pay = new Payment("CNPA", "11 Feb 2015, Fri", "01:35 PM");
        System.out.println(pay.getTID());
    }
}
