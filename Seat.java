package CZ2002;

public class Seat {

    /*  This class is coded with the assumption that the rows doesn't exceed 
     one alphabet
     */
    private char seatRow;
    private int seatNumber;
    private boolean isBooked;
    private final boolean exist;

    /* Constructors */
    Seat(char seatRow, int seatNumber, boolean exist) {
        this.seatRow = Character.toUpperCase(seatRow);
        this.seatNumber = seatNumber;
        this.exist = exist;
    }

    Seat(String seatID, boolean exist) {
        this(seatID.charAt(0), Integer.valueOf(seatID.substring(1)), exist);
    }

    Seat(char seatRow, int seatNumber) {
        this(seatRow, seatNumber, true);
    }

    Seat(String seatID) {
        this(seatID, true);
    }
    /* end of Constructors */

    /* Accessors */
    public int getSeatNumber() {
        return seatNumber;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public boolean seatIsBooked() {
        return isBooked;
    }

    public boolean seatExist() {
        return exist;
    }

    public String getSeatID() {
        return seatRow + Integer.toString(seatNumber);
    }
    /* end of Accessors */

    /* Mutators */
    public void setBookStatus(boolean booking) {
        isBooked = booking;
    }
    /* end of Mutators */

}
