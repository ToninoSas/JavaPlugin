package it.toninosas.main.Scoreboard.GestoreBoard;

import it.toninosas.main.Scoreboard.GestoreCoords.CoordsManager;
import it.toninosas.main.Scoreboard.GestoreDeath.DeathManager;
import it.toninosas.main.Scoreboard.GestoreKill.KillManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;

import static it.toninosas.main.Scoreboard.GestoreBoard.ScoreBoardManager.obj;
import static it.toninosas.main.Scoreboard.GestoreBoard.ScoreBoardManager.manager;
import static it.toninosas.main.Scoreboard.GestoreBoard.ScoreBoardManager.board;

public class ScoreBoardSetup {
    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void SetScoreBoard1(Player p){

        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("Player Data1", "dummy",
                "        SASMC       ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore("").setScore(11);

        Score PlayerName = obj.getScore(ChatColor.GRAY + " Nome: " + ChatColor.GOLD + p.getName());
        PlayerName.setScore(10);


        Score PlayerRank = obj.getScore(ChatColor.GRAY + " Rank: " + ChatColor.GOLD + "null");
        PlayerRank.setScore(9);

        Score PlayerTeam = obj.getScore(ChatColor.GRAY + " Team: " + ChatColor.GOLD + "null");
        PlayerTeam.setScore(8);

        obj.getScore(ChatColor.GRAY + " ---------------").setScore(7);

        Score PlayerDeath = obj.getScore(ChatColor.GRAY + " Morti: " + ChatColor.AQUA +
                DeathManager.GetDeaths(p));
        PlayerDeath.setScore(6);

        Score PlayerKills = obj.getScore(ChatColor.GRAY + " Uccisioni: " + ChatColor.AQUA +
                KillManager.GetKills(p));
        PlayerKills.setScore(5);

        obj.getScore(ChatColor.GRAY + " ---------------" + ChatColor.BLACK).setScore(4);

//        Score PlayerCoordinates = obj.getScore(ChatColor.GRAY + "" +
//                ChatColor.AQUA + df.format(CoordsManager.GetLocation(p).getX())+ "x" + ChatColor.GRAY + "/" +
//                ChatColor.AQUA + df.format(CoordsManager.GetLocation(p).getY())+ "y" + ChatColor.GRAY + "/" +
//                ChatColor.AQUA + df.format(CoordsManager.GetLocation(p).getZ()) + "z");
//        PlayerCoordinates.setScore(3);

//        obj.getScore(ChatColor.GRAY + " ---------------" + ChatColor.YELLOW).setScore(2);

        Team onlineCounter = ScoreBoardManager.board.registerNewTeam("onlineCounter");
        onlineCounter.setPrefix(ChatColor.AQUA + "" + Bukkit.getOnlinePlayers().size() +
                ChatColor.GRAY + "/" + ChatColor.AQUA + Bukkit.getMaxPlayers());

        Score onlinePlayers = obj.getScore(ChatColor.GRAY + " Online: " + onlineCounter.getPrefix());
        onlinePlayers.setScore(1);

        obj.getScore("" + ChatColor.YELLOW).setScore(0);


        p.setScoreboard(board);
    }
}
