package fr.bgoodes.uhc.files.config.adapters;

/**
 * An interface for serializing and deserializing custom objects. This must be
 * implemented for any type that is used as the value of an option and requires
 * custom serialization/deserialization logic.
 *
 * @param <T> The type of value this adapter handles
 */
public interface TypeAdapter<T> {
    T deserialize(Object obj);
    String serialize(T value);
}