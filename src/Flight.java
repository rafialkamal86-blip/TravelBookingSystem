import java.time.LocalDate;

public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate date;
    private int passengerCount;
    private double price;
    private int availableSeats;
    private String confirmationNumber;

    public Flight(String flightNumber, String origin, String destination, LocalDate date, int availableSeats, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.availableSeats = availableSeats;
        this.price = price;
        this.passengerCount = 0;
        this.confirmationNumber = null;
    }

    public Flight(String flightNumber, String origin, String destination, LocalDate date, int passengerCount, double price, String confirmationNumber) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.passengerCount = passengerCount;
        this.price = price;
        this.availableSeats = 0;
        this.confirmationNumber = confirmationNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    @Override
    public String toString() {
        String confirmationInfo = confirmationNumber == null ? "(kursi tersedia: " + availableSeats + ")" : "(konfirmasi: " + confirmationNumber + ")";
        return String.format("%s | %s -> %s | %s | Harga: Rp%.0f | Penumpang: %d %s",
                flightNumber, origin, destination, date, price, passengerCount, confirmationInfo);
    }
}
