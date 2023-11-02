package fr.bgoodes.uhc.game.tasks.core;

import fr.bgoodes.uhc.game.tasks.UHCTask;

public class PlayingTask extends UHCTask {
    private int time;

    @Override
    public void start() {
        this.time = 0;
    }

    @Override
    public void run() {
        time++;
    }

    @Override
    public void stop() {
        cancel();
    }
}
