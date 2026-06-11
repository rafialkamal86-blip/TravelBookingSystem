public final class HotelReservation extends Reservation {
    private final Hotel hotel;
    private final String guestName;
    private final String contactNumber;

    public HotelReservation(String confirmationNumber, Hotel hotel, String guestName, String contactNumber) {
        super(confirmationNumber);
        this.hotel = hotel;
        this.guestName = guestName;
        this.contactNumber = contactNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public void display() {
        System.out.println("=== Reservasi Hotel ===");
        System.out.println("Nomor konfirmasi: " + getConfirmationNumber());
        System.out.println("Nama tamu: " + guestName);
        System.out.println("Kontak: " + contactNumber);
        System.out.println("Detail hotel: " + hotel);
    }
}
