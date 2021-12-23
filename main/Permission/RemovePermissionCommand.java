package it.toninosas.main.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class RemovePermissionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("ProvaPlugin.permission")){
            if (args.length != 2){
                sender.sendMessage("§b>> §aUtilizzo: /removep <nome player> <permission>");
                return true;

            }else{
                Player t = Bukkit.getPlayer(args[0]);
                if (t==null){
                    sender.sendMessage("§b>> §cGiocatore non trovato");
                    return true;

                }else{
                    Permission permission = Bukkit.getPluginManager().getPermission(args[1]);

                    if (permission==null){
                        sender.sendMessage("§b>> §cPermesso" + " non esistente.");
                        return true;

                    }else{
                        if (PermissionManager.CheckPermission(t, permission)){
                            PermissionManager.RemovePermission(t, permission);
                            //PermissionManager.clear(t);
                        } else {
                            sender.sendMessage("§b>> §c" + t.getName() + " non ha il permesso: " + permission.getName());
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
