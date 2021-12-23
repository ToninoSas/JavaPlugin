package it.toninosas.main.Tp;

import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {

//    TpManager tp_Manager;
//    public TpaCommand(TpManager tpManager1){
//        tp_Manager = tpManager1;
//    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if(args.length !=1){
                p.sendMessage("/tpa <nome giocatore>");
                return true;

            }else{
                Player t = Bukkit.getServer().getPlayer(args[0]);
                if(t == null){
                    p.sendMessage("Player offline!");
                    return true;

                }else{

                    TpManager.tpa.put(p.getUniqueId(), t.getUniqueId());
                    TpManager.Timer(p, t);

                    p.sendMessage("Hai invitato una richiesta di tp a "+ t.getName());
                    t.sendMessage(p.getName() + " vuole teletrasportarsi da te!");
                    t.sendMessage("/tpaccept accettare, /tpadeny rifiutare");


                    return true;
                }
            }
        }else{
            System.out.println(Main.console);
            return true;
        }
    }

}
