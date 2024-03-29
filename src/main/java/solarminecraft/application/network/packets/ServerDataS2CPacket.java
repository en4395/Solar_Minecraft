package solarminecraft.application.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import solarminecraft.domain.SolarServerData;

/* Server data packets will be send from the server to clients every second.
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

    public static boolean handle(ServerDataS2CPacket packet, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork( () ->{
                // On client side
                SolarServerData.setCpuTemp(cpuTemp);
                SolarServerData.setPower(power);
        });
        return true;
    }
}
