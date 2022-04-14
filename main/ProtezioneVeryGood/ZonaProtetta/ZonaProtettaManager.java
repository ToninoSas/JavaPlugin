package it.toninosas.main.ProtezioneVeryGood.ZonaProtetta;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ZonaProtettaManager {

    ZonaProtettaManager(Player p, String zName, HashMap<UUID, Location> hashMap, HashMap<UUID, Location> hashMap2){
        player = p; nomeZona = zName; firstPoint = hashMap; secondPoint = hashMap2;
    }

    static class Point{
        public int x, z;
    }

    private static String nomeZona;
    private static Player player;
    private static HashMap<UUID, Location> firstPoint;
    private static HashMap<UUID, Location> secondPoint;

    public static boolean containsClaim(Point newPoint1, Point newPoint2, Player p){
        Point oldPoint1 = new Point(), oldPoint2 = new Point();

        //analizzo 1 per 1 i player
        for(String playerName : GestioneConfigs.protezioneConfiguration.getKeys(false)){
            //analizzo le loro zone
            for(String zona : Objects.requireNonNull(
                    GestioneConfigs.protezioneConfiguration.getConfigurationSection(playerName)).getKeys(false)){

                oldPoint1.x = GestioneConfigs.protezioneConfiguration.getInt(playerName+"."+zona+".p1.x");
                oldPoint1.z = GestioneConfigs.protezioneConfiguration.getInt(playerName+"."+zona+".p1.z");

                oldPoint2.x = GestioneConfigs.protezioneConfiguration.getInt(playerName+"."+zona+".p2.x");
                oldPoint2.z = GestioneConfigs.protezioneConfiguration.getInt(playerName+"."+zona+".p2.z");

                if(findPoint(newPoint1, newPoint2, oldPoint1) || findPoint(newPoint1, newPoint2, oldPoint2)){
                    //punto trovato nel database
                    //il punto appartiene al player che sta reclamando?
                    if(playerName.equals(p.getName())) return false;
                    else return true;
                }
            }
        }

        return false;
    }

    public static boolean findPoint(Point a, Point b, Point p){

        int Xmax, Xmin, Zmax, Zmin;

        Xmax = Math.max((a.x), (b.x));
        Xmin = Math.min((a.x), (b.x));

        Zmax = Math.max((a.z), (b.z));
        Zmin = Math.min((a.z), (b.z));

        if((p.x >= Xmin && p.x <= Xmax) && (p.z >= Zmin && p.z <= Zmax)) {
            //appartiene
            return true;
        }

        return false;
    }

    public void saveZone(){
        //aggiorno l'indice delle zone
        String string = player.getName() + "." + nomeZona;

        GestioneConfigs.protezioneConfiguration.set(string + ".world", firstPoint.get(player.getUniqueId()).getWorld().getName());
        GestioneConfigs.protezioneConfiguration.set(string + ".p1.x", firstPoint.get(player.getUniqueId()).getBlockX());
        GestioneConfigs.protezioneConfiguration.set(string + ".p1.z", firstPoint.get(player.getUniqueId()).getBlockZ());
        GestioneConfigs.protezioneConfiguration.set(string + ".p2.x", secondPoint.get(player.getUniqueId()).getBlockX());
        GestioneConfigs.protezioneConfiguration.set(string + ".p2.z", secondPoint.get(player.getUniqueId()).getBlockZ());

        GestioneConfigs.save();
    }



}
