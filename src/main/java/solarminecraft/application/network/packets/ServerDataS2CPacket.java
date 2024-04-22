package solarminecraft.application.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import solarminecraft.domain.SolarServerData;

import java.util.function.Supplier;

/* Server data packets will be sent from the server to clients every second.
(See thread in ServerSetup.onServerStarting)
 */

public class ServerDataS2CPacket {
    private static float cpuTemp;
    private static float power;

    public ServerDataS2CPacket(float cpuTemp, float power) {
        this.cpuTemp = cpuTemp;
        this.power = power;
    }

    public ServerDataS2CPacket(FriendlyByteBuf buf) {
        cpuTemp = buf.readFloat();
        power = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(cpuTemp);
        buf.writeFloat(power);
    }

    public static boolean handle(ServerDataS2CPacket packet, Supplier<NetworkEvent.ClientCustomPayloadEvent.Context> ctx) {
        ctx.get().enqueueWork( () ->{
                // On client side
                SolarServerData.setCpuTemp(cpuTemp);
                SolarServerData.setPower(power);
        });
        return true;
    }
}
