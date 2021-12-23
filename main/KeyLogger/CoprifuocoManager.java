package it.toninosas.main.KeyLogger;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

/*
comandi da registrare nel main:
/coprifuoco     --> 'CoprifuocoSetCommand'
/keyregister    --> 'KeyRegisterCommand'
/keyinsert      --> 'KeyInsertCommand'
/key            --> 'KeyCommand'

registra anche l evento 'CoprifuocoEvent'


 */


public class CoprifuocoManager {

    public static HashMap<UUID, Integer> keyHashmap = new HashMap<>();
    public static boolean state = false;

    public static boolean checkKey(){
        if (GestioneConfigs.pluginConfiguration.get("key") == null){
            Bukkit.broadcastMessage("Key non impostata. Coprifuoco senza effetto.");
            state = false;
            GestioneConfigs.pluginConfiguration.set("key", false);
            Bukkit.broadcastMessage("Coprifuoco impostato su: §coff");
            return false;
        }else{
            Bukkit.broadcastMessage("Il coprifuoco è attivo.");
            return true;
        }
    }

    public static void passCoprifuoco(Player p){
        if (checkKey()){
            keyHashmap.put(p.getUniqueId(), 30);
            BlockPlayer(p);
            p.sendMessage("Quando il coprifuoco è attivo, serve una key per giocare.");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (keyHashmap.containsKey(p.getUniqueId())){
                        if (keyHashmap.get(p.getUniqueId()) == 0){
                            p.kickPlayer("Il coprifuoco è attivo e non hai inserito la giusta key per entrare.");
                            clearPlayer(p);
                            this.cancel();
                        }else{
                            //qua aggiusta e rendilo piu carino
                            keyHashmap.put(p.getUniqueId(), keyHashmap.get(p.getUniqueId()) - 1);
                            p.sendTitle("§6Inserisci Key",
                                    "Digita: §c /keyinsert <key>",
                                    0, 30, 0
                            );
                            //inserisci anche i secondi rimanenti
                        }
                    }else{
                        clearPlayer(p);
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.plugin, 0, 20L);
        }else{
            FreePlayer(p);
        }
    }

    public static void BlockPlayer(Player p){
        p.setGameMode(GameMode.SPECTATOR);
        p.setFlying(true);
        p.setFlySpeed(0);
        //qua devi aggiungere un po di fatti,
    }

    public static void FreePlayer(Player p){
        p.setGameMode(GameMode.SURVIVAL);
        p.setFlying(false);
        p.setFlySpeed(0.1F);
        //anche qui
    }

    public static void clearPlayer(Player p){
        keyHashmap.remove(p.getUniqueId());
    }

    public static void CoprifuocoKick(String message){
        Bukkit.getOnlinePlayers().forEach(
                player -> player.kickPlayer(message));
    }

}
