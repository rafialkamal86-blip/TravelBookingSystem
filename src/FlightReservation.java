public final class FlightReservation extends Reservation {
    private final Flight flight;
    private final String passengerName;
    private final String contactNumber;

    public FlightReservation(String confirmationNumber, Flight flight, String passengerName, String contactNumber) {
        super(confirmationNumber);
        this.flight = flight;
        this.passengerName = passengerName;
        this.contactNumber = contactNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public void display() {
        System.out.println("=== Reservasi Penerbangan ===");
        System.out.println("Nomor konfirmasi: " + getConfirmationNumber());
        System.out.println("Nama penumpang: " + passengerName);
        System.out.println("Kontak: " + contactNumber);
        System.out.println("Detail penerbangan: " + flight);
    }
}
