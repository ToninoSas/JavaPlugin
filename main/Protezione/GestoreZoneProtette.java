package it.toninosas.main.Protezione;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

import static it.toninosas.main.Protezione.ProtezioneCommand.firstLocation;
import static it.toninosas.main.Protezione.ProtezioneCommand.secondLocation;

public class GestoreZoneProtette {

    public static HashMap<UUID, Integer> indexMap = new HashMap<>();
    public static HashMap<UUID, Integer> blocksMap = new HashMap<>();

    public static Integer index = 0; //variabile per tenere l indice delle zone
    public static Integer BLOCKSLIMIT = 5000; //constante da caricare nel file come limite
    public static Integer blocks = 0; //variabile per tenere il conto dei blocchi
    public static Integer area = 0; //variabile per l'area

    public static void setup(Player p){

        blocks = getProtectBlocks(p); //prendo i blocchi protetti
        blocksMap.put(p.getUniqueId(), blocks);

        index = getIndex(p);  //prendo l indice delle zone
        indexMap.put(p.getUniqueId(), index);

        GestioneConfigs.protezioneConfiguration.set(p.getName() + ".blocksLimit", BLOCKSLIMIT);
        GestioneConfigs.protezioneConfiguration.set(p.getName() + ".blocksProtect", blocks);
        GestioneConfigs.save();

        saveZona(p);
    }

    public static boolean areaManager(TestManager.Point p1, TestManager.Point p2, TestManager.Point p3, Player p){
        area = Area(p1, p2, p3);

        if (blocksMap.get(p.getUniqueId()) + area > BLOCKSLIMIT){ // se i blocchi protetti + area della nuova zona è maggiore del limite
            return true;
        }else{
            refreshBlocks(p); //aggiorno i blocchi protetti
            return false;
        }
    }
    public static int Area(TestManager.Point p1, TestManager.Point p2, TestManager.Point p3){
        //che vu capesh to
        int P1P3 = (int) Math.sqrt(Math.pow(((p3.x) - p1.x), 2) + Math.pow(((p3.z) - p1.z), 2)) + 1;
        int P2P3 = (int) Math.sqrt(Math.pow(((p2.x) - p3.x), 2) + Math.pow(((p2.z) - p3.z), 2)) + 1;

        area = P1P3 * P2P3;

        return area;
    }

    public static void saveZona(Player p){ //sav
        GestioneConfigs.protezioneConfiguration.set(p.getName() + ".index", indexMap.get(p.getUniqueId())); //salvo l index
        GestioneConfigs.protezioneConfiguration.set(p.getName() + ".blocksProtect", blocksMap.get(p.getUniqueId())); // salvo i blocchi protetti
        GestioneConfigs.save();
    }
    public static void addClaim(Player p){ //aggiorna l indice
        indexMap.put(p.getUniqueId(), indexMap.get(p.getUniqueId()) + 1);
        saveZona(p);
    }
    public static void refreshBlocks(Player p){ //aggiorna i blocchi protetti
        blocksMap.put(p.getUniqueId(), blocksMap.get(p.getUniqueId()) + area);
        saveZona(p);
    }



    public static int getIndex(Player p){ //prende l indice corrente
        index = GestioneConfigs.protezioneConfiguration.getInt(p.getName() + ".index");
        return index;
    }

    public static int getProtectBlocks(Player p){ //prende i blocchi protetti
        return GestioneConfigs.protezioneConfiguration.getInt(p.getName() + ".blocksProtect");
    }

    //carica i punti sul file
    public static void loadZone(Player p){
        addClaim(p); //aggiorna l indice e lo salva sul file

        String string = String.format(p.getName() +".%d", indexMap.get(p.getUniqueId())); //creo la stringa dinamica formattata
        //.%d sta per indicare .variabile, .1, .2, .3, ecc..

        GestioneConfigs.protezioneConfiguration.set(string + ".p1.x", firstLocation.get(p.getUniqueId()).getBlockX());
        GestioneConfigs.protezioneConfiguration.set(string + ".p1.z", firstLocation.get(p.getUniqueId()).getBlockZ());
        GestioneConfigs.protezioneConfiguration.set(string + ".p1.world", firstLocation.get(p.getUniqueId()).getWorld().getName());
        GestioneConfigs.protezioneConfiguration.set(string + ".p2.x", secondLocation.get(p.getUniqueId()).getBlockX());
        GestioneConfigs.protezioneConfiguration.set(string + ".p2.z", secondLocation.get(p.getUniqueId()).getBlockZ());
        GestioneConfigs.protezioneConfiguration.set(string + ".p2.world", secondLocation.get(p.getUniqueId()).getWorld().getName());

        GestioneConfigs.save();
    }

    public static void clear(Player p){
        indexMap.remove(p.getUniqueId());
        firstLocation.remove(p.getUniqueId());
        secondLocation.remove(p.getUniqueId());
    }
}
