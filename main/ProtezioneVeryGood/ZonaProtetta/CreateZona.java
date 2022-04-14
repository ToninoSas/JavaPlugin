package it.toninosas.main.ProtezioneVeryGood.ZonaProtetta;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CreateZona {

    public HashMap<UUID, Location> firstPoint;
    public HashMap<UUID, Location> secondPoint ;

    public HashMap<UUID, Integer> time;

    public String nomeZona;
    public Player player;

    CreateZona(HashMap<UUID, Integer> t){
        time = t;
    }

    public void selectPoint(Player p){
        //il giocatore puo fare questo comando?
        if(time.containsKey(p.getUniqueId())) {
            Location PointLocation = p.getLocation();

            ZonaProtettaManager.Point p1 = new ZonaProtettaManager.Point(),
                    p2 = new ZonaProtettaManager.Point();

            if (firstPoint.isEmpty()) { //primo punto nel primo hashmap
                firstPoint.put(p.getUniqueId(), PointLocation);

//                p1 = new ZonaProtettaManager.Point();
                p1.x = firstPoint.get(p.getUniqueId()).getBlockX();
                p1.z = firstPoint.get(p.getUniqueId()).getBlockZ();

                p.sendMessage("Primo punto registrato.");
                p.sendMessage("Coordinate: (X): " + p1.x + " | (Z): " + p1.z);
                p.sendMessage("Ti manca un punto!");
                return;

            } else if (secondPoint.isEmpty()) { //secondo punto nel secondo hashmap
                secondPoint.put(p.getUniqueId(), PointLocation);

//                p2 = new ZonaProtettaManager.Point();
                p2.x = secondPoint.get(p.getUniqueId()).getBlockX();
                p2.z = secondPoint.get(p.getUniqueId()).getBlockZ();

                p.sendMessage("Secondo punto registrato.");
                p.sendMessage("Coordinate: (X): " + p2.x + " | (Z): " + p2.z);

            } else {
                p.sendMessage("Non devi registrare nessun altro punto!");
                return;
            }

            p1.x = firstPoint.get(p.getUniqueId()).getBlockX();
            p1.z = firstPoint.get(p.getUniqueId()).getBlockZ();

            //devo controllare se contiene claims
            if(ZonaProtettaManager.containsClaim(p1, p2, p)){
                p.sendMessage("La zona è gia stata reclamata!");
                pulezz();
                return;
            }


            //devo creare un 3 punto
            //devo gestire il limite dei blocchi
            //devo calcolare l'area

            ZonaProtettaManager manager = new ZonaProtettaManager(p, nomeZona, firstPoint, secondPoint);
            manager.saveZone();

            p.sendMessage("Zona <" + nomeZona + "> salvata!");

            pulezz();
        }else{
            p.sendMessage("Non hai nessun punto da selezionare!");
        }
    }

    private void pulezz(){
        firstPoint.clear();
        secondPoint.clear();
        time.clear();
    }



}
