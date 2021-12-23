package it.toninosas.main.PvP;

import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class pvpCommand implements CommandExecutor {

    //HashMap<UUID, Boolean> hashMap = new HashMap<>();
    public static ArrayList<UUID> map = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            //pvpjoin
            //pvpleft


            map.add(p.getUniqueId());




        }


        return false;
    }
}
