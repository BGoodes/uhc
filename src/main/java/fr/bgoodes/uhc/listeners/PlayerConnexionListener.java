package fr.bgoodes.uhc.listeners;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.game.GameState;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.players.PlayerState;
import fr.bgoodes.uhc.game.players.UHCPlayer;
import fr.bgoodes.uhc.utils.TextUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnexionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        GameManager gameManager = UHC.getGameManager();
        PlayerManager playerManager = gameManager.getPlayerManager();
        Player player = event.getPlayer();
        UHCPlayer uhcPlayer;

        if (playerManager.isUHCPlayer(player)) {
            uhcPlayer = playerManager.getUHCPlayer(player);
        } else {
            PlayerState state = gameManager.getState() == GameState.WAITING ? PlayerState.ALIVE : PlayerState.SPECTATE;
            uhcPlayer = new UHCPlayer(player, state);
            playerManager.addPlayer(uhcPlayer);
        }

        uhcPlayer.refreshName();

        if (uhcPlayer.isSpectator()) {
            event.joinMessage(TextUtils.getText(TranslationKey.BC_SPECTATOR_JOIN, "fr", player.getName()));
            playerManager.setPlayerSpectate(uhcPlayer);
            return;
        }

        if (gameManager.getState() == GameState.WAITING)
            playerManager.setPlayerWait(uhcPlayer);

        event.joinMessage(TextUtils.getText(TranslationKey.BC_PLAYER_JOIN, "fr", player.getName()));
    }
}