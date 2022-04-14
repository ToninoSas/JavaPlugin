package it.toninosas.main.Scoreboard.GestoreBoard;

import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoardManager implements Listener {

    public static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static Scoreboard board;
    public static Objective obj;

    public static void updateScoreBoard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            new BukkitRunnable() {
                @Override
                public void run() {
                    ScoreBoardSetup.SetScoreBoard1(online);
                }
            }.runTaskTimer(Main.plugin, 0, 20L);
        }
    }

    public static void setup(Player p){
        ScoreBoardSetup.SetScoreBoard1(p);
        ScoreBoardManager.updateScoreBoard();
    }

//    @EventHandler
//    public void OnJoin(PlayerJoinEvent e){
//        ScoreBoardSetup.SetScoreBoard1(e.getPlayer());
//        ScoreBoardManager.updateScoreBoard();
//    }

    /*
        Se vuoi che la scoreboard si carichi dopo il login/register, chiama la funzione
        'setup()' dopo l'autenticazione.
        Se invece vuoi che si carichi subito, togli il commento all evento OnJoin()
     */

}
