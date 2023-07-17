package fr.bgoodes.uhc;

import fr.bgoodes.uhc.files.MCFile;
import fr.bgoodes.uhc.files.config.ServerConfig;
import fr.bgoodes.uhc.files.config.services.YMLConfigService;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.utils.LogUtils;
import fr.bgoodes.uhc.utils.TextUtils;
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

        TextUtils.loadLanguages();

        gameManager = new GameManager();

        try {
            ServerConfig serverConfig = new ServerConfig(new YMLConfigService(new MCFile("server-config.yml").getFile()));
            LogUtils.info("Default language : " + serverConfig.default_language_key.getValue());
            LogUtils.info("Option 1 : " + serverConfig.option1.getValue());
            LogUtils.info("Option 2 : " + serverConfig.option2.getValue());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateDefaultFiles() throws IOException {
        List<String> defaultFiles = Arrays.asList(
                "server-config.yml",
                "lang/fr.yml"
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
