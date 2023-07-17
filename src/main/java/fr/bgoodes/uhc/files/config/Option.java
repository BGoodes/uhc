package fr.bgoodes.uhc.files.config;

import fr.bgoodes.uhc.files.config.adapters.TypeAdapter;

public class Option<T> {

    private final String path;
    private T value;
    private final TypeAdapter<T> adapter;

    /**
     * An Option represents a single configurable attribute within any configuration class in the plugin.
     * Each Option has a path, which is used to identify it in the configuration file,
     * and a value, which can be of any type. Some options may also have a TypeAdapter to handle custom
     * serialization and deserialization.
     */
    public Option(String path, T value) {
        this(path, value, null);
    }

    public Option(String path, T value, TypeAdapter<T> adapter) {
        this.path = path;
        this.value = value;
        this.adapter = adapter;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public boolean hasAdapter() {
        return adapter != null;
    }

    public String serialize() {
        if (adapter == null)
            throw new IllegalStateException("Cannot serialize option without a type adapter");

        return adapter.serialize(getValue());
    }
}
