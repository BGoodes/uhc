package fr.bgoodes.uhc.game.tasks;

public interface UHCTask extends Runnable {

    void start();

    void stop();

    boolean isRunning();
}
