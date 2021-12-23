package it.toninosas.main.Comandi;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrivateMessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length < 2){
             //   p.sendMessage("§7+--------------------------------+");
                p.sendMessage("§b>> §aUtilizzo: /msg <nome giocatore> <messaggio>");
          //      p.sendMessage("§7+--------------------------------+");
                return true;
            }else{
                String message = "";
                for(int i = 1; i<= args.length - 1; i++){
                    message = message + args[i] + " ";
                }
                Player p2 = Bukkit.getPlayer(args[0]);
                if (p2==null){
          //          p.sendMessage("§7+--------------------------------+");
                    p.sendMessage("§b>> §cGiocatore non trovato");
          //          p.sendMessage("§7+--------------------------------+");
                    return true;
                }else{
                    p2.sendMessage("(Da " + p.getDisplayName() + ") " + message );
                    p.sendMessage("(A " + p2.getDisplayName() + ") " + message);
                    return true;
                }
            }
        }else{
            System.out.println("Ciao console");
            return true;
        }

    }
}
