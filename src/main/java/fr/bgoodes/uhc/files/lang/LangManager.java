package fr.bgoodes.uhc.files.lang;

import fr.bgoodes.uhc.UHC;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LangManager {
    private final Map<String, YamlConfiguration> languages = new HashMap<>();

    public void loadLanguages() {
        File langFolder = new File(UHC.getInstance().getDataFolder(), "lang");
        if (!langFolder.exists() || !langFolder.isDirectory()) {
            throw new IllegalStateException("Language folder does not exist or is not a directory.");
        }

        File[] langFiles = langFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (langFiles == null)
            throw new IllegalStateException("Error reading language files.");

        for (File file : langFiles) {
            String key = file.getName().replace(".yml", "");
            try {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                languages.put(key, config);
                UHC.getInstance().getLogger().info("Loaded language: " + key);
            } catch (Exception e) {
                UHC.getInstance().getLogger().severe("Error loading language file: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    public String getTranslation(String key, String langCode) {
        YamlConfiguration langConfig = languages.get(langCode);

        if (langConfig == null) {
            langConfig = languages.get("fr");
        }

        if (langConfig == null) {
            throw new IllegalStateException("Default language not found, check the server-config.yml.");
        }

        String translation = langConfig.getString(key);

        if (translation == null)
            throw new IllegalArgumentException("Translation not found for key: " + key);

        return translation;
    }
}