package fr.bgoodes.confutil.services;

import fr.bgoodes.confutil.Option;
import fr.bgoodes.confutil.adapters.TypeAdapter;

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
