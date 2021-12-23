package it.toninosas.main.KeyLogger;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class KeyInsertCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //keyinsert <key>
        //keyregister <key> <key>

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length!=1){
                p.sendMessage("Utilizzo: /keyinsert <key>");
                return true;
            }

            if (GestioneConfigs.pluginConfiguration.getBoolean("coprifuoco")){

                if (CoprifuocoManager.keyHashmap.containsKey(p.getUniqueId())){

                    if (Objects.equals(GestioneConfigs.pluginConfiguration.get("key"), args[0])){
                        //modifica un po qua l estetica
                        CoprifuocoManager.FreePlayer(p);
                        CoprifuocoManager.clearPlayer(p);

                        p.sendTitle("§bBuon divertimento!",
                                "§3 " + p.getDisplayName(),
                                5,
                                10,
                                5);

                        p.sendMessage("Key inserita. Ora sei libero di giocare.");
                        return true;
                    }else{
                        p.sendMessage("La key non corrisponde.");
                        return true;
                    }
                }else{
                    p.sendMessage("Hai gia inserito la key.");
                    return true;
                }
            }else{
                p.sendMessage("Il coprifuoco è spento.");
                return true;
            }
        }else{
            sender.sendMessage("Sei da console. fai cacca");
            return true;
        }


    }
}

