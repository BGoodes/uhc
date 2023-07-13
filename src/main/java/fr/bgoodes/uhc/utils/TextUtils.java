package fr.bgoodes.uhc.utils;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.lang.LangManager;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class TextUtils {

    private static final LangManager langManager = new LangManager();

    public static void loadLanguages() {
        langManager.loadLanguages();
    }
    public static void sendMessage(Player player, String key, Object... args) {
        String langCode = getLangCodeForPlayer(player);
        String message = langManager.getTranslation(key, langCode);
        player.sendMessage(format(message));
    }

    public static void logInfo(String key, Object... args) {
        String message = langManager.getTranslation(key, "fr");
        UHC.getInstance().getLogger().info(format(message, args));
    }

    private static String format(String text, Object... args) {
        return  MessageFormat.format(text, args);
    }

    private static String getLangCodeForPlayer(Player player) {
        return "fr";
    }
}
