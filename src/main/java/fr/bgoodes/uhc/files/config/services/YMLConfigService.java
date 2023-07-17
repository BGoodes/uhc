package fr.bgoodes.uhc.files.config.services;

import fr.bgoodes.uhc.files.config.Option;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        Option<T> option = new Option<>(path, value);
        options.put(path, option);
        return option;
    }

    @Override
    public void saveAll() throws IOException {
        for (Option<?> option : options.values()) {
            config.set(option.getPath(), option.getValue());
        }
        config.save(file);
    }
}
