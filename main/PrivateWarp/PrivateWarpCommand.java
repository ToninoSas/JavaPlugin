package it.toninosas.main.PrivateWarp;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

/*
* RICORDATI DI REGISTRARE NEL MAIN I SEGUENTI COMANDI:
*   -   deletepwarp
*   -   newpwarp
*   -   pwarp
*
*  AGGIUNGILI ANCHE NEL PLUGIN.YML
*
* SOSTITUISCI 'PLAYERDATACONFIGURATION' CON IL TUO FILE.YML
* QUESTA è LA GERARCHIA:
*
* PLAYERNAME:
*   privateWarps:
*       warp1:
*           x:
*           y:
*           z:
*       warp2:
*           x:
*           y:
*           z:
*
*   HO UTILIZZATO IL TRY/CATCH PERCHE OVVIAMENTE SE LA VOCE PRIVATEWARPS NON è STATA ANCORA CREATA,
*   AI VARI COMANDI /PWARP E /DELETEPWARP DAVA ERRORE.
*   PER RISOLVERE HO UTILIZZATO TRY/CATCH.
*   MA CI SONO ALTRE SOLUZIONI VALIDE, IO HO UTILIZZATO QUESTO PER SBRIGARMI.
*
*   AGGIUSTA ANCHE I COLORI.
*
*   GRAZIE :3
*
*
* */



public class PrivateWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;
            String privateWarpList = "";

            ConfigurationSection cs =
                    Objects.requireNonNull(GestioneConfigs.playerDataConfiguration.getConfigurationSection(
                            p.getName())).getConfigurationSection("privateWarps");

            if (args.length != 1) {
                p.sendMessage("Utilizzo: /pwarp <nome warp privato>");

                try {
                    for (String key : cs.getKeys(false)) {
                        privateWarpList = privateWarpList + key + " - ";
                    }

                    if (privateWarpList.equals("")) {
                        p.sendMessage("Non ci sono warp privati disponibili. Creali");
                    } else {
                        p.sendMessage("Warp disponibili: ");
                        p.sendMessage(privateWarpList);
                    }
                }catch (NullPointerException e){
//                    p.sendMessage("Non hai ancora creato nessun warp privato.");
                }

                return true;
            } else {
                try {
                    if (cs.get(args[0]) == null) {
                        p.sendMessage("Warp non trovato");
                    } else {
                        float yaw = (float) cs.getDouble(args[0] + "yaw");
                        float pitch = (float) cs.getDouble(args[0] + "pitch");

                        Location location = new Location(
                                Bukkit.getWorld(Objects.requireNonNull(cs.getString(args[0] + ".world"))),
                                cs.getDouble(args[0] + ".x"),
                                cs.getDouble(args[0] + ".y"),
                                cs.getDouble(args[0] + ".z"),
                                yaw,
                                pitch);

                        p.teleport(location);
                        p.sendMessage("§2Sei stato tippato al warp: §2" + args[0]);
                    }
                }catch (NullPointerException e){
                    p.sendMessage("Non hai ancora creato nessun warp privato.");
                }

                return true;
            }
        } else {
            System.out.println("Non puoi eseguire questo comando da console.");
            return true;
        }
    }
}


