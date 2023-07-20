package fr.bgoodes.uhc.game.tasks;

public interface UHCTaskProvider<T extends UHCTask> {
    T provide();
}