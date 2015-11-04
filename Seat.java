package CZ2002;

public class Seat {

    private char seatRow;
    private int seatNumber;
    private boolean isBooked;
    private final boolean exist;
    
    Seat(char seatRow, int seatNumber) {
        this(seatRow, seatNumber, true);
    }
    
    Seat(String seatID) {
        this(seatID, true);
    }

    Seat(char seatRow, int seatNumber, boolean exist) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.exist = exist;
    }

    Seat(String seatID, boolean exist) {
        this(seatID.charAt(0), Integer.valueOf(seatID.substring(1)), exist);
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public boolean seatIsBooked() {
        return isBooked;
    }

    public void setBookStatus(boolean booking) {
        isBooked = booking;
    }

    public boolean seatExist() {
        return exist;
    }

}
