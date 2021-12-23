package it.toninosas.main.Protezione;

import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProtezioneEvents implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if(TestManager.isClaimed(b, p) != null){
            e.setCancelled(true);
//            p.sendMessage("Non puoi spaccare qui.");
            p.sendMessage("Questa zona appartiene a " + TestManager.isClaimed(b, p));
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if(TestManager.isClaimed(b, p) != null){ //isclaimed stampa la scritta 'zona protetta da: '
            e.setCancelled(true);
//            p.sendMessage("Non puoi piazzare qui.");
            p.sendMessage("Questa zona appartiene a " + TestManager.isClaimed(b, p));
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if(b != null){
            if(TestManager.isClaimed(b, p) != null){
                if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                    if(b.getType().equals(Material.CHEST) ||
                            b.getType().equals(Material.HOPPER) ||
                            b.getType().equals(Material.FURNACE) ||
                            b.getType().equals(Material.ENDER_CHEST) ||
                            b.getType().equals(Material.CHEST_MINECART) ||
                            b.getType().equals(Material.FURNACE_MINECART) ||
                            b.getType().equals(Material.HOPPER_MINECART)
                    ){
                        e.setCancelled(true);
//                        p.sendMessage("Non puoi interagire.");
                        p.sendMessage("Questa zona appartiene a " + TestManager.isClaimed(b, p));

                    }
                }

            }
        }
    }
}
