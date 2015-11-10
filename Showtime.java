package CZ2002;

import java.util.Date;

public class Showtime {

    private Cineplex cineplex;
    private Cinema cinema;
    private Movie movie;
    private Date timing;

    /* Constructors */
    public Showtime(Cineplex cineplex, int CinemaRoom, Movie movie, Date timing) {
        this.cineplex = cineplex;
        this.cinema = this.cineplex.getCinemaList().get(CinemaRoom - 1);
        this.movie = movie;
        this.timing = timing;
    }
    /* end of Constructors */

    /* Accessors */
    public String getCinemaName() {
        return cinema.getName();
    }

    public String getCinemaType() {
        return cinema.getCinemaType();
    }

    public String getMovieName() {
        return movie.getTitle();
    }

    public String getMovieID() {
        return movie.getMovieID();
    }

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

    public String getDate() {
        String date[] = timing.toString().split(" ");
        return date[2] + " " + date[1] + " " + timing.getYear() + ", "
                + date[0];
    }
    /* end of Accessors */

    /* Mutators */
    /**
     * Sets the date and time. Date is to be in the format dd/mm/yyyy. Time is
     * to be in the format 0000 (hrs).
     *
     * @param date (dd/mm/yyyy)
     * @param time (2359)
     */
    public boolean setDate(String date, int time) {
        String dateArr[] = date.split("/");
        if (time < 0 || time / 100 >= 24 || time % 100 >= 60 || dateArr.length != 3) {
            return false;
        }
        timing.setYear(Integer.valueOf(dateArr[2]));
        timing.setMonth(Integer.valueOf(dateArr[1]) - 1);
        timing.setDate(Integer.valueOf(dateArr[0]));
        timing.setHours(time / 100);
        timing.setMinutes(time % 100);
        return true;
    }
    
    public boolean setDate(String str) {
        if (str.isEmpty())
            return false;
        String strArr[] = str.split("/");
        if (strArr.length == 3)
            return setDate(str, timing.getHours()*100+timing.getMinutes());
        else if (this.isInt(str)){
            String date = "";
            date += timing.getDate() + "/";
            date += (timing.getMonth() + 1) + "/";
            date += timing.getYear();
            return setDate(date, Integer.valueOf(str));
        }
        else
            return false;
    }
    
    private boolean isInt(String str) {
        char charArr[] = str.toCharArray();
        for (char c : charArr) {
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Cineplex cineplex = new Cineplex("IDD", "GV", "Orchard", 4);
        Movie movie = new Movie("Alpha", "GHD505");
        Date timing = new Date(2015, 11, 28, 18, 30);
        Showtime showtime = new Showtime(cineplex, 1, movie, timing);
        System.out.println("Date = " + showtime.getDate());
        System.out.println("Time = " + showtime.getTiming());
        if (showtime.setDate("")) {
            System.out.println("Date = " + showtime.getDate());
            System.out.println("Time = " + showtime.getTiming());
        }
        else System.out.println("Input error");
    }
}
