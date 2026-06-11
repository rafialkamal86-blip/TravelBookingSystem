import java.time.LocalDate;

public sealed abstract class Reservation implements Bookable permits FlightReservation, HotelReservation {
    private final String confirmationNumber;
    private final LocalDate bookingDate;

    public Reservation(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
        this.bookingDate = LocalDate.now();
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public abstract void display();
}
