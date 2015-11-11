package CZ2002;

import java.util.Date;

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
    private Date timing;

    /* Constructors */
    /**
     * Constructor of Showtime class. 'has a' relationship with Cineplex, Cinema
     * and Movie.
     *
     * @param cineplex
     * @param cinemaRoom
     * @param movie
     * @param timing
     */
    public Showtime(Cineplex cineplex, int cinemaRoom, Movie movie, Date timing) {
        this.cineplex = cineplex;
        this.cinema = this.cineplex.getCinemaList().get(cinemaRoom - 1);
        this.movie = movie;
        this.timing = timing;
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
     * @return True if setDate is successful; False if unsuccessful.
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
            return setDate(str, timing.getHours() * 100 + timing.getMinutes());
        } else if (this.isInt(str)) {
            String date = "";
            date += timing.getDate() + "/";
            date += (timing.getMonth() + 1) + "/";
            date += timing.getYear();
            return setDate(date, Integer.valueOf(str));
        } else {
            return false;
        }
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
