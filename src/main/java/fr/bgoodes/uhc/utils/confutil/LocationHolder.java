package fr.bgoodes.uhc.utils.confutil;

import fr.bgoodes.confutil.exceptions.DeserializationException;
import fr.bgoodes.confutil.holders.OptionHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationHolder extends OptionHolder {

    @Override
    public String serialize(Object o) {
        if (o == null) return null;

        Location loc = (Location) o;
        StringBuilder sb = new StringBuilder();
        sb.append(loc.getWorld().getName()).append(";");
        sb.append(loc.getX()).append(";");
        sb.append(loc.getY()).append(";");
        sb.append(loc.getZ());

        if (loc.getYaw() != 0.0f || loc.getPitch() != 0.0f) {
            sb.append(";").append(loc.getYaw()).append(";").append(loc.getPitch());
        }

        return sb.toString();
    }

    @Override
    public Object deserialize(String s) throws DeserializationException {
        if (s == null || s.isEmpty()) return null;

        String[] parts = s.split(";");
        if (parts.length < 4 || parts.length > 6) {
            throw new DeserializationException("Invalid location format: " + s);
        }

        World world = Bukkit.getServer().getWorld(parts[0]);
        if (world == null)
            throw new DeserializationException("World not found: " + parts[0]);

        try {
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            float yaw = 0.0f;
            float pitch = 0.0f;

            if (parts.length >= 5) {
                yaw = Float.parseFloat(parts[4]);
            }
            if (parts.length == 6) {
                pitch = Float.parseFloat(parts[5]);
            }

            return new Location(world, x, y, z, yaw, pitch);
        } catch (NumberFormatException e) {
            throw new DeserializationException("Invalid number format in location: " + s);
        }
    }

    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Location)) throw new IllegalArgumentException("Only Location instances are allowed");
        super.setValue(value);
    }
}
