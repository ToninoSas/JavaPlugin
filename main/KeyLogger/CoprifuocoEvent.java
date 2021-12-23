package it.toninosas.main.KeyLogger;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.LoginAndRegisterManager.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CoprifuocoEvent implements Listener {
    @EventHandler
    public void OnJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        //vedi il file dove sta salvato il valore
        if(GestioneConfigs.pluginConfiguration.getBoolean("coprifuoco")) {
            CoprifuocoManager.passCoprifuoco(p);
        }else{
            if (GestioneConfigs.playerDataConfiguration.get(p.getName() + ".password") == null){
                Manager.Register(p);
            }else{
                Manager.Login(p);
            }
        }
    }
    @EventHandler
    //disattivo tutti i comandi
    public void PlayerTryDoCommand(PlayerCommandPreprocessEvent e){
        if (CoprifuocoManager.keyHashmap.containsKey(e.getPlayer().getUniqueId())){
            if (!e.getMessage().startsWith("/keyinsert")){
                e.setCancelled(true);
                e.getPlayer().sendMessage("Inserisci prima la key.");
            }
        }
    }

    @EventHandler
    //svuoto l hashmap del tempo cosi non si buggano i secondi
    public void onQuit(PlayerQuitEvent e){
        CoprifuocoManager.clearPlayer(e.getPlayer());
    }


}
