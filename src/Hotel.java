import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int guestCount;
    private double pricePerNight;
    private String confirmationNumber;

    public Hotel(String hotelId, String name, String location, double pricePerNight) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.checkInDate = null;
        this.checkOutDate = null;
        this.guestCount = 0;
        this.confirmationNumber = null;
    }

    public Hotel(String hotelId, String name, String location, LocalDate checkInDate, LocalDate checkOutDate, int guestCount, double pricePerNight, String confirmationNumber) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestCount = guestCount;
        this.pricePerNight = pricePerNight;
        this.confirmationNumber = confirmationNumber;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public long getNights() {
        if (checkInDate == null || checkOutDate == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public double getTotalPrice() {
        return getNights() * pricePerNight;
    }

    @Override
    public String toString() {
        if (checkInDate == null || checkOutDate == null) {
            return String.format("%s | %s | Lokasi: %s | Harga/malam: Rp%.0f",
                    hotelId, name, location, pricePerNight);
        }
        return String.format("%s | %s | Lokasi: %s | %s sampai %s | Tamu: %d | Harga total: Rp%.0f | Konfirmasi: %s",
                hotelId, name, location, checkInDate, checkOutDate, guestCount, getTotalPrice(), confirmationNumber);
    }
}
