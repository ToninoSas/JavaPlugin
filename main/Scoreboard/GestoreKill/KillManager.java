package it.toninosas.main.Scoreboard.GestoreKill;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

//ricordati di registrare questo evento
public class KillManager implements Listener {

    public static HashMap<UUID, Integer> killMap = new HashMap<>();
    public static Integer kills = 0;

    @EventHandler
    public void KillEvent(PlayerDeathEvent e){
        if (e.getEntity().getKiller() instanceof Player){
            Player killer = e.getEntity().getKiller();

            killMap.put(killer.getUniqueId(), GetKills(killer) + 1);

            SaveKills(killer);
        }
    }

    @EventHandler
    public void OnJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();

        kills = GetKills(p);
        killMap.put(p.getUniqueId(), kills);

        SaveKills(p);
    }

    public static void SaveKills(Player p){
        GestioneConfigs.playerDataConfiguration.set(
                p.getName() + ".kills", killMap.get(p.getUniqueId()));
        GestioneConfigs.save();

        //AntonioSas:
        //  kills: x
    }

    public static int GetKills(Player p){
        ConfigurationSection cs =
                GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName());

        assert cs != null;
        if (cs.get("kills") == null){
            return 0;
        }else{
            return (int) cs.get("kills");
        }
    }
}
