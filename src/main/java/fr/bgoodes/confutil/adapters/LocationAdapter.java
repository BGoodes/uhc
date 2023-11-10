package fr.bgoodes.confutil.adapters;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

/**
 * An implementation of {@link TypeAdapter} for handling Bukkit's Location objects.
 * This adapter can deserialize a location from a string or a ConfigurationSection,
 * and can serialize a location to a string.
 */
public class LocationAdapter implements TypeAdapter<Location> {

    public static final LocationAdapter INSTANCE = new LocationAdapter();

    @Override
    public Location deserialize(Object obj) {
        if (obj instanceof String) {
            String[] split = ((String) obj).split(",");

            if (split.length != 4)
                throw new IllegalArgumentException("Invalid location string: " + obj);

            World world = Bukkit.getWorld(split[0]);
            if (world == null)
                throw new IllegalArgumentException("World not found: " + split[0]);

            double x = Double.parseDouble(split[1]);
            double y = Double.parseDouble(split[2]);
            double z = Double.parseDouble(split[3]);

            return new Location(world, x, y, z);

        }  else if (obj instanceof ConfigurationSection section) {
            String worldName = section.getString("world-name");
            if (worldName == null)
                throw new IllegalArgumentException("Invalid location section: " + obj);

            World world = Bukkit.getWorld(worldName);
            if (world == null)
                throw new IllegalArgumentException("World not found: " + worldName);

            double x = section.getDouble("x");
            double y = section.getDouble("y");
            double z = section.getDouble("z");

            return new Location(world, x, y, z);
        }
        else {
            throw new IllegalArgumentException("Unsupported object type for location deserialization: " + obj.getClass().getName());
        }
    }

    @Override
    public String serialize(Location value) {
        return value.getWorld().getName() + "," + value.getX() + "," + value.getY() + "," + value.getZ();
    }
}