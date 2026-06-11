import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        TravelApp app = new TravelApp();
        System.out.println("=== Travel Booking System ===");
        System.out.println("Selamat datang di aplikasi pemesanan perjalanan console Java.");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Pilih menu: ");
            switch (choice) {
                case 1 -> searchFlights(app);
                case 2 -> searchHotels(app);
                case 3 -> app.showAllReservations();
                case 4 -> cancelReservation(app);
                case 5 -> {
                    running = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                }
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Cari penerbangan");
        System.out.println("2. Cari hotel");
        System.out.println("3. Lihat semua reservasi");
        System.out.println("4. Batalkan reservasi");
        System.out.println("5. Keluar");
    }

    private static void searchFlights(TravelApp app) {
        String origin = readNonEmptyLine("Masukkan kota asal: ");
        String destination = readNonEmptyLine("Masukkan kota tujuan: ");
        LocalDate date = readDate("Masukkan tanggal perjalanan (yyyy-MM-dd): ");
        int passengers = readInt("Masukkan jumlah penumpang: ");
        if (passengers <= 0) {
            System.out.println("Jumlah penumpang harus lebih dari nol.");
            return;
        }

        List<Flight> matchingFlights = app.searchFlights(origin, destination, date, passengers);
        if (matchingFlights.isEmpty()) {
            System.out.println("Tidak ada penerbangan tersedia.");
            return;
        }
        System.out.println("Penerbangan tersedia:");
        for (int i = 0; i < matchingFlights.size(); i++) {
            System.out.println((i + 1) + ". " + matchingFlights.get(i));
        }

        int index = readInt("Pilih nomor penerbangan untuk dipesan (0 untuk batal): ");
        if (index == 0) {
            return;
        }
        if (index < 1 || index > matchingFlights.size()) {
            System.out.println("Pilihan penerbangan tidak valid.");
            return;
        }
        Flight flight = matchingFlights.get(index - 1);
        String name = readNonEmptyLine("Nama penumpang: ");
        String contact = readNonEmptyLine("Kontak penumpang: ");
        try {
            FlightReservation reservation = app.bookFlight(flight, name, contact, passengers);
            System.out.println("Pemesanan berhasil! Nomor konfirmasi: " + reservation.getConfirmationNumber());
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal memesan penerbangan: " + e.getMessage());
        }
    }

    private static void searchHotels(TravelApp app) {
        String location = readNonEmptyLine("Masukkan lokasi hotel: ");
        LocalDate checkIn = readDate("Masukkan tanggal check-in (yyyy-MM-dd): ");
        LocalDate checkOut = readDate("Masukkan tanggal check-out (yyyy-MM-dd): ");
        if (!checkOut.isAfter(checkIn)) {
            System.out.println("Tanggal check-out harus setelah tanggal check-in.");
            return;
        }
        int guests = readInt("Masukkan jumlah tamu: ");
        if (guests <= 0) {
            System.out.println("Jumlah tamu harus lebih dari nol.");
            return;
        }

        List<Hotel> matchingHotels = app.searchHotels(location, checkIn, checkOut, guests);
        if (matchingHotels.isEmpty()) {
            System.out.println("Tidak ada hotel tersedia.");
            return;
        }
        System.out.println("Hotel tersedia:");
        for (int i = 0; i < matchingHotels.size(); i++) {
            System.out.println((i + 1) + ". " + matchingHotels.get(i));
        }

        int index = readInt("Pilih nomor hotel untuk dipesan (0 untuk batal): ");
        if (index == 0) {
            return;
        }
        if (index < 1 || index > matchingHotels.size()) {
            System.out.println("Pilihan hotel tidak valid.");
            return;
        }
        Hotel hotel = matchingHotels.get(index - 1);
        String guestName = readNonEmptyLine("Nama tamu: ");
        String contact = readNonEmptyLine("Kontak tamu: ");
        HotelReservation reservation = app.bookHotel(hotel, checkIn, checkOut, guests, guestName, contact);
        System.out.println("Pemesanan hotel berhasil! Nomor konfirmasi: " + reservation.getConfirmationNumber());
    }

    private static void cancelReservation(TravelApp app) {
        String confirmation = readNonEmptyLine("Masukkan nomor konfirmasi: ");
        try {
            app.cancelReservation(confirmation);
            System.out.println("Reservasi berhasil dibatalkan.");
        } catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat. Silakan coba lagi.");
            }
        }
    }

    private static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input tidak boleh kosong. Silakan masukkan nilai yang valid.");
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return LocalDate.parse(line, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan yyyy-MM-dd.");
            }
        }
    }
}
