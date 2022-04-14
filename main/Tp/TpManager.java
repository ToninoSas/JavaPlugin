package it.toninosas.main.Tp;

import it.toninosas.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class TpManager {

    public static HashMap<UUID, UUID> tpa = new HashMap<>();
    public static HashMap<UUID, Integer> time = new HashMap<>();

    public static void clear(Player p ){
        tpa.remove(p.getUniqueId());
        time.remove(p.getUniqueId());
    }

    public static void Timer(Player p, Player t){

        time.put(p.getUniqueId(), 45);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (tpa.containsKey(p.getUniqueId())){
                    if (time.get(p.getUniqueId()) == 0){
                        p.sendMessage("Richiesta di tp a " + p.getName() + " scaduta");
                        clear(p);
                        this.cancel();
                    }else{
                        p.sendMessage(": " + time.get(p.getUniqueId()));
                        time.put(p.getUniqueId(), time.get(p.getUniqueId()) - 1);
                    }
                }else{
                    clear(p);
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.plugin, 0, 20L);
    }

}
