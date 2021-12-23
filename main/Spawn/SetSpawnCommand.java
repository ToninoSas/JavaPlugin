package it.toninosas.main.Spawn;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            Location spawnLocation = p.getLocation();

            World world = p.getWorld();
            world.setSpawnLocation(spawnLocation);
            world.save();

            GestioneConfigs.pluginConfiguration.createSection("spawn");
            ConfigurationSection cs = GestioneConfigs.pluginConfiguration.getConfigurationSection("spawn");

            assert cs != null;
            cs.set("x", spawnLocation.getBlockX());
            cs.set("y", spawnLocation.getBlockY());
            cs.set("z", spawnLocation.getBlockZ());
            cs.set("yaw", spawnLocation.getYaw());
            cs.set("pitch", spawnLocation.getPitch());
            cs.set("world", Objects.requireNonNull(spawnLocation.getWorld()).getName());

            GestioneConfigs.save();

            Bukkit.broadcastMessage("§bSpawn per tutti i giocatori settato.");

            return true;
        }else{
            System.out.println(Main.console);
            return true;
        }

    }
}
