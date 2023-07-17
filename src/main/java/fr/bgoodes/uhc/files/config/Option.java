package fr.bgoodes.uhc.files.config;

public class Option<T> {

    private final String path;
    private T value;

    public Option(String path, T value) {
        this.path = path;
        this.value = value;
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
}
