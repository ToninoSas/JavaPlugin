package it.toninosas.main.Home;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            Location homeLocation = p.getLocation();

            ConfigurationSection cs = Objects.requireNonNull(
                    GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName())
            ).createSection("home");

            cs.set("x", homeLocation.getBlockX());
            cs.set("y", homeLocation.getBlockY());
            cs.set("z", homeLocation.getBlockZ());
            cs.set("yaw", homeLocation.getYaw());
            cs.set("pitch", homeLocation.getPitch());
            cs.set("world", Objects.requireNonNull(homeLocation.getWorld()).getName());
            GestioneConfigs.save();

            p.sendMessage("§7+-----------------------------+");
            p.sendMessage("§2Home settata.");
            p.sendMessage("§7+-----------------------------+");

            return true;
        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
