package fr.bgoodes.uhc.files.config.adapters;

/**
 * An implementation of {@link TypeAdapter} for handling Java enum types.
 * This adapter deserializes an enum from a string and serializes an enum to a string.
 *
 * @param <T> The enum type
 */
public class EnumAdapter<T extends Enum<T>> implements TypeAdapter<T> {

    private final Class<T> enumType;

    public EnumAdapter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T deserialize(Object obj) {
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Serialized enum must be a string.");
        }

        try {
            return Enum.valueOf(enumType, ((String) obj).toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid enum string: " + obj);
        }
    }

    @Override
    public String serialize(T value) {
        return value.name();
    }
}