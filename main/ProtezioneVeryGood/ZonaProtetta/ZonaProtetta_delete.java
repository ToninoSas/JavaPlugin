package it.toninosas.main.ProtezioneVeryGood.ZonaProtetta;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ZonaProtetta_delete {
    static Player player;
    static String nomeZona;

    static HashMap<UUID, Integer> time = new HashMap<>();

    //costruttore
    ZonaProtetta_delete(String nome, Player p){
        nomeZona = nome;
        player = p;

        starting();
    }

    private void starting(){

        if(Objects.equals(nomeZona, "")){
            player.sendMessage("Utilizzo: /zonaprotetta delete <nome zona>");
        }else {
            //ora deve selezionare i punti
            time.put(player.getUniqueId(), 60);
            removeZona();

            player.sendMessage(nomeZona);
        }
    }

    private void removeZona(){
        if(time.containsKey(player.getUniqueId())) {

            //trovo la sezione del giocatore
            ConfigurationSection cs =
                    Objects.requireNonNull(GestioneConfigs.protezioneConfiguration.getConfigurationSection(player.getName()));

            //verifico che la zona esista
            if(cs.get(nomeZona) == null){
                player.sendMessage("ZonaProtetta non trovata");
            }else{
                //setto la zona a valore nullo, cosi viene eliminata
                cs.set(nomeZona, null);
                GestioneConfigs.save();
                player.sendMessage("Zona eliminata.");
            }

        }else{
            player.sendMessage("Non hai nessuna zona da eliminare!");
        }


    }










}
