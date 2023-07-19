package fr.bgoodes.uhc.game.gamemode;

import org.bukkit.GameMode;

public class UHCModeManager {

    private UHCMode uhcMode;

    public void setGameMode(UHCMode uhcMode) {
        this.uhcMode = uhcMode;
    }

    public UHCMode getUHCMode() {
        return this.uhcMode;
    }
}