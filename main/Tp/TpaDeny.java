package it.toninosas.main.Tp;

import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaDeny implements CommandExecutor {

//    TpManager tp_Manager;
//    public TpaDeny(TpManager tpManager1){
//        tp_Manager = tpManager1;
//    }


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

                t.sendMessage(p.getDisplayName()+ "ha rifiutato la richiesta di teletrasporto");
                p.sendMessage("Richiesta rifiutata");

                TpManager.clear(p);

                return true;

            }
            else{
                p.sendMessage("Non hai richieste di teletrasporto in sospeso.");
                return true;
            }

        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
