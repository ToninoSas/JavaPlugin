package it.toninosas.main.PvP;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class pvpEvent implements Listener {
    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e){
        if (!pvpCommand.map.contains(e.getDamager().getUniqueId())){
            e.setCancelled(true);
        }
    }
}
