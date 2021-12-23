package it.toninosas.main.PrivateWarp;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DeletePrivateWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            String privateWarpList = "";

            ConfigurationSection cs =
                    Objects.requireNonNull(GestioneConfigs.playerDataConfiguration.getConfigurationSection(
                            p.getName())).getConfigurationSection("privateWarps");
            if (args.length!=1){
                p.sendMessage("Utilizzo: /deletepwarp <nome warp privato>");
                try {
                    for (String key : cs.getKeys(false)){
                        privateWarpList = privateWarpList + key + " - ";
                    }

                    if (privateWarpList.equals("")){
                        p.sendMessage("Non ci sono warp privati disponibili.");
                    }else{
                        p.sendMessage("Warp disponibili: ");
                        p.sendMessage(privateWarpList);
                    }
                }catch (NullPointerException e){
//                    p.sendMessage("Non hai ancora creato nessun warp privato.");
                }

                return true;
            }else{
                try {
                    if (cs.get(args[0])== null){
                            p.sendMessage("Warp non trovato");
                        }else{
                            cs.set(args[0], null);
                            GestioneConfigs.save();
                            p.sendMessage("Warp eliminato.");
                        }
                }catch (NullPointerException e){
                    p.sendMessage("Non hai ancora creato nessun warp privato.");
                }
                return true;
            }
        }else{
            System.out.println("Non puoi eseguire questo comando da console.");
            return true;
        }
    }
}
