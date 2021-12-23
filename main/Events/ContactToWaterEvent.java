package it.toninosas.main.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static it.toninosas.main.Comandi.WaterKillCommand.kill;

public class ContactToWaterEvent implements Listener {
    @EventHandler
    public void PlayerToWater(PlayerMoveEvent event){
        Material m = event.getPlayer().getLocation().getBlock().getType();
        if (kill){
            if (m == Material.WATER){
                event.getPlayer().setHealth(0);
            }else {
                event.setCancelled(false);
            }
        }else{
            event.setCancelled(false);
        }
    }
}
