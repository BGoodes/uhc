package fr.bgoodes.uhc.game.tasks.core;

import fr.bgoodes.uhc.game.tasks.UHCTask;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class LoadingTask extends UHCTask {

    @Override
    public void start() {
        Bukkit.broadcast(Component.text("LOADING"));
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
