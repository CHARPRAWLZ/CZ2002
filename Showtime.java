package CZ2002;

import java.util.Calendar;

/**
 * Showtime : Class Contains information on the movie that's screening, the
 * cinema and its cineplex where it is screened in, and the timing of the
 * screening.
 *
 * @author Hocks
 */
public class Showtime {

    private Cineplex cineplex;
    private Cinema cinema;
    private Movie movie;
    private Calendar timing;
    private int status;     // 0 - Standby; 1 - Showing; 2 - Over

    /* Constructors */
    /**
     * Constructor of Showtime class. 'has a' relationship with Cineplex, Cinema
     * and Movie.
     *
     * @param cineplex
     * @param cinemaRoom
     * @param movie
     * @param timing
     * @param status
     */
    public Showtime(Cineplex cineplex, int cinemaRoom, Movie movie, 
            Calendar timing, int status) {
        this.cineplex = cineplex;
        this.cinema = this.cineplex.getCinemaList().get(cinemaRoom - 1);
        this.movie = movie;
        this.timing = timing;
        this.status = status;
    }
    
    public Showtime(Cineplex cineplex, int cinemaRoom, Movie movie, 
            Calendar timing){
        this(cineplex, cinemaRoom, movie, timing, 0);
    }
    /* end of Constructors */

    /* Accessors */
    /**
     * Returns cinema name.
     *
     * @return Cinema Name
     */
    public String getCinemaName() {
        return cinema.getName();
    }

    /**
     * Returns cinema type.
     *
     * @return Cinema Type
     */
    public String getCinemaType() {
        return cinema.getCinemaType();
    }

    /**
     * Returns cineplex name.
     *
     * @return Cineplex Name
     */
    public String getCineplexName() {
        return cineplex.getName();
    }

    /**
     * Returns movie name.
     *
     * @return Movie Name
     */
    public String getMovieName() {
        return movie.getTitle();
    }

    /**
     * Returns Movie ID.
     *
     * @return Movie ID
     */
    public String getMovieID() {
        return movie.getMovieID();
    }

    /**
     * Returns the time of the show in the format "hh:mm AM/PM".
     *
     * @return Show Time
     */
    public String getTiming() {
        String date[] = timing.toString().split(" ");
        Boolean morning = true;
        int hour = Integer.valueOf(date[3].substring(0, 2));
        if (hour / 12 == 1) {
            hour %= 12;
            morning = false;
        }
        String mins = date[3].substring(2, 5);
        return hour + mins + (morning ? " AM" : " PM");
    }

    /**
     * Returns the date of the show in the format "dd mth yyyy, day". Example:
     * 01 Feb 2015, Mon
     *
     * @return Show Date
     */
    public String getDate() {
        int year = this.timing.get(Calendar.YEAR);
        int month = this.timing.get(Calendar.MONTH);
        int date = this.timing.get(Calendar.DATE);
        int day = this.timing.get(Calendar.DAY_OF_WEEK);
        String monthStr, dayStr;
        
        switch(month){
            case 0: monthStr = "Jan"; break;
            case 1: monthStr = "Feb"; break;
            case 2: monthStr = "Mar"; break;
            case 3: monthStr = "Apr"; break;
            case 4: monthStr = "May"; break;
            case 5: monthStr = "Jun"; break;
            case 6: monthStr = "Jul"; break;
            case 7: monthStr = "Aug"; break;
            case 8: monthStr = "Sep"; break;
            case 9: monthStr = "Oct"; break;
            case 10: monthStr = "Nov"; break;
            case 11: monthStr = "Dec"; break;
            default: monthStr = ""; break;
        }
        
        switch(day){
            case 1: dayStr = "Sunday"; break;
            case 2: dayStr = "Monday"; break;
            case 3: dayStr = "Tuesday"; break;
            case 4: dayStr = "Wednesday"; break;
            case 5: dayStr = "Thursday"; break;
            case 6: dayStr = "Friday"; break;
            case 7: dayStr = "Saturday"; break;
            default: dayStr = ""; break;
        }
        
        return date + " " + monthStr + " " + year + ", " + dayStr;
    }
    
    /**
     * Returns the status of the showtime.
     * @return 
     */
    public String getStatus(){
        switch (this.status){
            case 0: return "Standby";
            case 1: return "Showing";
            case 2: return "Over";
            default: return "ERROR";
        }
    }
    /* end of Accessors */

    /* Mutators */
    /**
     * Sets the date and time. Date is to be in the format dd/mm/yyyy. Time is
     * to be in the format 0000 (hrs).
     *
     * @param date (dd/mm/yyyy)
     * @param time (2359)
     * @return True if setDate is successful; False if unsuccessful.
     */
    public boolean setDate(String date, int time) {
        String dateArr[] = date.split("/");
        if (time < 0 || time / 100 >= 24 || time % 100 >= 60 || dateArr.length != 3) {
            return false;
        }
        timing.set(Calendar.YEAR, Integer.valueOf(dateArr[2]));
        timing.set(Calendar.MONTH, Integer.valueOf(dateArr[1]) - 1);
        timing.set(Calendar.DATE, Integer.valueOf(dateArr[0]));
        timing.set(Calendar.HOUR, time / 100);
        timing.set(Calendar.MINUTE, time % 100);
        return true;
    }

    /**
     * Checks the content of str to determine if it is date or time.
     *
     * @param str
     * @return True if setDate is successful; False if unsuccessful.
     */
    public boolean setDate(String str) {
        if (str.isEmpty()) {
            return false;
        }
        String strArr[] = str.split("/");
        if (strArr.length == 3) {
            return setDate(str, timing.get(Calendar.HOUR) * 100 
                    + timing.get(Calendar.MINUTE));
        } else if (this.isInt(str)) {
            String date = "";
            date += timing.get(Calendar.DATE) + "/";
            date += (timing.get(Calendar.MONTH) + 1) + "/";
            date += timing.get(Calendar.YEAR);
            return setDate(date, Integer.valueOf(str));
        } else {
            return false;
        }
    }
    
    /**
     * Sets the showtime showing status.
     * 0 - Standby; 1 - Showing; 2 - Over
     * @param status 
     */
    public void setStatus(int status){
        this.status = status;
    }
    
    /**
     * Advance the showtime showing status.
     * If the status is 'Standby', the status is changed to 'Showing'.
     * If the status is 'Showing', the status is changed to 'Over'.
     * If the status is 'Over', nothing happens.
     */
    public void advanceStatus(){
        if (this.status < 2)
            this.status++;
    }
    /* end of Mutators */

    /**
     * Checks if string is int.
     *
     * @param str
     * @return True if string is int; false if otherwise.
     */
    private boolean isInt(String str) {
        char charArr[] = str.toCharArray();
        for (char c : charArr) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
