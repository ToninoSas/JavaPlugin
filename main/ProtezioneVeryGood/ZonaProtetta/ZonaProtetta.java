package it.toninosas.main.ProtezioneVeryGood.ZonaProtetta;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static it.toninosas.main.ProtezioneVeryGood.ZonaProtetta.ZonaProtetta_new.time;

public class ZonaProtetta implements CommandExecutor {

    void empty(Player p){

        String string = p.getName();

        if(GestioneConfigs.protezioneConfiguration.get(string) == null){
            p.sendMessage("Non hai zone protette.");
            p.sendMessage(" Utilizza </zonaprotetta new> per crearne una.");
        }else{
            ConfigurationSection cs = GestioneConfigs.protezioneConfiguration.getConfigurationSection(p.getName());
            String listaZone = "";
            p.sendMessage("Zone Protette disponibili: ");
            assert cs != null;
            for(String zona : cs.getKeys(false)){
                listaZone = listaZone + zona + ", ";
            }
            p.sendMessage(listaZone);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;

        if (args.length == 0){
            empty(p);
        }else if(args.length == 1){
            switch (args[0]){
                case "select":
                    if(!time.isEmpty()) {ZonaProtetta_new.selectPoint(p);}
                    break;
                case "new":
                    new ZonaProtetta_new("", p);
                    break;
                case "delete":
                    new ZonaProtetta_delete("", p);
                    break;
                case "modify":
                    new ZonaProtetta_modify("", p);
                    break;
                default:
                    //helper
                    break;
            }
        }else if(args.length == 2){
            switch (args[0]) {
                case "new":
                    new ZonaProtetta_new(args[1], p);
                    break;
                case "delete":
                    new ZonaProtetta_delete(args[1], p);
                    break;
                case "modify":
                    new ZonaProtetta_modify(args[1], p);
                    break;
                default:
                    //helper
            }
        }else{
            //helper
        }




        return false;
    }
}
