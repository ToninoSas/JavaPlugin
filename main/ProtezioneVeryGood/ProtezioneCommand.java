package it.toninosas.main.ProtezioneVeryGood;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import it.toninosas.main.ProtezioneVeryGood.GestoreZoneProtette;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

//import static it.toninosas.main.Protezione.GestoreZoneProtette.*;
import static it.toninosas.main.ProtezioneVeryGood.GestoreZoneProtette.*;

public class ProtezioneCommand implements CommandExecutor {
    public static HashMap<UUID, Location> firstLocation = new HashMap<>();
    public static HashMap<UUID, Location> secondLocation = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){

            Player p = (Player) sender;
            Block block = p.getLocation().getBlock();

            TestManager.Point p1 = new TestManager.Point();
            TestManager.Point p2 = new TestManager.Point();

            //isClaimed mi returna null se non è claimato da nessuno
            String presuntoPlayer = TestManager.isClaimed(block, p);

            if(presuntoPlayer != null){
                //verifico se il blocco fa parte di qualche zona gia claimata
                p.sendMessage("§cQuesta zona e' gia protetta da " + presuntoPlayer);
                return true;
            }

            if(!firstLocation.containsKey(p.getUniqueId())){ //primo comando /protezione

                Location l = p.getLocation();
                firstLocation.put(p.getUniqueId(), l);

                //prendo le coord del primo punto
                p1.x = firstLocation.get(p.getUniqueId()).getBlockX();
                p1.z = firstLocation.get(p.getUniqueId()).getBlockZ();

                p.sendMessage("§5§m--------------------------------------------");
                p.sendMessage("");
                p.sendMessage("§dCoordinate del primo punto prese con successo!");
                p.sendMessage("§5X: " + p1.x + "  §5Z: " + p1.z);
                p.sendMessage("§cProcedere con la selezione del secondo punto.");
                p.sendMessage("");
                p.sendMessage("§5§m--------------------------------------------");

                return true;
            }




            Location l = p.getLocation();
            secondLocation.put(p.getUniqueId(), l);

            //prendo le coordinate dei due nuovi punti
            p1.x = firstLocation.get(p.getUniqueId()).getBlockX();
            p1.z = firstLocation.get(p.getUniqueId()).getBlockZ();
            p2.x = secondLocation.get(p.getUniqueId()).getBlockX();
            p2.z = secondLocation.get(p.getUniqueId()).getBlockZ();

            if(TestManager.containsClaim(p1, p2, p)){
                //controllo se i due punti nuovi, all interno hanno dei punti gia salvati nel file
                p.sendMessage("§cQuesta zona è gia occupata da qualcun'altro!");
                clear(p);
                return true;
            }

            TestManager.Point p3 = new TestManager.Point(); //creo il terzo punto per calcolare l'area
            p3.x = p1.x; //setto le coordinate del 3 punto
            p3.z = p2.z;

            GestoreZoneProtette.setup(p); //carico l'index delle zone e il limite dei blocchi

            if (areaManager(p1, p2, p3, p)){ //calcolo l area della zona
                p.sendMessage("§cLa tua zona selezionata è troppo grande! Supera il limite!");
                p.sendMessage("Blocchi protetti: " +
                        GestioneConfigs.protezioneConfiguration.getInt(p.getName() + ".blocksProtect") + "/" +
                        GestioneConfigs.protezioneConfiguration.getInt(p.getName() + ".blocksLimit"));
                clear(p);
                return true;
            }

            GestoreZoneProtette.loadZone(p); //carico i punti delle zone

            p.sendMessage("§5§m--------------------------------------------");
            p.sendMessage("");
            p.sendMessage("§dCoordinate del secondo punto prese con successo!");
            p.sendMessage("§5X: " + p2.x + " §5Z: " + p2.z);
            p.sendMessage("§aLuogo protetto con successo!");
            p.sendMessage("Blocchi protetti: " +
                    GestioneConfigs.protezioneConfiguration.getInt(p.getName() + ".blocksProtect") + "/" +
                    GestioneConfigs.protezioneConfiguration.getInt(p.getName() + ".blocksLimit"));
            clear(p);
            p.sendMessage("");
            p.sendMessage("§5§m--------------------------------------------");

            return true;

        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
