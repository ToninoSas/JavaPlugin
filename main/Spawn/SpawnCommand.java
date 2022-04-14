package it.toninosas.main.Spawn;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            ConfigurationSection cs =
                    GestioneConfigs.pluginConfiguration.getConfigurationSection("spawn");

            if (cs == null){
                p.sendMessage("§cSpawn non settato.");
                return true;

            }else {
                float yaw = (float) cs.getDouble("yaw");
                float pitch = (float) cs.getDouble("pitch");
                Location location = new Location(
                        Bukkit.getWorld(Objects.requireNonNull(cs.getString("world"))),
                        cs.getDouble("x"),
                        cs.getDouble("y"),
                        cs.getDouble("z"),
                        yaw,
                        pitch);

                p.teleport(location);
                p.sendMessage("§7+-----------------------------+");
                p.sendMessage("§2Sei stato tippato allo spawn!");
                p.sendMessage("§7+-----------------------------+");
                return true;
            }
        }else {
            System.out.println(Main.console);
            return true;
        }
    }
}
