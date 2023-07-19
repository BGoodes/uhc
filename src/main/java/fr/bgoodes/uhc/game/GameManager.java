package fr.bgoodes.uhc.game;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.config.GameConfig;
import fr.bgoodes.uhc.files.config.services.ConfigService;
import fr.bgoodes.uhc.files.config.services.YMLConfigService;
import fr.bgoodes.uhc.game.gamemode.UHCModeManager;
import fr.bgoodes.uhc.game.players.PlayerManager;
import fr.bgoodes.uhc.game.scenarios.ScenarioManager;
import fr.bgoodes.uhc.game.tasks.TaskManager;
import fr.bgoodes.uhc.game.worlds.WorldManager;
import fr.bgoodes.uhc.utils.LogUtils;

/**
 * This class is responsible for managing the game.
 * It provides access to the game's managers and configuration.
 */
public class GameManager {
        // Managers
        private final PlayerManager playerManager;
        private final WorldManager worldManager;
        private final UHCModeManager uhcModeManager;
        private final ScenarioManager scenarioManager;
        private final TaskManager taskManager;

        private GameState state;

        // Configuration
        private final GameConfig configuration;

        public GameManager() {
                this.playerManager = new PlayerManager();
                this.worldManager = new WorldManager();
                this.uhcModeManager = new UHCModeManager();
                this.scenarioManager = new ScenarioManager();
                this.taskManager = new TaskManager();

                this.state = GameState.WAITING;

                ConfigService configService = new YMLConfigService(UHC.getFileHandler().defaultGameConfig.getFile());
                this.configuration = new GameConfig(configService);
        }

        // Getters
        public PlayerManager getPlayerManager() {
                return this.playerManager;
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

        public GameConfig getConfiguration() {
                return this.configuration;
        }

        // Game state
        public GameState getState() {
                return state;
        }

        public void setState(GameState state) {
                this.state = state;
        }

        public Boolean isStart() {
                return this.state == GameState.PLAYING || this.state == GameState.ENDING;
        }
}
