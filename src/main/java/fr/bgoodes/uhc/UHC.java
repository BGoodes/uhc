package fr.bgoodes.uhc;

import org.bukkit.plugin.java.JavaPlugin;

public final class UHC extends JavaPlugin {

    private static UHC instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }

    public static UHC getInstance() {
        return instance;
    }
}
