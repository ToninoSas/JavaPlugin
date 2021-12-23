package it.toninosas.main.Death;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class DeathEventSavePosition implements Listener {

    public static HashMap<UUID, Location> deathLocationHashmap = new HashMap<>();
    public static boolean killed = false;

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        killed = true;

        Player p = e.getEntity();
        e.setDeathMessage("§6" + p.getDisplayName() + "§7 è crepato...");

        Location locationDeath = p.getLocation();

        double x = locationDeath.getBlockX();
        double y = locationDeath.getBlockY();
        double z = locationDeath.getBlockZ();

        deathLocationHashmap.put(p.getUniqueId(), locationDeath);
        p.sendMessage("§7+--------------------------------+");
        p.sendMessage("§aCoordinate di morte: " + x + ", " + y + ", " + z);
        p.sendMessage("§9/tpdeath §aper tipparti alle cordinate.");
        p.sendMessage("§a di morte");
        p.sendMessage("§7+--------------------------------+");

    }
}
