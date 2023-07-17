package fr.bgoodes.uhc.utils;

import fr.bgoodes.uhc.files.lang.LangManager;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * The TextUtils class provides utility methods for handling text and language-related tasks.
 */
public class TextUtils {

    private static final LangManager langManager = new LangManager();

    private TextUtils() {
        // Private constructor to prevent instantiation
    }

    public static void loadLanguages() {
        langManager.loadLanguages();
    }

    public static void sendMessage(Player player, String key, Object... args) {
        String langCode = getLangCodeForPlayer(player);
        String message = langManager.getTranslation(key, langCode);
        player.sendMessage(format(message));
    }

    private static String format(String text, Object... args) {
        return  MessageFormat.format(text, args);
    }

    private static String getLangCodeForPlayer(Player player) {
        return "fr";
    }
}
