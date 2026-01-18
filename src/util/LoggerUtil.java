package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple utility for console logging.
 */
public class LoggerUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        System.out.println("[" + LocalDateTime.now().format(FORMATTER) + "] INFO: " + message);
    }

    public static void logError(String message) {
        System.err.println("[" + LocalDateTime.now().format(FORMATTER) + "] ERROR: " + message);
    }
}