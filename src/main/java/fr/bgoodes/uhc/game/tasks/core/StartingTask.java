package fr.bgoodes.uhc.game.tasks.core;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.game.GameState;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.players.UHCPlayer;
import fr.bgoodes.uhc.game.tasks.UHCTask;
import fr.bgoodes.uhc.utils.MCSound;
import org.bukkit.Sound;

public class StartingTask extends UHCTask {

    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private int countdown;

    private final MCSound TIMER_SOUND = new MCSound(Sound.UI_BUTTON_CLICK, 0.6f);
    private final MCSound START_SOUND = new MCSound(Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f);
    private final MCSound CANCEL_SOUND = new MCSound(Sound.BLOCK_NOTE_BLOCK_BASS, 1f);

    public StartingTask() {
        this.gameManager = UHC.getGameManager();
        this.playerManager = gameManager.getPlayerManager();
    }

    @Override
    public void start() {
        this.countdown = 30;
        this.runTaskTimer(UHC.getInstance(), 0, 20);
    }

    @Override
    public void run() {

        if (countdown <= 0) {
            gameManager.enterState(GameState.LOADING);

        } else {
            setXp(countdown);

            if (countdown == 10 || countdown == 20 || countdown == 30) {
                gameManager.broadcast(TranslationKey.BC_GAME_STARTING, countdown);
                gameManager.playSound(TIMER_SOUND);
            }
        }

        countdown--;
    }

    private void setXp(Integer level) {
        for (UHCPlayer p : playerManager.getConnectedPlayers()) {
            p.getPlayer().setLevel(level);
            p.getPlayer().setExp(0);
        }
    }

    public void cancelStart() {
        gameManager.enterState(GameState.WAITING);
        UHC.getGameManager().broadcast(TranslationKey.BC_GAME_CANCELLED);
        gameManager.playSound(CANCEL_SOUND);
    }

    @Override
    public void stop() {
        setXp(0);
        cancel();
    }
}
