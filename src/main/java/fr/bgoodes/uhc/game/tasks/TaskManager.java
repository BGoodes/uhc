package fr.bgoodes.uhc.game.tasks;

import fr.bgoodes.uhc.game.GameState;
import fr.bgoodes.uhc.game.tasks.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskManager {

    private final Map<GameState, UHCTaskProvider<?>> coreTasks;
    private final List<UHCTask> subTasks;
    private UHCTask currentCoreTask;

    public TaskManager() {
        this.coreTasks = new HashMap<>();
        this.subTasks = new ArrayList<>();

        // Register main tasks
        registerMainTask(GameState.WAITING, WaitingTask::new);
        registerMainTask(GameState.STARTING, StartingTask::new);
        registerMainTask(GameState.LOADING, LoadingTask::new);
        registerMainTask(GameState.PLAYING, PlayingTask::new);
        registerMainTask(GameState.ENDING, EndingTask::new);
    }

    private <T extends UHCTask> void registerMainTask(GameState state, UHCTaskProvider<T> provider) {
        this.coreTasks.put(state, provider);
    }

    public void enterState(GameState state) {
        UHCTaskProvider<?> provider = coreTasks.get(state);
        if (currentCoreTask != null && currentCoreTask.isRunning())
            currentCoreTask.stop();
        if (provider != null) {
            currentCoreTask = provider.provide();
            currentCoreTask.start();
        }
    }

    public UHCTask getCoreTask() {
        return currentCoreTask;
    }

    public void addSubTask(UHCTask task) {
        this.subTasks.add(task);
        task.start();
    }

    public void removeSubTask(UHCTask task) {
        task.stop();
        this.subTasks.remove(task);
    }

    public void stopAllTasks() {
        if (currentCoreTask != null && currentCoreTask.isRunning()) {
            currentCoreTask.stop();
            currentCoreTask = null;
        }
        for (UHCTask task : subTasks)
            task.stop();
        subTasks.clear();
    }
}