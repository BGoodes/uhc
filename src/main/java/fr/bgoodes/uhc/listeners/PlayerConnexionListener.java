package fr.bgoodes.uhc.listeners;

import fr.bgoodes.uhc.files.lang.TranslationKey;
import fr.bgoodes.uhc.utils.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnexionListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(TextUtils.getText(TranslationKey.BC_PLAYER_JOIN, "fr", player.getName()));
    }
}