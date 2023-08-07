package fr.bgoodes.uhc.game.tasks.core;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.tasks.UHCTask;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class LoadingTask extends UHCTask {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    public LoadingTask() {
        this.gameManager = UHC.getGameManager();
        this.playerManager = gameManager.getPlayerManager();
    }

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
