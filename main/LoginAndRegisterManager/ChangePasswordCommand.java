package it.toninosas.main.LoginAndRegisterManager;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ChangePasswordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length != 2){
                p.sendMessage("Utilizzo: /changepassword <oldpassword> <newpassword>");
                return true;

            }else{
                ConfigurationSection cs =
                        GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName());
                String oldpassword = args[0], newpassword = args[1];

                assert cs != null;
                if (!oldpassword.equals(cs.get("password"))){
                    p.sendMessage("La oldpassword non corrisponde.");
                    return true;

                }else {
                    cs.set("password", newpassword);
                    GestioneConfigs.save();

                    p.sendMessage("Password cambiata.");
                    return true;
                }
            }
        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
