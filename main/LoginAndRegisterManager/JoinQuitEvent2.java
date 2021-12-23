package it.toninosas.main.LoginAndRegisterManager;

import it.toninosas.main.Death.DeathEventSavePosition;
import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import it.toninosas.main.Permission.PermissionManager;
import it.toninosas.main.Tp.TpManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class JoinQuitEvent2 implements Listener {

    @EventHandler
    private void OnJoin(PlayerJoinEvent e){

        getIp(e);

        Player p = e.getPlayer();
        e.setJoinMessage("§f[§a+§f] §6" + p.getDisplayName());

//        if(GestioneConfig.playerDataConfiguration.get(p.getName() + ".password") == null) {
//            p.teleport(p.getWorld().getSpawnLocation());
//            Manager.Register(p);
//        }else{
//            Manager.Login(p);
//        }
    }

    @EventHandler
    private void OnQuit(PlayerQuitEvent e){
        Manager.registerHashmap.remove(e.getPlayer().getUniqueId());
        Manager.loginHashmap.remove(e.getPlayer().getUniqueId());
        DeathEventSavePosition.deathLocationHashmap.remove(e.getPlayer().getUniqueId());
        TpManager.tpa.remove(e.getPlayer().getUniqueId());
        TpManager.time.remove(e.getPlayer().getUniqueId());

        e.setQuitMessage("§f[§c-§f] §6" + e.getPlayer().getDisplayName());

        if (PermissionManager.map.containsKey(e.getPlayer().getUniqueId())){
            e.getPlayer().removeAttachment(PermissionManager.attachment);
            PermissionManager.map.remove(e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onTp(PlayerTeleportEvent e){
        if (Manager.registerHashmap.containsKey(e.getPlayer().getUniqueId()) ||
                Manager.loginHashmap.containsKey(e.getPlayer().getUniqueId())){

            Player p = e.getPlayer();
            p.sendMessage(Main.auth);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void BlockCommandsOnAuthentication(PlayerCommandPreprocessEvent e){
        if (Manager.loginHashmap.containsKey(e.getPlayer().getUniqueId())){
            if (!e.getMessage().startsWith("/login")){
                e.setCancelled(true);
                e.getPlayer().sendMessage(Main.auth);
            }
        }

        if (Manager.registerHashmap.containsKey(e.getPlayer().getUniqueId())){
            if (!e.getMessage().startsWith("/register")){
                e.setCancelled(true);
                e.getPlayer().sendMessage(Main.auth);
            }
        }
    }

    private void getIp(PlayerJoinEvent e){
        String ip = e.getPlayer().getAddress().toString().replaceAll("/", "");
        GestioneConfigs.playerDataConfiguration.set(e.getPlayer().getName() + ".ipaddress", ip);

        GestioneConfigs.save();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
    }
}
