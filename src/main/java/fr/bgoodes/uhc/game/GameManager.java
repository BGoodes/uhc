package fr.bgoodes.uhc.game;

import fr.bgoodes.confutil.ConfigFactory;
import fr.bgoodes.confutil.exceptions.ConfigInstantiationException;
import fr.bgoodes.confutil.exceptions.StorageException;
import fr.bgoodes.confutil.storage.YMLStorage;
import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.FileHandler;
import fr.bgoodes.uhc.files.config.GameConfig;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import fr.bgoodes.uhc.game.gamemode.UHCModeManager;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.players.UHCPlayer;
import fr.bgoodes.uhc.game.players.teams.TeamManager;
import fr.bgoodes.uhc.game.scenarios.ScenarioManager;
import fr.bgoodes.uhc.game.tasks.TaskManager;
import fr.bgoodes.uhc.game.worlds.WorldManager;
import fr.bgoodes.uhc.utils.LogUtils;
import fr.bgoodes.uhc.utils.MCSound;
import fr.bgoodes.uhc.utils.TextUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;

/**
 * The GameManager class is responsible for managing the game state and all of the game's managers.
 * It also provides a few utility methods for broadcasting messages to players and playing sounds.
 */
public class GameManager {
    // Managers
    private final PlayerManager playerManager;

    private final TeamManager teamManager;
    private final WorldManager worldManager;
    private final UHCModeManager uhcModeManager;
    private final ScenarioManager scenarioManager;
    private final TaskManager taskManager;

    private final GameConfig configuration;
    private GameState state;

    // Config

    public GameManager() throws ConfigInstantiationException {
        this.playerManager = new PlayerManager();
        this.teamManager = new TeamManager();
        this.worldManager = new WorldManager();
        this.uhcModeManager = new UHCModeManager();
        this.scenarioManager = new ScenarioManager();
        this.taskManager = new TaskManager();

        this.enterState(GameState.WAITING);

        YMLStorage storage = new YMLStorage(UHC.getFileHandler().defaultGameConfig.getFile());
        this.configuration = ConfigFactory.getInstance(GameConfig.class);

        try {
            this.configuration.load(storage);
        } catch (StorageException e) {
            LogUtils.warning("Failed to load default game config.");
        }
    }

    // Getters
    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public TeamManager getTeamManager() {
        return this.teamManager;
    }

    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    public UHCModeManager getUHCModeManager() {
        return this.uhcModeManager;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public ScenarioManager getScenarioManager() {
        return this.scenarioManager;
    }

    // Game state
    public GameState getState() {
        return state;
    }

    public void enterState(GameState state) {
        this.state = state;
        this.taskManager.enterState(state);
    }

    public Boolean isStart() {
        return this.state == GameState.PLAYING || this.state == GameState.ENDING;
    }

    public void broadcast(TranslationKey key, Object... args) {
        broadcast(key, null, args);
    }

    public void broadcast(TranslationKey key, MCSound sound, Object... args) {
        for (UHCPlayer p : this.playerManager.getPlayers()) {
            p.getPlayer().sendMessage(TextUtils.getComponent(key, p.getLangCode(), args));
            if (sound != null) sound.play(p.getPlayer());
        }
    }

    public void title(TranslationKey titleKey, Title.Times times, Object... args) {
        title(titleKey, null, times, (MCSound) null, args);
    }

    public void title(TranslationKey titleKey, Title.Times times, MCSound sound, Object... args) {
        title(titleKey, null, times, sound, args);
    }

    public void title(TranslationKey titleKey, TranslationKey subtitleKey, Title.Times times, Object... args) {
        title(titleKey, subtitleKey, times, (MCSound) null, args);
    }

    public void title(TranslationKey titleKey, TranslationKey subtitleKey, Title.Times times, MCSound sound, Object... args) {
        for (UHCPlayer p : this.playerManager.getPlayers()) {
            Component title = TextUtils.getComponent(titleKey, p.getLangCode(), args);
            Component subtitle = subtitleKey != null ? TextUtils.getComponent(subtitleKey, p.getLangCode(), args) : Component.empty();

            p.getPlayer().showTitle(Title.title(title, subtitle, times));
            if (sound != null) sound.play(p.getPlayer());
        }
    }

    public void playSound(MCSound sound) {
        for (UHCPlayer p : this.playerManager.getPlayers()) {
            sound.play(p.getPlayer());
        }
    }
}
