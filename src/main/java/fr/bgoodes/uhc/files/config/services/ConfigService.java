package fr.bgoodes.uhc.files.config.services;

import fr.bgoodes.uhc.files.config.Option;
import fr.bgoodes.uhc.files.config.adapters.TypeAdapter;

import java.io.IOException;

/**
 * A service for managing server configuration. The service is responsible for
 * registering options and saving the configuration. The actual storage and retrieval
 * of options is handled by the concrete implementation of this interface.
 */
public interface ConfigService {
    <T> Option<T> registerOption(String path, Class<T> type);
    <T> Option<T> registerOption(String path, TypeAdapter<T> adapter);
    void saveAll() throws IOException;
}
