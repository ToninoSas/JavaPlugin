package it.toninosas.main.PrivateWarp;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class NewPrivateWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length != 1){
                p.sendMessage("Utilizzo: /newpwarp <nome warp privato>");
                return true;
            }else{
                Location warpLocation = p.getLocation();
                String nameWarp = args[0];

                ConfigurationSection cs =
                        GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName());

                if (cs.getConfigurationSection("privateWarps") == null){
                    cs.createSection("privateWarps");
                }

                cs = cs.getConfigurationSection("privateWarps");

                assert cs != null;
                cs.set(nameWarp + ".x", warpLocation.getBlockX());
                cs.set(nameWarp + ".y", warpLocation.getBlockY());
                cs.set(nameWarp + ".z", warpLocation.getBlockZ());
                cs.set(nameWarp + ".yaw", warpLocation.getYaw());
                cs.set(nameWarp + ".pitch", warpLocation.getPitch());
                cs.set(nameWarp + ".world", Objects.requireNonNull(warpLocation.getWorld()).getName());

                GestioneConfigs.save();

                p.sendMessage("Warp " + args[0] + " creato");
                return true;
            }
        }else {
            System.out.println("Non puoi eseguire questo comando da console.");
            return true;
        }
    }
}
