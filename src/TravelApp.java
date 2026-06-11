import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TravelApp {
    private final List<Flight> availableFlights = new ArrayList<>();
    private final List<Hotel> availableHotels = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public TravelApp() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        availableFlights.add(new Flight("TRV100", "Jakarta", "Bali", LocalDate.now().plusDays(1), 22, 1250000));
        availableFlights.add(new Flight("TRV200", "Jakarta", "Yogyakarta", LocalDate.now().plusDays(2), 43, 550000));
        availableFlights.add(new Flight("TRV300", "Surabaya", "Jakarta", LocalDate.now().plusDays(3), 32, 800000));
        availableFlights.add(new Flight("TRV400", "Bandung", "Bali", LocalDate.now().plusDays(4), 90, 1450000));

        availableHotels.add(new Hotel("H100", "Hotel Merah Putih", "Bali", 750000));
        availableHotels.add(new Hotel("H200", "Grand Yogyakarta", "Yogyakarta", 550000));
        availableHotels.add(new Hotel("H300", "Surabaya Suites", "Surabaya", 620000));
        availableHotels.add(new Hotel("H400", "Jakarta City Hotel", "Jakarta", 680000));
    }

    public List<Flight> searchFlights(String origin, String destination, LocalDate date, int passengers) {
        return availableFlights.stream()
                .filter(f -> f.getOrigin().equalsIgnoreCase(origin))
                .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                .filter(f -> f.getDate().equals(date))
                .filter(f -> f.getAvailableSeats() >= passengers)
                .collect(Collectors.toList());
    }

    public List<Hotel> searchHotels(String location, LocalDate checkIn, LocalDate checkOut, int guests) {
        return availableHotels.stream()
                .filter(h -> h.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    public FlightReservation bookFlight(Flight flight, String passengerName, String contactNumber, int passengers) {
        if (flight.getAvailableSeats() < passengers) {
            throw new IllegalArgumentException("Kursi tidak mencukupi untuk penerbangan ini.");
        }
        String confirmation = ConfirmationCodeGenerator.generateCode();
        Flight bookedFlight = new Flight(
                flight.getFlightNumber(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDate(),
                passengers,
                flight.getPrice(),
                confirmation
        );
        flight.setAvailableSeats(flight.getAvailableSeats() - passengers);
        FlightReservation reservation = new FlightReservation(confirmation, bookedFlight, passengerName, contactNumber);
        reservations.add(reservation);
        return reservation;
    }

    public HotelReservation bookHotel(Hotel hotel, LocalDate checkIn, LocalDate checkOut, int guests, String guestName, String contactNumber) {
        String confirmation = ConfirmationCodeGenerator.generateCode();
        Hotel bookedHotel = new Hotel(
                hotel.getHotelId(),
                hotel.getName(),
                hotel.getLocation(),
                checkIn,
                checkOut,
                guests,
                hotel.getPricePerNight(),
                confirmation
        );
        HotelReservation reservation = new HotelReservation(confirmation, bookedHotel, guestName, contactNumber);
        reservations.add(reservation);
        return reservation;
    }

    public void cancelReservation(String confirmationNumber) throws ReservationNotFoundException {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getConfirmationNumber().equals(confirmationNumber)) {
                if (reservation instanceof FlightReservation flightReservation) {
                    restoreFlightSeats(flightReservation);
                }
                iterator.remove();
                return;
            }
        }
        throw new ReservationNotFoundException("Nomor konfirmasi tidak ditemukan: " + confirmationNumber);
    }

    private void restoreFlightSeats(FlightReservation reservation) {
        Flight bookedFlight = reservation.getFlight();
        for (Flight flight : availableFlights) {
            if (flight.getFlightNumber().equalsIgnoreCase(bookedFlight.getFlightNumber())
                    && flight.getDate().equals(bookedFlight.getDate())) {
                flight.setAvailableSeats(flight.getAvailableSeats() + bookedFlight.getPassengerCount());
                break;
            }
        }
    }

    public void showAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Belum ada reservasi saat ini.");
            return;
        }
        reservations.stream()
                .sorted(Comparator.comparing(Reservation::getConfirmationNumber))
                .forEach(Reservation::display);
    }
}
