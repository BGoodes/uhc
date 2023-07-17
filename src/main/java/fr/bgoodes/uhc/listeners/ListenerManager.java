package fr.bgoodes.uhc.listeners;

import fr.bgoodes.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenerManager {

    public void registerListeners(UHC pl) {
        registerListener(new PlayerConnexionListener(), pl);
    }

    private void registerListener(Listener listener, UHC plugin) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }
}