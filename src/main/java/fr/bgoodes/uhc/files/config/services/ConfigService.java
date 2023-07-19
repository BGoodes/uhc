package fr.bgoodes.uhc.files.config.services;

import fr.bgoodes.uhc.files.config.Option;
import fr.bgoodes.uhc.files.config.adapters.TypeAdapter;

import java.io.IOException;

/**
 * An interface for managing server configuration. This service is responsible for
 * registering options and saving the configuration. The actual storage and retrieval
 * of options is delegated to the concrete implementation of this interface.
 */
public interface ConfigService {
    <T> Option<T> registerOption(String path, Class<T> type);
    <T> Option<T> registerOption(String path, TypeAdapter<T> adapter);
    void saveAll() throws IOException;
}
