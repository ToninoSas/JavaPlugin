package it.toninosas.main.ProtezioneVeryGood;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ProtezioneManager {
    public static boolean isClaimed(Block b){
        int x = b.getLocation().getBlockX();
        int z = b.getLocation().getBlockZ();
        //for(Player p : Bukkit.getOnlinePlayers()){
            //if(t == p){
                //Bukkit.broadcastMessage(Main.debug + "Test primo if.");
                //return false;
            //}
            for(String st : GestioneConfigs.protezioneConfiguration.getKeys(true)) {
                for(Player t : Bukkit.getOnlinePlayers()){
                    if(t.getName().equals(st)){
                        Bukkit.broadcastMessage("Test primo if");
                        return false;
                    }
                }
            if(GestioneConfigs.protezioneConfiguration.contains(st)){
                int x1 = Math.min(GestioneConfigs.protezioneConfiguration.getInt(st + ".1.X"),
                        GestioneConfigs.protezioneConfiguration.getInt(st + ".2.X"));
                int x2 = Math.max(GestioneConfigs.protezioneConfiguration.getInt(st + ".1.X"),
                        GestioneConfigs.protezioneConfiguration.getInt(st + ".2.X"));
                int z1 = Math.min(GestioneConfigs.protezioneConfiguration.getInt(st + ".1.Z"),
                        GestioneConfigs.protezioneConfiguration.getInt(st + ".2.Z"));
                int z2 = Math.max(GestioneConfigs.protezioneConfiguration.getInt(st + ".1.Z"),
                        GestioneConfigs.protezioneConfiguration.getInt(st + ".2.Z"));
                if(x >= x1 && x <= x2 && z >= z1 && z <= z2){
                    return true;
                }
            }
        }
        return false;
    }
}
