package fr.bgoodes.uhc.game.players;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.config.ServerConfig;
import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerManager {

    private final Map<UUID, UHCPlayer> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

    // --------------------------------------------
    // Get multiple players

    public List<UHCPlayer> getPlayers() {
        return new ArrayList<>(players.values());
    }

    public List<UHCPlayer> getConnectedPlayers() {
        return players.values().stream().filter(UHCPlayer::isConnected).collect(Collectors.toList());
    }

    public List<UHCPlayer> getAlivePlayers() {
        return players.values().stream().filter(UHCPlayer::isAlive).collect(Collectors.toList());
    }

    public List<UHCPlayer> getPlayingPlayers() {
        return players.values().stream().filter(UHCPlayer::isPlaying).collect(Collectors.toList());
    }

    public List<UHCPlayer> getSpectatingPlayers() {
        return players.values().stream().filter(UHCPlayer::isSpectator).collect(Collectors.toList());
    }

    // --------------------------------------------
    // Get a specific player

    public UHCPlayer getUHCPlayer(Player player) {
        return getUHCPlayer(player.getUniqueId());
    }
    public UHCPlayer getUHCPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public Boolean isUHCPlayer(Player player) {
        return isUHCPlayer(player.getUniqueId());
    }

    public Boolean isUHCPlayer(UUID uuid) {
        return players.containsKey(uuid);
    }

    // --------------------------------------------
    // Add and remove players

    public void addPlayer(UHCPlayer uhcPlayer) {
        players.put(uhcPlayer.getID(), uhcPlayer);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    // --------------------------------------------
    // Set player state
    public void setPlayerWait(UHCPlayer uhcPlayer) {
        uhcPlayer.setState(PlayerState.ALIVE);

        if (uhcPlayer.isConnected()) {
            Player player = uhcPlayer.getPlayer();
            resetPlayer(uhcPlayer);

            ServerConfig serverConfig = UHC.getServerConfig();
            player.setGameMode(serverConfig.defaultGamemode.getValue());
            player.teleport(serverConfig.spawnLocation.getValue());
        }
    }

    public void setPlayerSpectate(UHCPlayer uhcPlayer) {
        uhcPlayer.setState(PlayerState.SPECTATE);

        if (uhcPlayer.isConnected()) {
            Player player = uhcPlayer.getPlayer();
            resetPlayer(uhcPlayer);

            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(UHC.getServerConfig().spawnLocation.getValue());
        }
    }

    public void resetPlayer(UHCPlayer uhcPlayer) {
        if (uhcPlayer.isConnected()) {
            Player player = uhcPlayer.getPlayer();

            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            player.setAbsorptionAmount(0.0f);
            player.setHealth(20.0D);

            player.setFoodLevel(20);
            player.setSaturation(20.0f);
            player.setExhaustion(0.0f);
            player.setStatistic(Statistic.TIME_SINCE_REST, 0);

            player.setExp(0.0f);
            player.setLevel(0);

            player.setInvisible(false);

            player.setFireTicks(0);

            player.getActivePotionEffects().forEach(p -> player.removePotionEffect(p.getType()));

            clearPlayerInventory(uhcPlayer);
        }
    }

    public void clearPlayerInventory(UHCPlayer uhcPlayer) {
        if (uhcPlayer.isConnected()) {
            Player player = uhcPlayer.getPlayer();

            player.getInventory().clear();
            player.getInventory().setArmorContents(new ItemStack[] {null, null, null, null});
        }
    }
}
