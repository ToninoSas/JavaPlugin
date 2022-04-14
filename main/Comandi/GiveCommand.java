package it.toninosas.main.Comandi;

import it.toninosas.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            // /give <nome giocatore> <oggetto> <quantita>

            if (args.length!=3){
                //p.sendMessage("§7+----------------------------------------+");
                p.sendMessage("§b>> §aUtilizzo: /give <nome giocatore> <oggetto> <quantita>");
               // p.sendMessage("§7+----------------------------------------+");
                return true;
            }

            Player t = Bukkit.getPlayer(args[0]);
            if (t==null){
               // p.sendMessage("§7+--------------------------------+");
                p.sendMessage("§b>> §cGiocatore non trovato");
              //  p.sendMessage("§7+--------------------------------+");
                return true;
            }
            int quantity = 0;
            Material material = Material.matchMaterial(args[1]);

            try {
                quantity = Integer.parseInt(args[2]);
            }catch (NumberFormatException e){
           //     p.sendMessage("§7+--------------------------------+");
                p.sendMessage("§b>> §cQuantità " + args[2] + " non valida");
           //     p.sendMessage("§7+--------------------------------+");
                return true;
            }

            if (quantity <= 0 || quantity > 1000){
        //        p.sendMessage("§7+--------------------------------+");;
                p.sendMessage("§b>> §cQuantità " + quantity + " non valida");
        //        p.sendMessage("§7+--------------------------------+");
                return true;
            }

            if(material==null){
        //        p.sendMessage("§7+--------------------------------+");
                p.sendMessage("§b>> §cMateriale " + args[1] + " non esistente.");
        //        p.sendMessage("§7+--------------------------------+");
                return true;
            }else{
                ItemStack itemStack = new ItemStack(material, quantity);
                t.getInventory().addItem(itemStack);
          //      p.sendMessage("§7+--------------------------------+");
                t.sendMessage("§b>> §2Hai ricevuto " + material + " * " + quantity);
        //        p.sendMessage("§7+--------------------------------+");
                return true;
            }
        }else{
            System.out.println(Main.console);
            return true;
        }
    }
}
