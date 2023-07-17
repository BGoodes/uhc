package fr.bgoodes.uhc.utils;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.lang.LangManager;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * The TextUtils class provides utility methods for handling text and language-related tasks.
 */
public class TextUtils {

    private static final LangManager langManager = new LangManager();

    private TextUtils() {
        // Private constructor to prevent instantiation
    }

    public static void loadLanguages() throws IOException {
        langManager.loadLanguages();
    }

    public static void sendMessage(Player player, TranslationKey key, Object... args) {
        String langCode = getLangCodeForPlayer(player);
        player.sendMessage(getText(key, langCode));
    }

    public static Component getText(TranslationKey key, String langCode, Object... args) {
        String message = format(langManager.getTranslation(key, langCode));
        return Component.text(message);
    }

    private static String format(String text, Object... args) {
        return MessageFormat.format(text, args);
    }

    private static String getLangCodeForPlayer(Player player) {
        return UHC.getServerConfig().defaultLanguageKey.getValue();
    }
}
