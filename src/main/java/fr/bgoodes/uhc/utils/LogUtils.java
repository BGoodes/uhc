package fr.bgoodes.uhc.utils;

import fr.bgoodes.uhc.UHC;
import net.kyori.adventure.text.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The LogUtils class provides utility methods for logging messages.
 *
 * @author B. Goodes
 */
public class LogUtils {

    private static final Logger LOGGER = UHC.getInstance().getLogger();

    private LogUtils() {
        // Private constructor to prevent instantiation
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warning(String message) {
        LOGGER.warning(message);
    }

    public static void severe(String message) {
        LOGGER.severe(message);
    }

    public static void severe(String message, Exception exception) {
        LOGGER.log(Level.SEVERE, message, exception);
    }
}
