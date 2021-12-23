package it.toninosas.main.Protezione;

import it.toninosas.main.GestioneConfigs;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TestManager {

    static class Point{
        public int x, z;
    }

    public static Point getPoint(Block b){
        Point point = new Point();
        point.x = b.getLocation().getBlockX();
        point.z = b.getLocation().getBlockZ();

        return point;
    }

    //verifica se quel blocco è claimato o meno
    public static String isClaimed(Block block, Player player){

        Point p = getPoint(block); //prendo il punto del blocco

        Point a = new Point(), b = new Point();
        int cont;

        for(String playerName : GestioneConfigs.protezioneConfiguration.getKeys(false)) {
            cont = 1;

            while (cont<=GestioneConfigs.protezioneConfiguration.getInt(playerName + ".index")){

                String string = String.format(playerName +".%d", cont);
                cont++;

                //prendo i punti delle varie zone protette
                a.x = GestioneConfigs.protezioneConfiguration.getInt(string + ".p1.x");
                a.z = GestioneConfigs.protezioneConfiguration.getInt(string + ".p1.z");

                b.x = GestioneConfigs.protezioneConfiguration.getInt(string + ".p2.x");
                b.z = GestioneConfigs.protezioneConfiguration.getInt(string + ".p2.z");

                //controllo se il punto del blocco appartiene alla zona protetta AB
                if (appartiene(p, a, b) && !playerName.equals(player.getName())){
//                    player.sendMessage("§cQuesta zona appartiene a §2" + playerName);
                    return playerName;
                }
            }

        }

        return null;
    }

    //se uno prova a claimare una zona che ha un altra zona all'interno
    public static boolean containsClaim(Point newPoint1, Point newPoint2, Player player){
        //i newpoints sono i nuovi punti che un player prova a claimare

        Point oldPoint1 = new Point(), oldPoint2 = new Point();
        int cont;

        for(String playerName : GestioneConfigs.protezioneConfiguration.getKeys(false)) {
            cont = 1;

            while (cont <= GestioneConfigs.protezioneConfiguration.getInt(playerName + ".index")){

                String string = String.format(playerName +".%d", cont);
                cont++;

                //prendo i punti salvati nel file
                oldPoint1.x = GestioneConfigs.protezioneConfiguration.getInt(string + ".p1.x");
                oldPoint1.z = GestioneConfigs.protezioneConfiguration.getInt(string + ".p1.z");

                oldPoint2.x = GestioneConfigs.protezioneConfiguration.getInt(string + ".p2.x");
                oldPoint2.z = GestioneConfigs.protezioneConfiguration.getInt(string + ".p2.z");

                if ((appartiene(oldPoint1, newPoint1, newPoint2))&& !playerName.equals(player.getName())){
                    //per controllare se il proprietario del claim è lui

                    //a appartiene
                    return true;
                }

                if ((appartiene(oldPoint2, newPoint1, newPoint2)) && !playerName.equals(player.getName())){
                    //b appartiene
                    return true;
                }

                if (checkLine(newPoint1, newPoint2, oldPoint1, oldPoint2)){
                    //controllo della linea singola
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean appartiene(Point p, Point a, Point b) {
        int Xmax, Xmin, Zmax, Zmin;

        Xmax = Math.max((a.x), (b.x));
        Xmin = Math.min((a.x), (b.x));

        Zmax = Math.max((a.z), (b.z));
        Zmin = Math.min((a.z), (b.z));

        //controllo per verificare che il punto sia esterno
        if (!(p.x < Xmin || p.x > Xmax || p.z < Zmin || p.z > Zmax)) {
            return true; //appartiene a una zona salvata nel file
        } else {
            return false; //zona libera
        }
    }

    //questa serve nel caso il player x claimi una singola linea di blocchi attraverso il claim di un altro tizio
    public static boolean checkLine(Point p1, Point p2, Point a, Point b){

        int Xmax, Xmin, Zmax, Zmin;

        Xmax = Math.max((a.x), (b.x));
        Xmin = Math.min((a.x), (b.x));

        Zmax = Math.max((a.z), (b.z));
        Zmin = Math.min((a.z), (b.z));

        if (p1.x == p2.x){
            if ((p1.z > Zmax && p2.z < Zmin) || (p2.z > Zmax && p1.z < Zmin)){
                if (p1.x < Xmax && p1.x > Xmin){
                    return true;
                }
            }
        }

        if (p1.z == p2.z){
            if ((p1.x > Xmax && p2.x < Xmin) || (p2.x > Xmax && p1.x < Xmin)){
                if (p1.z < Zmax && p1.z > Zmin){
                    return true;
                }
            }
        }
        return false;
    }

}
