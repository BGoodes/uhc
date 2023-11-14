package fr.bgoodes.uhc.files.config;

import fr.bgoodes.confutil.Config;
import fr.bgoodes.confutil.Option;

public interface ServerConfig extends Config {

    @Option(key="default-language-code", defaultValue="fr")
    String getDefaultLanguageCode();
}
