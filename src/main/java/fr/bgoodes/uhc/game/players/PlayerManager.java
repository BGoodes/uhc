package fr.bgoodes.uhc.game.players;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.config.ServerConfig;
import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

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

            // Clear inventory and equipment
            PlayerInventory inventory = player.getInventory();
            inventory.clear();
            inventory.setArmorContents(null);
            inventory.setExtraContents(null);

            // Clear all potion effects
            for (PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }

            // Reset health and food
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            player.setFoodLevel(20);
            player.setSaturation(20.0f);

            // Clear experience
            player.setExp(0);
            player.setLevel(0);
            player.setTotalExperience(0);

            // Reset player status effects
            player.setFireTicks(0);
            player.setFallDistance(0);

            player.setGlowing(false);
            player.setInvisible(false);

            // Reset player environment
            player.resetPlayerTime();
            player.resetPlayerWeather();

            // Reset player statistics
            player.setStatistic(Statistic.TIME_SINCE_REST, 0);
        }
    }
}
