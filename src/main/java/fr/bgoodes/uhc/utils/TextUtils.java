package fr.bgoodes.uhc.utils;

import fr.bgoodes.uhc.files.lang.LangManager;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import net.kyori.adventure.text.Component;

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

    public static String getTranslation(TranslationKey key, String langCode, Object... args) {
        return format(langManager.getTranslation(key, langCode), args);
    }

    public static Component getComponent(TranslationKey key, String langCode, Object... args) {
        String message = format(langManager.getTranslation(key, langCode), args);
        return Component.text(message);
    }

    private static String format(String text, Object... args) {
        return MessageFormat.format(text, args);
    }
}
