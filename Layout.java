package CZ2002;

/**
 * Layout of a cinema can be generated using this class. THis class is more 
 * commonly used for creating the layout for booking for each showtime.
 * @author Hocks
 */
public class Layout {

    private final Seat[][] seat;
    private final String cinemaType;
    private final int row, col;

    /* Constructors */
    /**
     * Instantiates layout of cinema and corresponding movie, and assigns the
     * seats' booking status based on the booking data obtained from database.
     * Booking data is in a binary format saved as an integer.
     *
     * @param cinemaType
     * @param bookedBinaryData
     */
    public Layout(String cinemaType, int bookedBinaryData) {
        /*  bookedBinaryData: First binary bit indicates whether the last seat is
         booked. Last binary bit indicates whether the first seat is booked.
         E.g. cinemaType = "NORMAL"
         First bit: seat[7][11];
         Last bit : seat[0][0];
         */
        this.cinemaType = cinemaType.toUpperCase();

        /*switch (this.cinemaType) {
         case "GOLD":
         this.row = 4;
         this.col = 6;
         break;
         case "NORMAL":
         default:
         this.row = 8;
         this.col = 12;
         break;
         }*/
        if (this.cinemaType.equals("GOLD")) {
            this.row = 4;
            this.col = 6;
        } else { // if (this.cinemaType.equals("NORMAL")), else
            this.row = 8;
            this.col = 12;
        }
        seat = new Seat[this.row][this.col];

        char c = 'A';
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col;) {
                this.seat[i][j] = new Seat(c, ++j);
            }
            c++;
        }

        if (bookedBinaryData != 0) {
            assignBookingStatus(bookedBinaryData);
        }
    }

    /**
     * Instantiates layout of cinema and corresponding movie, initializing all
     * seats to be not booked.
     *
     * @param cinemaType
     */
    public Layout(String cinemaType) {
        this(cinemaType, 0);
    }

    /**
     * If nothing no parameter is given, a default NORMAL layout would be
     * generated.
     */
    public Layout() {
        this("NORMAL");
    }
    /* end of Constructors */

    /* Accessors */
    /**
     * The layout of the cinema is printed out with booking status.
     */
    public void printLayout() {
        // partition: number of seats before walkway in between the seats
        int partition = cinemaType.equals("GOLD") ? 2 : 6;
        char row = 'A';
        int length = this.col * 2 - 4;
        
        String screen = "  ";
        screen += "||";
        for (int i = 0; i < length; i++){
            screen += "-";
        }
        screen += "SCREEN";
        for (int i = 0; i < length; i++){
            screen += "-";
        }
        screen += "||";
        System.out.println(screen);
        
        for (int i = 0; i < this.row; i++){
            System.out.print(row);
            for (int j = 0; j < this.col; j++){
                System.out.print(((j % partition == 0 && j != 0) ? 
                        "|  |" : "   "));
                System.out.print(seat[i][j].seatIsBooked() ? "X" : "O");
            }
            System.out.println();
            row++;
        }
        
        System.out.print("    ");
        for (int j = 0; j < this.col; j++){
            System.out.format(((j % partition == 0 && j != 0) ? 
                    " %-4d" : "%-4d"), (j + 1));
        }
        
        System.out.println();
        
        System.out.println("O : Available");
        System.out.println("X : Booked");
        
    }
    
    /**
     * Returns an Integer value of current showtime's booking status for saving
     * to database.
     * @return bookedBinaryData (Integer)
     */
    public int getBookedData(){
        int data = 0;
        
        for (int i = 0; i < this.row; i++){
            data *= 2;
            for (int j = 0; j < this.col; j++)
                data += 1;
        }
        
        return data;
    }
    
    /**
     * Returns the seat requested.
     * @param row
     * @param col
     * @return 
     */
    public Seat getSeat(char row, int col){
        row = Character.toUpperCase(row);
        int i = 0;
        while (row != 'A'){
            i++;
            row--;
        }
        return seat[i][col - 1];
    }
    
    /**
     * Returns the seat requested.
     * @param seatID
     * @return 
     */
    public Seat getSeat(String seatID){
        char row = seatID.charAt(0);
        seatID = seatID.substring(1);
        return this.getSeat(row, Integer.valueOf(seatID));
    }
    /* end of Accessors */

    /**
     * Initialize individual seat's booking status with bookedBinaryData saved
     * in the database.
     * @param binaryData 
     */
    private void assignBookingStatus(int binaryData) {
        char c = cinemaType.equals("GOLD") ? 'E' : 'H';
        int i = this.row - 1;
        int col = this.col - 1;

        while (i >= 0) {
            int j = col;
            while (j >= 0) {
                if (binaryData % 2 == 1) {
                    seat[i][j].bookSeat();
                }
                binaryData /= 2;
                j--;
            }
            i--;
            c--;
        }
    }
    
    public static void main(String[] args){
        Layout layout = new Layout("GOLD");
        layout.printLayout();
        
        Seat seat;
        seat = layout.getSeat("A1");
        seat.bookSeat();
        
        layout.printLayout();
        
        seat = layout.getSeat("C4");
        seat.bookSeat();
        
        layout.printLayout();
        
        seat = layout.getSeat("A1");
        seat.unbookSeat();
        
        layout.printLayout();
    }
    
}
