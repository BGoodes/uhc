package fr.bgoodes.confutil.test;

import fr.bgoodes.confutil.Config;
import fr.bgoodes.confutil.Option;
import org.bukkit.GameMode;

public interface ServerConfig extends Config {

    @Option(key = "default-language-code", defaultValue = "fr")
    String getDefaultLanguageCode();

    @Option(key = "lobby-settings.default-gamemode")
    String getDefaultGamemode();
}
