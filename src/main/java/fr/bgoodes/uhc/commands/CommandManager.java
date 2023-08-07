package fr.bgoodes.uhc.commands;


import fr.bgoodes.uhc.UHC;
import fr.bgoodes.uhc.utils.LogUtils;
import org.bukkit.command.PluginCommand;

public class CommandManager {
    private final UHC plugin;

    public CommandManager(UHC plugin) {
        this.plugin = plugin;
    }

    public void registerCommand(BaseComand command, String... names) {
        for (String name : names) {

            PluginCommand pluginCommand = plugin.getCommand(name);

            if (pluginCommand == null) {
                LogUtils.warning("Command '"+ name + "' not registered in the plugin.yml");
                continue;
            }

            pluginCommand.setExecutor(command);
            pluginCommand.setTabCompleter(command);
        }
    }

    public void initializeCommands() {
        registerCommand(new HostCommand(), "host", "h");
    }
}