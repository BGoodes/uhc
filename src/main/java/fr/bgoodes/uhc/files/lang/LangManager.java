package fr.bgoodes.uhc.files.lang;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.utils.LogUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * The LangManager class is responsible for managing language configurations and translations.
 * It loads language files from the "lang" folder and provides translations based on the provided language code.
 */
public class LangManager {
    private final Map<String, HashMap<TranslationKey, String>> languages = new HashMap<>();

    /**
     * Loads all language files from the "lang" folder.
     * Each language file is a Yaml file that maps translation keys to their translations.
     *
     * @throws IOException if the language folder doesn't exist, isn't a directory, or can't be read.
     */
    public void loadLanguages() throws IOException {
        Path langFolderPath = UHC.getInstance().getDataFolder().toPath().resolve("lang");
        if (!Files.exists(langFolderPath) || !Files.isDirectory(langFolderPath))
            throw new IOException("Language folder does not exist or is not a directory.");

        try (Stream<Path> langFiles = Files.list(langFolderPath)) {
            langFiles.filter(file -> file.toString().endsWith(".yml")).forEach(file -> {

                String key = file.getFileName().toString().replace(".yml", "");

                YamlConfiguration config = YamlConfiguration.loadConfiguration(file.toFile());
                HashMap<TranslationKey, String> translations = new HashMap<>();

                for (TranslationKey translationKey : TranslationKey.values())
                    translations.put(translationKey, loadValue(config, translationKey));

                languages.put(key, translations);
                LogUtils.info("Loaded language: " + key);

            });
        } catch (IOException e) {
            throw new IOException("Failed to load language files.", e);
        }
    }

    /**
     * Loads the value of the given translation key from the given configuration.
     *
     * @param config the configuration
     * @param key the translation key
     * @return the value of the translation key in the configuration
     */
    private String loadValue(YamlConfiguration config, TranslationKey key)  {
        String value = config.getString(key.getPath());

        if (value != null) {
            Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(value);

            while (m.find()) {
                String s = m.group();
                s = s.substring(1, s.length() - 1);

                // TODO: create a class or a method for this
                if (s.startsWith("link:")) {
                    String r = config.getString(s.substring(5));
                    if (r != null) value = value.replace("[" + s + "]", r);
                }
            }
        }

        return value;
    }

    /**
     * Gets the translation for the given key in the given language.
     * If the language isn't loaded, it defaults to the server's default language.
     * If the translation key doesn't exist, it throws an exception.
     *
     * @param key the translation key
     * @param langCode the language code
     * @return the translation of the key in the given language
     * @throws IllegalStateException if the default language isn't loaded
     * @throws IllegalArgumentException if the translation key doesn't exist
     */
    public String getTranslation(TranslationKey key, String langCode) {
        HashMap<TranslationKey, String> langConfig = languages.get(langCode);

        if (langConfig == null)
            langConfig = languages.get(UHC.getServerConfig().defaultLanguageCode.getValue());

        if (langConfig == null)
            throw new IllegalStateException("Default language not found, check the server-config.yml.");

        String translation = langConfig.get(key);

        if (translation == null)
            throw new IllegalArgumentException("Translation not found for key: " + key.getPath());

        return translation;
    }
}