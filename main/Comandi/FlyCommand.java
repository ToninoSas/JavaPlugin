package it.toninosas.main.Comandi;

import it.toninosas.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
                if (args.length == 1){
                    if(args[0].equalsIgnoreCase("on")){
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.sendMessage("§b>> §2Fly abilitata.");
                        return true;
                    }else if(args[0].equalsIgnoreCase("off")){
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.sendMessage("§b>> §2Fly disabilitata");
                        return true;
                    }else{
                        p.sendMessage("§b>> §aUtilizzo: /fly <on/off>");
                        return true;
                    }
                }else{
                    p.sendMessage("§b>> §aUtilizzo: /fly <on/off>");
                    return true;
                }
        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
