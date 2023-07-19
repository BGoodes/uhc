package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.adapters.EnumAdapter;
import fr.bgoodes.uhc.files.config.adapters.LocationAdapter;
import fr.bgoodes.uhc.files.config.services.ConfigService;
import org.bukkit.GameMode;
import org.bukkit.Location;

/**
 * The ServerConfig class encapsulates the initial configuration options required for plugin initialization.
 * These options are modifiable only within the server-config.yml file by default.
 */
public class ServerConfig {

    public final Option<String> defaultLanguageKey;
    public final Option<Location> spawnLocation;
    public final Option<GameMode> defaultGamemode;

    public ServerConfig(ConfigService configService) {
        this.defaultLanguageKey  = configService.registerOption("default-language-key", String.class);
        this.spawnLocation = configService.registerOption("lobby.spawn-location", new LocationAdapter());
        this.defaultGamemode = configService.registerOption("lobby.default-gamemode", new EnumAdapter<>(GameMode.class));
    }
}
