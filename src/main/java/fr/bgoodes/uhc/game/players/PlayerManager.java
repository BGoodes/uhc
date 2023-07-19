package fr.bgoodes.uhc.game.players;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.config.ServerConfig;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerManager {

    private final Map<UUID, UHCPlayer> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

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

    public void addPlayer(UHCPlayer uhcPlayer) {
        players.put(uhcPlayer.getID(), uhcPlayer);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    public void setPlayerWait(UHCPlayer uhcPlayer) {
        uhcPlayer.setState(PlayerState.ALIVE);

        if (uhcPlayer.isConnected()) {
            Player player = uhcPlayer.getPlayer();
            //resetPlayer(uhcPlayer);

            ServerConfig serverConfig = UHC.getServerConfig();
            player.setGameMode(serverConfig.defaultGamemode.getValue());
            player.teleport(serverConfig.spawnLocation.getValue());
        }
    }

    public void setPlayerSpectate(UHCPlayer uhcPlayer) {
        uhcPlayer.setState(PlayerState.SPECTATE);

        if (uhcPlayer.isConnected()) {
            Player player = uhcPlayer.getPlayer();
            //resetPlayer(uhcPlayer);

            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(UHC.getServerConfig().spawnLocation.getValue());
        }
    }
}
