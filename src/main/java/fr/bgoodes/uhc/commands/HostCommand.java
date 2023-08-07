package fr.bgoodes.uhc.commands;

import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.files.lang.TranslationKey;
import fr.bgoodes.uhc.game.GameManager;
import fr.bgoodes.uhc.game.GameState;
import fr.bgoodes.uhc.game.tasks.core.StartingTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HostCommand implements BaseComand {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        GameManager gameManager = UHC.getGameManager();

        if (gameManager.getState() == GameState.WAITING)
            gameManager.enterState(GameState.STARTING);
        else if (gameManager.getState() == GameState.STARTING) {
            ((StartingTask) gameManager.getTaskManager().getCoreTask()).cancelStart();
        }


        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
