package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.services.ConfigService;

public class ServerConfig {

    public final Option<String> default_language_key;
    public final Option<Integer> option1;
    public final Option<Boolean> option2;

    public ServerConfig(ConfigService configService) {
        this.default_language_key = configService.registerOption("default-language-key", String.class);
        this.option1 = configService.registerOption("test.option1", Integer.class);
        this.option2 = configService.registerOption("test.option2", Boolean.class);
    }
}
