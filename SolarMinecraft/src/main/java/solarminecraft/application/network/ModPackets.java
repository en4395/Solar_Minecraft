package solarminecraft.application.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;
import solarminecraft.SolarMinecraft;
import solarminecraft.application.network.packets.ServerDataS2CPacket;

public class ModPackets {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id(){ return packetId++;}

    public static void register(){
        INSTANCE = ChannelBuilder
                .named(new ResourceLocation(SolarMinecraft.MODID, SolarMinecraft.MODID))
                .networkProtocolVersion(1)
                .optionalServer()
                .clientAcceptedVersions(Channel.VersionTest.exact(1))
                .simpleChannel();

        INSTANCE.messageBuilder(ServerDataS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ServerDataS2CPacket::new)
                .encoder(ServerDataS2CPacket::toBytes)
                .consumerMainThread(ServerDataS2CPacket::handle)
                .add();
    }

    // Send packet to player
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        //INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
        INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
    }

    // Send packet to all clients on server
    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(message, PacketDistributor.ALL.noArg());
    }
}