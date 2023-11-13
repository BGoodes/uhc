package fr.bgoodes.uhc;

import fr.bgoodes.uhc.commands.CommandManager;
import fr.bgoodes.uhc.files.FileHandler;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.listeners.ListenerManager;
import fr.bgoodes.uhc.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

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
    private static FileHandler fileHandler;

    /**
     * This method is called when the plugin is enabled.
     * It initializes the file handler, server configuration, and game manager.
     */
    @Override
    public void onEnable() {
        instance = this;

        // Initialize file handler and generate defaults files
        try {
            fileHandler = new FileHandler();
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to initialize files: " + e.getMessage());
            throw new IllegalPluginAccessException("Failed to initialize files");
        }

        // Initialize server configuration
        //TODO: Implement server config
        //ConfigService configService = new YMLConfigService(fileHandler.serverConfig.getFile());
        //serverConfig = new ServerConfig(configService);

        // Load language files
        try {
            TextUtils.loadLanguages();
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to load languages: " + e.getMessage());
            throw new IllegalPluginAccessException("Failed to load languages");
        }


        // Initialize game manager
        gameManager = new GameManager();

        new ListenerManager(this).initializeListeners();
        new CommandManager(this).initializeCommands();

        // Display startup message
        Bukkit.getLogger().info("===============================");
        Bukkit.getLogger().info("  UHC Plugin");
        Bukkit.getLogger().info("  Version: 1.0-SNAPSHOT");
        Bukkit.getLogger().info("  Developer: B. Goodes");
        Bukkit.getLogger().info("===============================");
    }

    /**
     * This method is called when the plugin is disabled.
     */
    @Override
    public void onDisable() {
    }

    public static UHC getInstance() {
        return instance;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }
}
