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
    private final String TID;
    
    /**
     * Constructor for Payment
     * @param cinemaName name of the Cinema
     * @param movieDate selected date for Movie
     * @param movieTime selected time for Movie
     */
    public Payment(String cinemaName, String movieDate, String movieTime) {
        TID = cinemaName + generateDate(movieDate) + generateTime(movieTime);
    }
    
    /**
     * Returns a String that consist of date data to generate the TID
     * @param date booked date of the movie
     * @return formatted booked date of the movie for the TID
     */
    private String generateDate(String date){
        /*
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
        return str2;*/
        int index = 0;
        String year, day, month;
        for(int i = 0; i < date.length(); i++) {
            char c = date.charAt(i);
            if(c == ',')
                index = i;
        }
        String str = date.substring(0, index);
        String[] arr = str.split(" ");
        day = arr[0];
        month = arr[1];
        year = arr[2];
        if(month.equalsIgnoreCase("Jan")) {
            month = "01";
        }
        else if(month.equalsIgnoreCase("Feb")) {
            month = "02";
        }
        else if(month.equalsIgnoreCase("Mar")) {
            month = "03";
        }
        else if(month.equalsIgnoreCase("Apr")) {
            month = "04";
        }
        else if(month.equalsIgnoreCase("May")) {
            month = "05";
        }
        else if(month.equalsIgnoreCase("Jun")) {
            month = "06";
        }
        else if(month.equalsIgnoreCase("Jul")) {
            month = "07";
        }
        else if(month.equalsIgnoreCase("Aug")) {
            month = "08";
        }
        else if(month.equalsIgnoreCase("Sep")) {
            month = "09";
        }
        else if(month.equalsIgnoreCase("Oct")) {
            month = "10";
        }
        else if(month.equalsIgnoreCase("Nov")) {
            month = "11";
        }
        else if(month.equalsIgnoreCase("Dec")) {
            month = "12";
        }
        return year + month + day;
    }
    
    /**
     * Returns a String that consist of time data to generate the TID
     * @param time booked time of the movie for the TID
     * @return formatted booked time of the movie for the TID
     */
    private String generateTime(String time){
        //int size = time.length();
        char[] charArr = time.toCharArray();
        String hours = "", mins = "", afternoon = "";
        int i = 2;
        for (char c : charArr) {
            if (c < '0' || c > '9' && i != 0){
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
            //System.out.println(hours + ", " + mins + ", " + afternoon);
            if (i == -1)
                break;
        }
        //System.out.println(afternoon);
        if (afternoon.equals("P")) {
            int val = Integer.valueOf(hours);
            val += 12;
            hours = String.valueOf(val);
        }
        return hours + mins;
    }
    
    /**
     * Returns the TID
     * @return TID
     */
    public String getTID() {
        return TID;
    }
    /*
    public static void main(String[] args){
        Payment pay = new Payment("CNPA", "11 Feb 2015, Fri", "01:35 PM");
        System.out.println(pay.getTID());
    }*/
}