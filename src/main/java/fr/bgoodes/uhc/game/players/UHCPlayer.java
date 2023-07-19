package fr.bgoodes.uhc.game.players;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UHCPlayer {

    private final UUID uuid;
    private String name;
    private PlayerState state;

    public UHCPlayer(Player player, PlayerState state) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.state = state;
    }

    public UUID getID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void refreshName() {
        this.name = getPlayer().getName();
    }

    public Boolean isConnected() {
        return getPlayer() != null;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getID());
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public Boolean isAlive() {
        return this.state == PlayerState.ALIVE;
    }

    public Boolean isPlaying() {
        return isConnected() && isAlive();
    }

    public Boolean isSpectator() {
        return this.state == PlayerState.SPECTATE;
    }
}
