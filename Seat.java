package CZ2002;

/**Seat : Class
 * 
 * 
 * @author Hocks
 */
public class Seat {

    /*  This class is coded with the assumption that the rows doesn't exceed 
     one alphabet
     */
    private char seatRow;
    private int seatNumber;
    private boolean isBooked;
    private final boolean exist;

    /* Constructors */
    /**Creates an instance of Seat and states whether it exists or not.
     * 
     * @param seatRow
     * @param seatNumber
     * @param exist 
     */
    public Seat(char seatRow, int seatNumber, boolean exist) {
        this.seatRow = Character.toUpperCase(seatRow);
        this.seatNumber = seatNumber;
        this.exist = exist;
    }

    /**Creates an instance of Seat and states whether it exists or not.
     * 
     * @param seatID
     * @param exist 
     */
    public Seat(String seatID, boolean exist) {
        this(seatID.charAt(0), Integer.valueOf(seatID.substring(1)), exist);
    }

    /**Creates an instance of Seat.
     * Default setting to exist.
     * 
     * @param seatRow
     * @param seatNumber 
     */
    public Seat(char seatRow, int seatNumber) {
        this(seatRow, seatNumber, true);
    }

    /**Creates an instance of Seat.
     * Default setting to exist.
     * 
     * @param seatID 
     */
    public Seat(String seatID) {
        this(seatID, true);
    }
    /* end of Constructors */

    /* Accessors */
    /**Returns Seat Number in the row.
     * 
     * @return Seat Column Number
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**Returns the Seat Row value.
     * 
     * @return Seat Row Value
     */
    public char getSeatRow() {
        return seatRow;
    }

    /**Returns a boolean of whether the seat is booked.
     * 
     * @return Booked
     */
    public boolean seatIsBooked() {
        return isBooked;
    }

    /**Returns a boolean of whether the seat exists.
     * 
     * @return Exists
     */
    public boolean seatExist() {
        return exist;
    }

    /**Returns the Seat ID.
     * 
     * @return SeatID
     */
    public String getSeatID() {
        return seatRow + Integer.toString(seatNumber);
    }
    /* end of Accessors */

    /* Mutators */
    /**Changes the booked value to True.
     * 
     */
    public void bookSeat() {
        isBooked = true;
    }

    /**Changes the booked value to False.
     * 
     */
    public void unbookSeat() {
        isBooked = false;
    }
    /* end of Mutators */

}
