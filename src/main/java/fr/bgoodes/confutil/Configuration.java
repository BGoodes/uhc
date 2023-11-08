package fr.bgoodes.confutil;

import fr.bgoodes.confutil.services.ConfigService;

public class Configuration {

    private final ConfigService service;

    public Configuration(ConfigService service) {
        this.service = service;
    }


}
