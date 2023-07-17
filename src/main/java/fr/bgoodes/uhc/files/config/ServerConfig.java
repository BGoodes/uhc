package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.services.ConfigService;

public class ServerConfig {

    public final Option<String> DEFAULT_LANGUAGE_KEY;

    public ServerConfig(ConfigService configService) {
        this.DEFAULT_LANGUAGE_KEY = configService.registerOption("default-language-key", String.class);
    }
}
