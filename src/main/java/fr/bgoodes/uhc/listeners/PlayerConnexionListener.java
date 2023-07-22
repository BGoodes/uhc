package fr.bgoodes.uhc.listeners;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.game.GameState;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.players.PlayerState;
import fr.bgoodes.uhc.game.players.UHCPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnexionListener implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        GameManager gameManager = UHC.getGameManager();
        PlayerManager playerManager = gameManager.getPlayerManager();
        Player player = event.getPlayer();
        UHCPlayer uhcPlayer;

        event.joinMessage(null);

        if (playerManager.isUHCPlayer(player)) {
            uhcPlayer = playerManager.getUHCPlayer(player);
        } else {
            PlayerState state = gameManager.getState() == GameState.WAITING ? PlayerState.ALIVE : PlayerState.SPECTATE;
            uhcPlayer = new UHCPlayer(player, state);
            playerManager.addPlayer(uhcPlayer);
        }

        uhcPlayer.refreshName();

        if (uhcPlayer.isSpectator()) {
            gameManager.broadcast(TranslationKey.BC_SPECTATOR_JOIN, player.getName());
            playerManager.setPlayerSpectate(uhcPlayer);
            return;
        }

        if (gameManager.getState() == GameState.WAITING)
            playerManager.setPlayerWait(uhcPlayer);

        gameManager.broadcast(TranslationKey.BC_PLAYER_JOIN, player.getName());
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event) {
        GameManager gameManager = UHC.getGameManager();
        PlayerManager playerManager = gameManager.getPlayerManager();
        Player player = event.getPlayer();

        event.quitMessage(null);

        if (!playerManager.isUHCPlayer(player))
            return;

        UHCPlayer uhcPlayer = playerManager.getUHCPlayer(player);

        if (uhcPlayer.isSpectator())
            gameManager.broadcast(TranslationKey.BC_SPECTATOR_LEAVE, player.getName());
        else
            gameManager.broadcast(TranslationKey.BC_PLAYER_LEAVE, player.getName());
    }
}