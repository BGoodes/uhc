package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.adapters.EnumAdapter;
import fr.bgoodes.uhc.files.config.adapters.LocationAdapter;
import fr.bgoodes.uhc.files.config.services.ConfigService;
import org.bukkit.GameMode;
import org.bukkit.Location;

/**
 * The ServerConfig class encapsulates the initial configuration options required
 * for the plugin initialization. These options are modifiable only within the
 * server-config.yml file by default.
 * The options are represented as instances of the {@link Option} class. They are
 * registered with a {@link ConfigService} that handles reading the actual values
 * from a configuration source.
 */
public class ServerConfig {

    public final Option<String> defaultLanguageCode;
    public final Option<Location> spawnLocation;
    public final Option<GameMode> defaultGamemode;

    /**
     * Constructs a new ServerConfig object.
     *
     * @param configService  the configuration service used to register and retrieve
     *                       the server options. This is typically an instance that
     *                       reads the configuration from a file or another external source.
     */
    public ServerConfig(ConfigService configService) {
        this.defaultLanguageCode  = configService.registerOption("default-language-code", String.class);
        this.spawnLocation = configService.registerOption("lobby-settings.spawn-location", LocationAdapter.INSTANCE);
        this.defaultGamemode = configService.registerOption("lobby-settings.default-gamemode", new EnumAdapter<>(GameMode.class));
    }
}
