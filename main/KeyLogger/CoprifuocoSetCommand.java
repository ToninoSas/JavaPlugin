package it.toninosas.main.KeyLogger;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class CoprifuocoSetCommand implements CommandExecutor {
    //coprifuoco on
    //coprifuoco off

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length!=1){
            sender.sendMessage("Utilizzo: /coprifuoco <on/off>");
            return true;
        }

        if (Objects.equals(args[0], "on")){
            if (GestioneConfigs.pluginConfiguration.getBoolean("coprifuoco")){ //verifico che non sia gia attivo
                sender.sendMessage("Coprifuoco gia attivo.");
            }else{
                CoprifuocoManager.state = true;
                //decidi dove salvare il valore
                GestioneConfigs.pluginConfiguration.set("coprifuoco", true);
                GestioneConfigs.save();

                Bukkit.broadcastMessage("Coprifuoco settato su: on");
                CoprifuocoManager.CoprifuocoKick("Il coprifuoco è stato attivato. Utilizza la key per entrare.");
            }
            return true;
        }else if(Objects.equals(args[0], "off")){
            if (!GestioneConfigs.pluginConfiguration.getBoolean("coprifuoco")){
                sender.sendMessage("Coprifuoco gia spento.");
            }else{
                CoprifuocoManager.state = false;
                //decidi dove salvare il valore
                GestioneConfigs.pluginConfiguration.set("coprifuoco", false);
                GestioneConfigs.save();
                Bukkit.broadcastMessage("Coprifuoco settato su: off");
            }
            return true;
        }else{
            sender.sendMessage("Utilizzo: /coprifuoco <on/off>");
        }

        return true;
    }
}
