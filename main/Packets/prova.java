package it.toninosas.main.Packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;

public class prova {

    public void f(){
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        //ascoltare da server a client
        protocolManager.addPacketListener(
            new PacketAdapter((Plugin) this, ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                @Override
                public void onPacketSending(PacketEvent event) {
                    if(event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT){
                        event.setCancelled(true);
                    }
                }
            }
        );


    }

    //
//
//    public void createArmorStand2(Player player) {
//
//        player.sendMessage("Sto eseguendo..");
//
//        WrapperPlayServerSpawnEntity packet = new WrapperPlayServerSpawnEntity();
//        final int ID = (int)(Math.random() * Integer.MAX_VALUE);
//
//        packet.setEntityID(ID);
//        packet.setType(78); //armor stand
//        packet.setX(player.getLocation().getX());
//        packet.setY(player.getLocation().getY());
//        packet.setX(player.getLocation().getZ());
//        packet.sendPacket(player);
//
//        WrapperPlayServerEntityMetadata packet2 = new WrapperPlayServerEntityMetadata();
//        packet2.setEntityID(ID);
//
//        WrappedDataWatcher dataWatcher = new WrappedDataWatcher(packet2.getMetadata());
//
////        dataWatcher.setObject(0, (byte)0x20);
////        dataWatcher.setObject(2, "sas");
////        dataWatcher.setObject(3, true);
////        packet2.setMetadata(dataWatcher.getWatchableObjects());
////        packet2.sendPacket(player);
//
//        WrappedDataWatcher.WrappedDataWatcherObject object =
//                new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class));
//        WrappedDataWatcher.WrappedDataWatcherObject object2 =
//                new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.get(String.class));
//        WrappedDataWatcher.WrappedDataWatcherObject object3 =
//                new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class));
//
//        dataWatcher.setObject(object, (byte)0x20);
//        dataWatcher.setObject(object2, "sas");
//        dataWatcher.setObject(object3, true);
//
//        player.sendMessage("sent packet");
//    }
//
//}






//    public void Chat() {
//        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
//
//        protocolManager.addPacketListener(new PacketAdapter(Main.plugin,
//                ListenerPriority.NORMAL,
//                PacketType.Play.Client.CHAT) {
//            @Override
//            public void onPacketReceiving(PacketEvent event) {
//                if (event.getPacketType() == PacketType.Play.Client.CHAT) {
//                    PacketContainer packet = event.getPacket();
//                    String message = packet.getStrings().read(0);
//
//                    if (message.contains("shit")
//                            || message.contains("damn")) {
//                        event.setCancelled(true);
//                        event.getPlayer().sendMessage("Bad manners!");
//                    }
//                }
//            }
//        });
//
//    }

//    public void explosion(Player player){
//        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
//        PacketContainer fakeExplosion = protocolManager.createPacket(PacketType.Play.Server.EXPLOSION);
//
//        fakeExplosion.getDoubles() //insert coordinates in the list of packet's data
//                .write(0, player.getLocation().getX())
//                .write(1, player.getLocation().getY())
//                .write(2, player.getLocation().getZ());
//
//        try{
//            protocolManager.sendServerPacket(player, fakeExplosion);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(
//                    "cannot send packet " + fakeExplosion, e
//            );
//        }
//
//        //Remember to add AbstractPacket AND WrapperPlayServerExplosion to your project.
////        WrapperPlayServerExplosion fakeExplosion = new WrapperPlayServerExplosion();
////
////        fakeExplosion.setX(player.getLocation().getX());
////        fakeExplosion.setY(player.getLocation().getY());
////        fakeExplosion.setZ(player.getLocation().getZ());
////        fakeExplosion.setRadius(3);
////
////        fakeExplosion.sendPacket(player);
//    }


}
