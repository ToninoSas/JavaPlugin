package it.toninosas.main.LoginAndRegisterManager;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import it.toninosas.main.Scoreboard.GestoreBoard.ScoreBoardManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class LoginCommand implements CommandExecutor {
//    Main plugin;
//    Manager manager;
//
//    public LoginCommand(Main main, Manager manager1){
//        plugin = main;
//        manager = manager1;
//    }
//
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length != 1){
                //p.sendMessage("§7+--------------------------------+");
                p.sendMessage("§b>> §aUtilizzo: /login <password>");
                //p.sendMessage("§7+--------------------------------+");
                return true;

            }else {
                ConfigurationSection cs =
                        GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName());

                if (Manager.loginHashmap.containsKey(p.getUniqueId())){
                    assert cs != null;
                    if (Objects.equals(cs.get("password"), args[0])){
                        Manager.logged = true;

                        Manager.clearLogin(p);
                        Manager.FreePlayer(p);

                        p.sendTitle("§bBuon divertimento!",
                                    "§3 " + p.getDisplayName(),
                                    5,
                                    10,
                                    5);

                        p.sendMessage("§7+=============================+");
                        p.sendMessage("§3Ti sei Loggato con successo!");
                        p.sendMessage("§3Buon divertimento §6" + p.getDisplayName());
                        p.sendMessage("§7+=============================+");

                        ScoreBoardManager.setup(p);

                    }else {
                        p.sendMessage("§b>> §cPassword errata");
                    }
                    return true;
                }else{
                    if(cs == null){
                        p.sendMessage("§b>> §cDevi prima registrarti!");
                    }else{
                        p.sendMessage("§b>> §cSei gia loggato!");
                    }
                    return true;
                }
            }
        }else{
            System.out.println(Main.console);
        }
        return false;
    }
}
