package fr.bgoodes.uhc.game.tasks;

import java.util.ArrayList;
import java.util.List;


public class TaskManager {

    private UHCTask mainTask;
    private final List<UHCTask> subTasks;

    public TaskManager() {
        this.subTasks = new ArrayList<>();
    }

    public void setMainTask(UHCTask mainTask) {
        if (this.mainTask != null && this.mainTask.isRunning())
            this.mainTask.stop();
        this.mainTask = mainTask;
    }

    public void runMainTask() {
        if (this.mainTask != null && !this.mainTask.isRunning())
            this.mainTask.start();
    }

    public void stopMainTask() {
        if (this.mainTask != null && this.mainTask.isRunning())
            this.mainTask.stop();
    }

    public void addSubTask(UHCTask task) {
        this.subTasks.add(task);
        task.start();
    }

    public void removeSubTask(UHCTask task) {
        task.stop();
        this.subTasks.remove(task);
    }

    public void stopAllSubTasks() {
        for (UHCTask task : subTasks)
            task.stop();
        subTasks.clear();
    }
}