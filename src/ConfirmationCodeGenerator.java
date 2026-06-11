import java.util.Random;

public final class ConfirmationCodeGenerator {
    private static final Random RANDOM = new Random();

    private ConfirmationCodeGenerator() {
        // Kelas utilitas tidak bisa diinstansiasi
    }

    public static String generateCode() {
        int code = RANDOM.nextInt(900_000) + 100_000;
        return String.valueOf(code);
    }
}
