package fr.bgoodes.uhc.listeners;

import fr.bgoodes.uhc.UHC;
import org.bukkit.event.Listener;

public class ListenerManager {
    private final UHC plugin;

    public ListenerManager(UHC plugin) {
        this.plugin = plugin;
    }

    public void registerListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    public void initializeListeners() {
        registerListener(new PlayerConnexionListener());
    }
}