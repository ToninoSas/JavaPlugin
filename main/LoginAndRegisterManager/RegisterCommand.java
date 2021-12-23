package it.toninosas.main.LoginAndRegisterManager;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import it.toninosas.main.Scoreboard.GestoreBoard.ScoreBoardManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class RegisterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length != 2){
                p.sendMessage("§b>> §aUtilizzo: /register <password> <password>");
                return true;

            } else{
                if (Manager.registerHashmap.containsKey(p.getUniqueId())){
                    if (Objects.equals(args[0], args[1])){
                        Manager.registered = true;

                        GestioneConfigs.playerDataConfiguration.set(p.getName() + ".password", args[0]);
                        GestioneConfigs.save();

                        Manager.FreePlayer(p);
                        Manager.clearRegister(p);

                        p.sendTitle("§bBuon divertimento! §3", p.getDisplayName(), 5, 15, 5);

                        p.sendMessage("§7+===============================+");
                        p.sendMessage("§3Ti sei registrato con successo!");
                        p.sendMessage("§3Buon divertimento! §6" + p.getDisplayName());
                        p.sendMessage("§7+===============================+");

                        ScoreBoardManager.setup(p);

                    }else {
                        p.sendMessage("§b>> §cLe due password non corrispondono");
                    }
                    return true;
                }else{
                    if (GestioneConfigs.playerDataConfiguration.get(p.getName() + ".password") == null){
                        p.sendMessage("§b>> §cDevi registrarti!");
                    }else {
                        p.sendMessage("§b>> §cSei gia registrato!");
                    }
                    return true;
                }
            }
        }else {
            System.out.println(Main.console);
            return true;
        }
    }
}
