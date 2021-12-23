package it.toninosas.main.Scoreboard.GestoreCoords;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CoordsManager {

    public static HashMap<UUID, Location> locationHashMap = new HashMap<>();
    public static Location location = null;

    public static Location GetLocation(Player p){
        location = p.getLocation();
        locationHashMap.put(p.getUniqueId(), location);
        return locationHashMap.get(p.getUniqueId());
    }

}
