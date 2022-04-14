package it.toninosas.main.ProtezioneVeryGood.ZonaProtetta;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

//se non esiste, te la crea
public class ZonaProtetta_modify {

    static String nomeZona;
    static Player player;

    static HashMap<UUID, Location> firstPoint = new HashMap<>();
    static HashMap<UUID, Location> secondPoint = new HashMap<>();
    static HashMap<UUID, Integer> time = new HashMap<>();

    ZonaProtetta_modify(String name, Player player1){
        nomeZona = name;
        player = player1;

        starting();
    }

    private void starting(){

        if(Objects.equals(nomeZona, "")){
            player.sendMessage("Utilizzo: /zonaprotetta modify <nome zona>");
        }else{

            //ora deve selezionare i punti
            time.put(player.getUniqueId(), 60);

            player.sendMessage(nomeZona);
        }
    }

    //TO DO: OTTIMIZARE QUESTO CODICE COPIATO 2 VOLTE

    public static void selectPoint(Player p){
        //il giocatore puo fare questo comando?
        if(time.containsKey(p.getUniqueId())){
            Location PointLocation = p.getLocation();

            if(firstPoint.isEmpty()){ //primo punto nel primo hashmap
                firstPoint.put(p.getUniqueId(), PointLocation);

                ZonaProtettaManager.Point p1 = new ZonaProtettaManager.Point();
                p1.x = firstPoint.get(p.getUniqueId()).getBlockX();
                p1.z = firstPoint.get(p.getUniqueId()).getBlockZ();

                p.sendMessage("Primo punto registrato.");
                p.sendMessage("Coordinate: (X): " + p1.x + " | (Z): " + p1.z);
                return;

            } else if(secondPoint.isEmpty()){ //secondo punto nel secondo hashmap
                secondPoint.put(p.getUniqueId(), PointLocation);

                ZonaProtettaManager.Point p2 = new ZonaProtettaManager.Point();
                p2.x = secondPoint.get(p.getUniqueId()).getBlockX();
                p2.z = secondPoint.get(p.getUniqueId()).getBlockZ();

                p.sendMessage("Secondo punto registrato.");
                p.sendMessage("Coordinate: (X): " + p2.x + " | (Z): " + p2.z);

            }else{
                p.sendMessage("Non devi registrare nessun altro punto!");
                return;
            }


            //devo controllare se contiene claims
            //devo creare un 3 punto
            //devo gestire il limite dei blocchi
            //devo calcolare l'area

            ZonaProtettaManager manager = new ZonaProtettaManager(p, nomeZona, firstPoint, secondPoint);
            manager.saveZone();

            p.sendMessage("Zona <"+ nomeZona +"> salvata!");

            firstPoint.clear();
            secondPoint.clear();
            time.clear();
        }else{
            p.sendMessage("Non hai nessun punto da selezionare!");
        }
    }


}
