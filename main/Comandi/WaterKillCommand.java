package it.toninosas.main.Comandi;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class WaterKillCommand implements CommandExecutor, Listener {

    public static boolean kill = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("on")){
                    kill = true;
                    Bukkit.broadcastMessage("Server >> §bWaterkill On.");
                }else if(args[0].equalsIgnoreCase("off")){
                    kill = false;
                    Bukkit.broadcastMessage("Server >> §bWaterkill Off.");
                }
                else{
                    p.sendMessage("§b>> §aUtilizzo: /waterkill <on/off>");
                }
            }else{
                p.sendMessage("§b>> §aUtilizzo: /waterkill <on/off>");
            }
        }
        return true;
    }
}

