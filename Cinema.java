package CZ2002;

public class Cinema {

    private String cinemaCode;
    //private Seat[] seat;
    
    public Cinema(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public void getLayout() {
        // enter code here
    }

    public String getName() {
        return this.cinemaCode;
    }

    public void setName(String Name) {
        this.cinemaCode = Name;
    }
}
