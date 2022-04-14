package it.toninosas.main.Tp;

import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpaccept implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){

            Player p = (Player) sender;

            if(args.length !=0){
                p.sendMessage("/tpaccept - /tpadeny");
                return true;
            }
            if(TpManager.tpa.containsKey(p.getUniqueId())){

                Player t = Bukkit.getPlayer(TpManager.tpa.get(p.getUniqueId()));

                assert t != null;
                t.sendMessage(p.getDisplayName()+ "ha accettato la richiesta di teletrasporto");
                t.teleport(p.getLocation());

                p.sendMessage("Richiesta accettata");

                TpManager.clear(p);

                return true;
            }
            else{
                if (TpManager.time.get(p.getUniqueId()) == null){
                    p.sendMessage("la Richiesta di tp da parte di " + TpManager.tpa.get(p.getUniqueId()) + " è scaduta");

                }else{
                    p.sendMessage("Non hai richieste di teletrasporto in sospeso.");

                }

                return true;
            }

        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
