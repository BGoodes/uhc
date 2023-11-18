package fr.bgoodes.uhc.files.config;

import fr.bgoodes.confutil.Config;
import fr.bgoodes.confutil.Option;
import org.bukkit.GameMode;
import org.bukkit.Location;

public interface ServerConfig extends Config {
    @Option(key="default-language-code", defaultValue="fr")
    String getDefaultLanguageCode();

    @Option(key="lobby-settings.spawn-location")
    Location getSpawnLocation();

    @Option(key="lobby-settings.default-gamemode")
    GameMode getDefaultGamemode();

}
