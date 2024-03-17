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
    private static float pvVoltage;

    public ServerDataS2CPacket(float cpuTemp, float power, float pvVoltage) {
        this.cpuTemp = cpuTemp;
        this.power = power;
        this.pvVoltage = pvVoltage;
    }

    public ServerDataS2CPacket(FriendlyByteBuf buf) {
        cpuTemp = buf.readFloat();
        power = buf.readFloat();
        pvVoltage = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(cpuTemp);
        buf.writeFloat(power);
        buf.writeFloat(pvVoltage);
    }

    public static boolean handle(ServerDataS2CPacket packet, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork( () ->{
                // On client side
                SolarServerData.setCpuTemp(cpuTemp);
                SolarServerData.setPower(power);
                SolarServerData.setPvVoltage(pvVoltage);
        });
        return true;
    }
}
