package it.toninosas.main.KeyLogger;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class KeyRegisterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender.hasPermission("Key.Register")) { //poi qua te la vedi tu con i rank
                if (args.length != 2) {
                    sender.sendMessage("§b>> §aUtilizzo: /keyregister <key> <key>");
                    return true;
                } else {
                    if (Objects.equals(args[0], args[1])) {
                        GestioneConfigs.pluginConfiguration.set("key", args[0]);
                        GestioneConfigs.save();

                        Bukkit.broadcastMessage("E stata impostata la nuova key §c" + args[0] + " §fper il coprifuoco.");
                        if (GestioneConfigs.pluginConfiguration.getBoolean("coprifuoco")) { //dopo che ho cambiato la key, nel caso il coprifuoco fosse attivo
                            //kicko tutti i giocatori
                            //se vuoi qui potresti far aspettare del tempo prima di kickare i giocatori
                            CoprifuocoManager.CoprifuocoKick("La key è stata cambiata. ");
                            return true;
                        }
                    } else {
                        sender.sendMessage("§b>> §cLe due key non corrispondono");
                    }
                    return true;
                }
            }else{
                sender.sendMessage("Non hai i permessi");
                return true;
            }
        }
    }

