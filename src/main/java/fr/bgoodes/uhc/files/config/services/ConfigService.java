package fr.bgoodes.uhc.files.config.services;

import fr.bgoodes.uhc.files.config.Option;

import java.io.IOException;

public interface ConfigService {
    <T> Option<T> registerOption(String path, Class<T> type);
    void saveAll() throws IOException;
}
