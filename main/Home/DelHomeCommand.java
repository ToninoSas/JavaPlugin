package it.toninosas.main.Home;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DelHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            ConfigurationSection cs = Objects.requireNonNull(
                    GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName()));

            if (GestioneConfigs.playerDataConfiguration.get(p.getName() + ".home") == null){
                p.sendMessage("§cHome non settata.");

            }else {

                cs.set("home", null);
                GestioneConfigs.save();

                p.sendMessage("Home cancellata.");
            }
            return true;
        }else {
            System.out.println(Main.console);
            return true;
        }
    }
}
