package fr.bgoodes.uhc;

import fr.bgoodes.uhc.files.MCFile;
import fr.bgoodes.uhc.game.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Main class for the UHC plugin.
 * This class is responsible for enabling and disabling the plugin,
 * and provides a static instance of itself for use throughout the plugin.
 *
 * @author B. Goodes
 */
public final class UHC extends JavaPlugin {

    private static UHC instance;

    private static GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        try {
            generateDefaultFiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gameManager = new GameManager();
    }

    private void generateDefaultFiles() throws IOException {
        List<String> defaultFiles = Arrays.asList(
                "server-config.yml",
                "lang/fr.properties"
        );

        for (String filename : defaultFiles) {
            String path = filename.contains("/") ? filename.substring(0, filename.lastIndexOf('/')) : "";
            String fileName = filename.contains("/") ? filename.substring(filename.lastIndexOf('/') + 1) : filename;
            new MCFile(fileName, path);
        }
    }

    @Override
    public void onDisable() {
    }

    public static UHC getInstance() {
        return instance;
    }
    public static GameManager getGameManager() {
        return gameManager;
    }
}
