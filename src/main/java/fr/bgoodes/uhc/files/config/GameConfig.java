package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.services.ConfigService;

/**
 * This class represents the configuration for a game of UHC. It defines game-specific
 * options that are adjustable by game organizers.
 * The options are represented as instances of the {@link Option} class. They are
 * registered with a {@link ConfigService} that handles reading the actual values
 * from a configuration source.
 */
public class GameConfig {

    public final Option<String> gameName;
    public final Option<Integer> teamSize;
    public final Option<Integer> teamCount;

    /**
     * Constructs a new GameConfig object with default values.
     *
     * @param configService  the configuration service used to register and retrieve
     *                       the game options. This is typically an instance that
     *                       reads the configuration from a file or another external source.
     */
    public GameConfig(ConfigService configService) {
        this.gameName = configService.registerOption("game-name", String.class);

        this.teamSize = configService.registerOption("team-settings.team-size", Integer.class);
        this.teamCount = configService.registerOption("team-settings.team-count", Integer.class);
    }
}
