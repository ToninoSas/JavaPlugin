package it.toninosas.main.Zone;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteZonaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            String lista = "";

            if (args.length != 1){
                p.sendMessage("§7+-----------------------------+");
                p.sendMessage("§aUtilizzo: /deletezona <nome zona>");

                for (String key : GestioneConfigs.zonaConfiguration.getKeys(false)){
                    lista = lista + key + " - ";
                }

                if (lista.equals("")){
                    p.sendMessage("§aNon ci sono zone disponibili. Creale.");
                    p.sendMessage("§7+-----------------------------+");
                }else{
                    p.sendMessage("§aZone disponibili: ");
                    p.sendMessage("§2" + lista);
                    p.sendMessage("§7+-----------------------------+");
                }

                return true;

            }else {
                if (GestioneConfigs.zonaConfiguration.contains(args[0])){

                    GestioneConfigs.zonaConfiguration.set(args[0], null);
                    GestioneConfigs.save();

                    p.sendMessage("§7+-----------------------------+");
                    p.sendMessage("§2Zona §9" + args[0] +"§2 eliminata.");
                    p.sendMessage("§7+-----------------------------+");

                } else{
                    p.sendMessage("§7+-----------------------------+");
                    p.sendMessage("§cZona: §9" + args[0] + "§c, non trovata");
                    p.sendMessage("§7+-----------------------------+");
                }
                return true;
            }
        }else{
            return true;
        }
    }
}
