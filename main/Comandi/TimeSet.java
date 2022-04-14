package it.toninosas.main.Comandi;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*

ti avviso, sto coso ti invalida il comando classico di minecraft /time set day

quindi se lo inserisci potrai utilizzare solo questo /time

aggiungi sia al main che al plugin.yml


 */

public class TimeSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length!=1){
            sender.sendMessage("Utilizzo: /time <giorno/notte>");
            return true;
        }

        if (args[0].equalsIgnoreCase("giorno")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                p.getWorld().setTime(1000);
                Bukkit.broadcastMessage("Impostato su: " + args[0]);
            }else{
                for (World world : Bukkit.getServer().getWorlds()){
                    world.setTime(1000);
                }
                Bukkit.broadcastMessage("Impostato su: " + args[0]);
            }
            return true;
        }else if (args[0].equalsIgnoreCase("notte")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                p.getWorld().setTime(13000);
                Bukkit.broadcastMessage("Impostato su: " + args[0]);
            }else{
                for (World world : Bukkit.getServer().getWorlds()){
                    world.setTime(13000);
                }
                Bukkit.broadcastMessage("Impostato su: " + args[0]);
            }
            return true;
        }else{
            sender.sendMessage("Utilizzo: /time <giorno/notte>");
            return true;
        }
    }
}
