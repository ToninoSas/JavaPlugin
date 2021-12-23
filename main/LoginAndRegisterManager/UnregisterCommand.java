package it.toninosas.main.LoginAndRegisterManager;

import it.toninosas.main.GestioneConfigs;
import it.toninosas.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class UnregisterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length > 0){
                p.sendMessage("Utilizzo: /unregister");
                return true;

            }else{
                ConfigurationSection cs =
                        GestioneConfigs.playerDataConfiguration.getConfigurationSection(p.getName());

                assert cs != null;
                cs.set("password", null);
                GestioneConfigs.save();

                p.sendMessage("Password eliminata.");
                return true;

            }
        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
