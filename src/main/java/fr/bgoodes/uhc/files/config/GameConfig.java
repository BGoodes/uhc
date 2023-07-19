package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.services.ConfigService;

public class GameConfig {

    public final Option<String> gameName;

    public GameConfig(ConfigService configService) {
        this.gameName = configService.registerOption("game-name", String.class);
    }
}
