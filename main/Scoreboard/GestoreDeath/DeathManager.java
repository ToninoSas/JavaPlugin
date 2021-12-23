package it.toninosas.main.Scoreboard.GestoreDeath;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

//ricordati di registrare questo evento
public class DeathManager implements Listener {

    public static HashMap<UUID, Integer> deathMap = new HashMap<>();
    public static Integer deaths = 0;

    @EventHandler
    public void DeathEvent(PlayerDeathEvent e){
        deathMap.put(e.getEntity().getUniqueId(), GetDeaths(e.getEntity()) + 1);

        SaveDeaths(e.getEntity());
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();

        deaths = DeathManager.GetDeaths(p);
        deathMap.put(p.getUniqueId(), deaths);

        SaveDeaths(p);
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e){
        deathMap.remove(e.getPlayer().getUniqueId());
    }

    public static void SaveDeaths(Player p){
        GestioneConfigs.playerDataConfiguration.set(
                p.getName() + ".deaths", deathMap.get(p.getUniqueId()));
        GestioneConfigs.save();

        //AntonioSas:
        //  deaths: x
    }

    public static int GetDeaths(Player p){
        ConfigurationSection cs =
                GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName());

        assert cs != null;
        if (cs.get("deaths") == null){
            return 0;
        }else{
            return (int) cs.get("deaths");
        }
    }
}
