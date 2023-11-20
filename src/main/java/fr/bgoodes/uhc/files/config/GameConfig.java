package fr.bgoodes.uhc.files.config;

import fr.bgoodes.confutil.Config;
import fr.bgoodes.confutil.Option;

public interface GameConfig extends Config {

    @Option(key="game-name", defaultValue = "UHC")
    String getGameName();
    void setGameName(String gameName);

    // Team settings ---
    @Option(key="team-settings.team-size", defaultValue = "1")
    int getTeamSize();
    void setTeamSize(int teamSize);

    @Option(key="team-settings.team-count", defaultValue = "4")
    int getTeamCount();
    void setTeamCount(int teamCount);
}
