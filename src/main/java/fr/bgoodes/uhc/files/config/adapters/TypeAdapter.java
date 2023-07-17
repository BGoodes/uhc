package fr.bgoodes.uhc.files.config.adapters;

/**
 * An adapter for serializing and deserializing options. This interface must be
 * implemented for any type that is used as the value of an option and requires
 * custom serialization/deserialization.
 */
public interface TypeAdapter<T> {
    T deserialize(Object obj);
    String serialize(T value);
}