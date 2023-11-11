package fr.bgoodes.confutil;

import fr.bgoodes.confutil.services.ConfigService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Config {

    private final ConfigService service;

    public Config(ConfigService service) {
        this.service = service;
    }

    public void saveAll() throws IOException {
        service.saveAll();
    }

    public void saveAll(ConfigService service) throws IOException {
        service.saveAll();
    }
}
