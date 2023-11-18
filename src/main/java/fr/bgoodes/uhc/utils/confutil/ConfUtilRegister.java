package fr.bgoodes.uhc.utils.confutil;

import fr.bgoodes.confutil.holders.HolderFactory;
import org.bukkit.Location;

public class ConfUtilRegister {

    private ConfUtilRegister() {}
    public static void initialize() {
        HolderFactory.registerHolder(Location.class, LocationHolder.class);
    }
}
