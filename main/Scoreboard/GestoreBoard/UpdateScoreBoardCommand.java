package it.toninosas.main.Scoreboard.GestoreBoard;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UpdateScoreBoardCommand implements CommandExecutor {
    //ricorda di registrarlo nel main e nel .yml /updatescoreboard

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        ScoreBoardManager.updateScoreBoard();
        return false;
    }
}
