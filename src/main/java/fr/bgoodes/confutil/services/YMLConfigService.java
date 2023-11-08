package fr.bgoodes.confutil.services;

import fr.bgoodes.uhc.exceptions.MissingOptionException;
import fr.bgoodes.confutil.Option;
import fr.bgoodes.confutil.adapters.TypeAdapter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A YAML-based implementation of the {@link ConfigService} interface. This class
 * reads configuration options from a YAML file and writes them back to the same file.
 * It also handles the registration of options.
 */
public class YMLConfigService implements ConfigService {
    private final File file;
    private final YamlConfiguration config;
    private final Map<String, Option<?>> options = new HashMap<>();

    public YMLConfigService(File file) {
        this.file = file;
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public <T> Option<T> registerOption(String path, Class<T> type) {
        T value = type.cast(config.get(path));
        if (value == null)
            throw new MissingOptionException("Missing option for path: " + path, file);

        Option<T> option = new Option<>(path, value);
        options.put(path, option);
        return option;
    }

    @Override
    public <T> Option<T> registerOption(String path, TypeAdapter<T> adapter) {
        Object obj = config.get(path);
        if (obj == null)
            throw new MissingOptionException("Missing option for path: " + path, file);

        T value = adapter.deserialize(obj);
        Option<T> option = new Option<>(path, value, adapter);
        options.put(path, option);
        return option;
    }

    @Override
    public void saveAll() throws IOException {
        for (Option<?> option : options.values()) {
            if (option.hasAdapter())
                config.set(option.getPath(), option.serialize());
            else
                config.set(option.getPath(), option.getValue());
        }
        config.save(file);
    }
}
