package fr.bgoodes.uhc.game.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class UHCTask extends BukkitRunnable {
    public abstract void start();
    public abstract void stop();
}
