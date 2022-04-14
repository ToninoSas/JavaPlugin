package it.toninosas.main.Zone;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class NewZonaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){

            Player p = (Player) sender;

            if (args.length != 1){
                p.sendMessage("§7+-----------------------------+");
                p.sendMessage("§aUtilizzo: /newzona <nome zona>");
                p.sendMessage("§7+-----------------------------+");
                return true;

            }else{

                Location zonaLocation = p.getLocation();
                GestioneConfigs.zonaConfiguration.createSection(args[0]);
                ConfigurationSection cs = GestioneConfigs.zonaConfiguration.getConfigurationSection(args[0]);

                assert cs != null;
                cs.set("x", zonaLocation.getBlockX());
                cs.set("y", zonaLocation.getBlockY());
                cs.set("z", zonaLocation.getBlockZ());
                cs.set("yaw", zonaLocation.getYaw());
                cs.set("pitch", zonaLocation.getPitch());
                cs.set("world", Objects.requireNonNull(zonaLocation.getWorld()).getName());
                cs.set("creator", p.getName());

                GestioneConfigs.save();

                p.sendMessage("§7+-----------------------------+");
                p.sendMessage("§2Zona §9" + args[0] + " §2creata.");
                p.sendMessage("§7+-----------------------------+");
                return true;
            }
        }else {
            System.out.println(Main.console);
            return true;
        }
    }
}
