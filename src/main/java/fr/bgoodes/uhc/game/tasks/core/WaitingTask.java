package fr.bgoodes.uhc.game.tasks.core;

import fr.bgoodes.uhc.game.tasks.UHCTask;

public class WaitingTask extends UHCTask {
    @Override
    public void start() {

    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {
        cancel();
    }
}
