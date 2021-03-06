package CZ2002;
import java.util.ArrayList;
import java.util.Calendar;

public class ShowTimeMgr {
    
    private XML xml = new XML("showtime");
    private ArrayList <Showtime> showtimes = new <Showtime> ArrayList();
    private ArrayList <Showtime> filteredList;
    private MovieListing movieList;
    private CinemaMgr cineplex = new CinemaMgr();
    
    /* Constructors */
    public ShowTimeMgr(MovieListing movieList){
        this.movieList = movieList;
        generateList(); // Generate showtime listing from XML file
    }
    /* end of Constructors */
    
    /* Accessors */
    
    
    /* end of Accessors */
    
    /* Mutators */
    // contains methods for manipulating showtime entries in XML file
    
    public Showtime addShowtime(Cineplex cineplex, int cinemaRoom, Movie movie,
            Calendar timing, int status){
        // declare entry
        Showtime showtimeEntry;
        
        // Class objects' storeable data
        String cineplexID, movieID, date, time;
        
        cineplexID = cineplex.getID();
        movieID = movie.getMovieID();
        date = getDate(timing);
        time = getTiming(timing);
        
        // create entry and update local ArrayList
        showtimeEntry = addToArrayList(cineplex, cinemaRoom, movie, timing, 
                status);
        
        // create entry and update XML database
        String[] arr1 = new String[]{"cineplexID", "cinemaRoom", "movieID", 
            "date", "time", "status"};
        String[] arr2 = new String[]{cineplexID, String.valueOf(cinemaRoom), 
            movieID, date, time, String.valueOf(status)};
        xml.refreshCurElement();
        xml.addItem(arr1, arr2);
        
        // returns created entry
        return showtimeEntry;
    }
    
    private Showtime addToArrayList(Cineplex cineplex, int cinemaRoom, Movie 
            movie, Calendar timing, int status){
        
        // create eny and update local ArrayList
        Showtime entry = new Showtime(cineplex, cinemaRoom, movie, timing, 
                status);
        showtimes.add(entry);
        
        // returns created entry
        return entry;
    }
    
    /* end of Mutators */
    
    private void generateList(){
        
        // declare entry variable
        Showtime showtimeEntry;
        
        // declare variables for storing from retrieved data
        String cineplexID, movieID, date, time;
        int cinemaRoom, status;
        Cineplex cineplex;
        Movie movie;
        Calendar timing = Calendar.getInstance();
        
        //initialize variables
        cineplexID = movieID = date = time = "";
        cinemaRoom = status = 0;
        
        // declare corresponding boolean flags
        boolean cineplexIDflag, movieIDflag, dateFlag, timeFlag, roomFlag, 
                statusFlag;
        
        // retrieve data
        String retrievedList[][] = xml.retrieveData("None");
        
        // parse array
        for (String[] subArr : retrievedList){
            
            // initialize
            cineplexIDflag = movieIDflag = dateFlag = timeFlag = roomFlag = 
                    statusFlag = true;
            
            for (int i = 0; i < subArr.length; i+=2){
                
                if (cineplexIDflag && subArr[i].equals("cineplexID")){
                    cineplexID = subArr[i+1];
                    cineplexIDflag = false;
                } else if (movieIDflag && subArr[i].equals("movieID")){
                    movieID = subArr[i+1];
                    movieIDflag = false;
                } else if (dateFlag && subArr[i].equals("date")){
                    date = subArr[i+1];
                    dateFlag = false;
                } else if (timeFlag && subArr[i].equals("time")){
                    time = subArr[i+1];
                    timeFlag = false;
                } else if (roomFlag && subArr[i].equals("cinemaRoom")){
                    cinemaRoom = Integer.parseInt(subArr[i+1]);
                    roomFlag = false;
                } else if (statusFlag && subArr[i].equals("status")){
                    status = Integer.parseInt(subArr[i+1]);
                    statusFlag = false;
                } else {
                    System.out.println("Error generating List.");
                    return;
                }
            }
            
            // append to ArrayList if all fields are filled
            if (!(cineplexIDflag || movieIDflag || dateFlag || timeFlag || 
                    roomFlag || statusFlag)){
                cineplex = this.cineplex.findCineplex(cineplexID);
                movie = this.movieList.findMovie(movieID);
                setTime(timing, time);
                setDate(timing, date);
                
                addToArrayList(cineplex, cinemaRoom, movie, timing, status);
            }
        }
        
    }
    
    public void setTime(Calendar timing, String time){
        int hour, min, am_pm;
        
        hour = Integer.parseInt(time.substring(0, 2));
        min = Integer.parseInt(time.substring(3,5));
        am_pm = (time.charAt(6) == 'A') ? Calendar.AM : Calendar.PM;
        
        timing.set(Calendar.HOUR, hour);
        timing.set(Calendar.MINUTE, min);
        timing.set(Calendar.AM_PM, am_pm);
    }
    
    public void setDate(Calendar timing, String date){
        int date_of_month, month, year, day;
        
        date_of_month = Integer.parseInt(date.substring(0, 2));
        year = Integer.parseInt(date.substring(7,11));
        
        switch(date.charAt(3)){
            case 'J':
                if (date.charAt(4) == 'a'){
                    month = Calendar.JANUARY;
                } else if (date.charAt(6) == 'n'){
                    month = Calendar.JUNE;
                } else{
                    month = Calendar.JULY;
                }break;
            case 'F':
                month = Calendar.FEBRUARY; break;
            case 'M':
                if (date.charAt(6) == 'r'){
                    month = Calendar.MARCH;
                } else {
                    month = Calendar.MAY;
                }
            case 'A':
                if (date.charAt(5) == 'p'){
                    month = Calendar.APRIL;
                } else {
                    month = Calendar.AUGUST;
                } break;
            case 'S': month = Calendar.SEPTEMBER; break;
            case 'O': month = Calendar.OCTOBER; break;
            case 'N': month = Calendar.NOVEMBER; break;
            case 'D': month = Calendar.DECEMBER; break;
            default: month = 0;
        }
        
        switch(date.charAt(13)){
            case 'M': day = Calendar.MONDAY; break;
            case 'T': if (date.charAt(14) == 'u'){
                day = Calendar.TUESDAY;
            } else {
                day = Calendar.THURSDAY;
            } break;
            case 'W': day = Calendar.WEDNESDAY; break;
            case 'F': day = Calendar.FRIDAY; break;
            case 'S': if (date.charAt(14) == 'a'){
                day = Calendar.SATURDAY;
            } else {
                day = Calendar.SUNDAY;
            } break;
            default: day = 1;
        }
        
        timing.set(Calendar.DATE, date_of_month);
        timing.set(Calendar.MONTH, month);
        timing.set(Calendar.YEAR, year);
        timing.set(Calendar.DAY_OF_WEEK, day);
    }
    
    /**
     * Returns the time of the show in the format "hh:mm AM/PM".
     *
     * @return Show Time
     */
    public String getTiming(Calendar timing) {
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
    public String getDate(Calendar timing) {
        int year = timing.get(Calendar.YEAR);
        int month = timing.get(Calendar.MONTH);
        int date = timing.get(Calendar.DATE);
        int day = timing.get(Calendar.DAY_OF_WEEK);
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
    
}
