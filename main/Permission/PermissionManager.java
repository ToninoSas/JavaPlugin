package it.toninosas.main.Permission;

import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class PermissionManager {

    public static HashMap<UUID, PermissionAttachment> map = new HashMap<>();
    public static PermissionAttachment attachment;

    public static boolean CheckPermission(Player p, Permission permissionName){
        return p.hasPermission(permissionName);
    }

    public static void GivePermission(Player p, Permission permission){
        attachment = p.addAttachment(Main.plugin);
        attachment.setPermission(permission, true);

        map.put(p.getUniqueId(), attachment);

        Bukkit.broadcastMessage("Server >> §b" + p.getName() + " ora ha il permesso: " + permission.getName());
    }

    public static void RemovePermission(Player p, Permission permission){
        if (map.containsKey(p.getUniqueId())){
            attachment.unsetPermission(permission);
            p.removeAttachment(attachment);

            Bukkit.broadcastMessage("Server >> §b" + p.getName() + " non ha piu' il permesso: " + permission.getName());

        }else{
            p.sendMessage("§c" + p.getName() + " non ha il permesso: " + permission.getName());
        }
        clear(p);
    }

    public static void clear(Player p){
        map.remove(p.getUniqueId());
    }

}
