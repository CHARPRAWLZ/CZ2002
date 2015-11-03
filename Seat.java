package CZ2002;

public class Seat {
    
    private int seatNumber;
    private boolean isBooked;
    
    Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        isBooked = false;
    }
    
    public int getSeatNumber() {
        return this.seatNumber;
    }
    
    public boolean seatIsBooked() {
        return this.isBooked;
    }
    
    public void setBookStatus(boolean booking) {
        this.isBooked = booking;
    }
    
}
