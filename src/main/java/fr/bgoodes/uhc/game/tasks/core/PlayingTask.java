package fr.bgoodes.uhc.game.tasks.core;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.tasks.UHCTask;
import fr.bgoodes.uhc.game.worlds.WorldManager;

public class PlayingTask extends UHCTask {

    private int time;

    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private final WorldManager worldManager;


    public PlayingTask() {
        this.gameManager = UHC.getGameManager();
        this.playerManager = gameManager.getPlayerManager();
        this.worldManager = gameManager.getWorldManager();
    }

    @Override
    public void start() {
        time = 0;

    }

    @Override
    public void run() {
        time++;
    }

    @Override
    public void stop() {
        cancel();
    }
}
