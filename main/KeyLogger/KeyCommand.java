package it.toninosas.main.KeyLogger;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KeyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("La key è: §2" + GestioneConfigs.pluginConfiguration.get("key"));
        return true;
    }
}
