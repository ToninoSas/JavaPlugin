package it.toninosas.main.Packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

//utilizzo:
//Hologram hologram = new Hologram(player, location, message);
//hologram.setLine(message)
//hologram.despawn()

public class Hologram {
    private static final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    public static HashMap<PacketContainer, Integer> map = new HashMap<>();

    public static Location defaultLocation;
    public static Player defaultPlayer;

    private final double INTERLINEA = -0.27; //lo spazio tra una riga e l'altra

    public Hologram(Player player, Location loc, String message){ //costruttore
        defaultLocation = loc;
        defaultPlayer = player;

        sendHologram(player, defaultLocation, message);
    }

    public void setLine(String message){

        defaultLocation.add(0, INTERLINEA, 0);//aggiungo lo spazio tra le righe
        sendHologram(defaultPlayer, defaultLocation, message);
    }

    public static void sendHologram(Player player, Location loc, String message){

        Integer entityID = (int) (Math.random() * Integer.MAX_VALUE);

        PacketContainer newHologram = protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);

        map.put(newHologram, entityID);

        newHologram.getIntegers().write(0, entityID); //inserisco l 'id
        newHologram.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);

        newHologram.getDoubles().write(0, loc.getX()); //inserisco le coordinate
        newHologram.getDoubles().write(1, loc.getY());
        newHologram.getDoubles().write(2, loc.getZ());

        newHologram.getUUIDs().write(0, UUID.randomUUID());

        sendPacketToPlayer(player, newHologram); //invio il pacchetto, verrà spawnato un armor stand semplice

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA); //creo il pacchetto per il metadata

        packet.getIntegers().write(0, entityID); //stesso id del pacchetto dello spawn dell'entita

        Optional<?> opt = Optional.of(WrappedChatComponent.fromChatMessage(message)[0].getHandle()); //NON SACC COST CH FE, DOVREBBE METTERE IL NOME

        WrappedDataWatcher metadata = new WrappedDataWatcher(); //creo il metadata effettivo

        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) 0x20); //invis
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true)), opt); //custom name
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), true); //custom name visible
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(5, WrappedDataWatcher.Registry.get(Boolean.class)), true); //no gravity

        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects()); //aggiungo il metadata al pacchetto da inviare

        sendPacketToPlayer(player, packet); //invio il pacchetto, l'armorstand spawnato sarà invisibile e con un nome custom
    }

    public void despawn(){

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getIntLists().write(0, getIdList());

        sendPacketToPlayer(defaultPlayer, packet);

    }

    public static List<Integer> getIdList(){ //mi serve per poi distruggere l'hologram
        ArrayList<Integer> list = new ArrayList<>();

        for (PacketContainer packet : map.keySet()){
            list.add(map.get(packet));
        }
        return list;
    }

    public static void sendPacketToPlayer(Player player, PacketContainer packet){
        //  non lo fare asincrono
        try {
            protocolManager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}








































