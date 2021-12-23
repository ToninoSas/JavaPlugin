package it.toninosas.main.Home;

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

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            ConfigurationSection cs = Objects.requireNonNull(
                    GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName())
            ).getConfigurationSection("home");

            if (GestioneConfigs.playerDataConfiguration.get(p.getName() + ".home") == null){
                p.sendMessage("§cHome non settata.");

            }else {

                assert cs != null;
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
                p.sendMessage("§2Sei stato tippato alla tua home!");
            }
            return true;
        }else {
            System.out.println(Main.console);
            return true;
        }
    }
}
