package CZ2002;

public class Seat {
    
    private int seatNumber;
    private boolean isBooked;
    
    Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        isBooked = false;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }
    
    public boolean seatIsBooked() {
        return isBooked;
    }
    
    public void setBookStatus(boolean booking) {
        isBooked = booking;
    }
    
}
