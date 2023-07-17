package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.services.ConfigService;

/**
 * The ServerConfig class encapsulates the initial configuration options required for plugin initialization.
 * These options are modifiable only within the server-config.yml file by default.
 */
public class ServerConfig {

    public final Option<String> DEFAULT_LANGUAGE_KEY;

    public ServerConfig(ConfigService configService) {
        this.DEFAULT_LANGUAGE_KEY = configService.registerOption("default-language-key", String.class);
    }
}
