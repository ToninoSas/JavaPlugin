package it.toninosas.main.Death;

import it.toninosas.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static it.toninosas.main.Death.DeathEventSavePosition.killed;
import static it.toninosas.main.Death.DeathEventSavePosition.deathLocationHashmap;

public class tpDeathCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (killed){
                p.teleport(deathLocationHashmap.get(p.getUniqueId()));
                p.sendMessage("§aSei stato tippato dove sei morto");
                deathLocationHashmap.remove(p.getUniqueId());
                killed = false;
            }else {
                p.sendMessage("§cNon sei morto recentemente!");
                p.sendMessage("§cE puoi tipparti una sola volta!");
            }
            return true;
        }else {
            System.out.println(Main.console);
        }
        return true;
    }
}
