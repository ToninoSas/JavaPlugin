package it.toninosas.main.Zone;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ZonaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;
            String lista = "";
            int i = 0;

            if (args.length!=1){
                p.sendMessage("§7+-----------------------------+");
                p.sendMessage("§b>> §aUtilizzo: /zona <nomezona>");

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

            }else{

                if (GestioneConfigs.zonaConfiguration.getConfigurationSection(args[0]) == null){
                    p.sendMessage("§7+-----------------------------+");
                    p.sendMessage("§cZona: §2" + args[0] + ",§c non trovata.");
                    p.sendMessage("§7+-----------------------------+");

                }else{

                    ConfigurationSection cs =
                            GestioneConfigs.zonaConfiguration.getConfigurationSection(args[0]);

                    assert cs != null;
                    float yaw = (float) cs.getDouble("yaw");
                    float pitch = (float) cs.getDouble("pitch");
                    Location location = new Location(
                            Bukkit.getWorld(Objects.requireNonNull(cs.getString("world"))),
                            cs.getDouble("x"),
                            cs.getDouble("y"),
                            cs.getDouble("z"),
                            yaw,
                            pitch);

                    p.teleport(location);
                    p.sendMessage("§7+-----------------------------+");
                    p.sendMessage("§2Sei stato tippato alla zona: §2" + args[0]);
                    p.sendMessage("§7+-----------------------------+");
                }
                return true;
            }
        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}

