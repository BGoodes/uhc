package fr.bgoodes.confutil;

import fr.bgoodes.confutil.services.ConfigService;

import java.io.IOException;

public class Configuration {

    private final ConfigService service;

    public Configuration(ConfigService service) {
        this.service = service;
    }

    public void saveAll() throws IOException {
        service.saveAll();
    }
}
