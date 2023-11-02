package fr.bgoodes.uhc.game.tasks.core;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.game.GameState;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.players.UHCPlayer;
import fr.bgoodes.uhc.game.tasks.UHCTask;
import fr.bgoodes.uhc.utils.MCSound;

import java.time.Duration;

import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;

public class StartingTask extends UHCTask {
    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private int countdown;

    private final MCSound TIMER_SOUND = new MCSound(Sound.UI_BUTTON_CLICK, 0.6f);
    private final MCSound START_SOUND = new MCSound(Sound.BLOCK_NOTE_BLOCK_PLING, 0.6f);
    private final MCSound CANCEL_SOUND = new MCSound(Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f);
    private final Title.Times TITLE_TIMES = Title.Times.times(Duration.ofMillis(250L), Duration.ofMillis(1000L), Duration.ofMillis(0L));

    private final String[] TITLE_COLOR = new String[] {"§a", "§e", "§6", "§c", "§4"};

    public StartingTask() {
        this.gameManager = UHC.getGameManager();
        this.playerManager = this.gameManager.getPlayerManager();
    }

    public void start() {
        this.countdown = 30;
        runTaskTimer(UHC.getInstance(), 0L, 20L);
    }

    public void run() {
        if (this.countdown <= 0) {
            this.gameManager.enterState(GameState.LOADING);
        } else {
            setXp(this.countdown);
            if (this.countdown == 10 || this.countdown == 20 || this.countdown == 30)
                this.gameManager.broadcast(TranslationKey.BC_GAME_STARTING, this.TIMER_SOUND, this.countdown);
            if (this.countdown <= 5)
                this.gameManager.title(TranslationKey.BC_GAME_STARTING_TITLE, this.TITLE_TIMES, this.START_SOUND, this.TITLE_COLOR[this.countdown - 1], this.countdown);
        }
        this.countdown--;
    }

    private void setXp(Integer level) {
        for (UHCPlayer p : this.playerManager.getConnectedPlayers()) {
            p.getPlayer().setLevel(level);
            p.getPlayer().setExp(0.0f);
        }
    }

    public void cancelStart() {
        this.gameManager.enterState(GameState.WAITING);
        UHC.getGameManager().broadcast(TranslationKey.BC_GAME_CANCELLED);
        this.gameManager.playSound(this.CANCEL_SOUND);
    }

    public void stop() {
        setXp(0);
        cancel();
    }
}