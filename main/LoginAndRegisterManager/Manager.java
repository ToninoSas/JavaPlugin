package it.toninosas.main.LoginAndRegisterManager;

import it.toninosas.main.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class Manager implements Listener {

    public static HashMap<UUID, Integer> loginHashmap = new HashMap<>();
    public static HashMap<UUID, Integer> registerHashmap = new HashMap<>();

    public static boolean logged = false, registered = false;

    public static void Register(Player p){
        registerHashmap.put(p.getUniqueId(), 30);
        BlockPlayer(p);

        new BukkitRunnable() {
            @Override
            public void run() {
            if (registerHashmap.containsKey(p.getUniqueId())){
                if (registerHashmap.get(p.getUniqueId()) == 0){
                    p.kickPlayer("Il player non si è registrato.");
                    clearRegister(p);
                    this.cancel();

                }else{
                    registerHashmap.put(p.getUniqueId(), registerHashmap.get(p.getUniqueId()) - 1);
                    p.sendTitle("§c Devi registrarti",
                            "§7 ti mancano §9" + registerHashmap.get(p.getUniqueId()) + "§7 secondi",
                            0, 40, 0);
                }
            }else {
                clearRegister(p);
                this.cancel();
            }
            }
        }.runTaskTimer(Main.plugin, 0, 20L);
    }

    public static void Login(Player p){
        BlockPlayer(p);
        loginHashmap.put(p.getUniqueId(), 30);

        new BukkitRunnable() {
            @Override
            public void run() {
            if (loginHashmap.containsKey(p.getUniqueId())){
                if (loginHashmap.get(p.getUniqueId()) == 0){
                    p.kickPlayer("Il tempo per effettuare il login è scaduto");
                    clearLogin(p);
                    this.cancel();

                }else{
                    loginHashmap.put(p.getUniqueId(), loginHashmap.get(p.getUniqueId()) - 1);
                    p.sendTitle("§cDevi loggarti",
                            "§7ti mancano §9" + loginHashmap.get(p.getUniqueId()) + "§7 secondi",
                            0, 30, 0);
                }
            }else{
                clearLogin(p);
                this.cancel();
            }
            }
        }.runTaskTimer(Main.plugin, 0, 20L);
    }

    public static void BlockPlayer(Player p){
        p.setGameMode(GameMode.SPECTATOR);
        p.setFlying(true);
        p.setFlySpeed(0);

    }

    public static void FreePlayer(Player p){
        p.setGameMode(GameMode.SURVIVAL);
        p.setFlying(false);
        p.setFlySpeed(0.1F);
    }

    public static void clearLogin(Player p){
        loginHashmap.remove(p.getUniqueId());
    }

    public static void clearRegister(Player p){
        registerHashmap.remove(p.getUniqueId());
    }


}
