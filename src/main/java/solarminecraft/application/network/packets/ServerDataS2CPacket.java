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
    private static float pvCurrent;
    private static float pvPower;
    private static float battVoltage;
    private static float battChargeCurrent;
    private static float battChargePower;
    private static float lPower;
    private static float battRemaining;
    private static float battTemp;
    private static float battOverallCurrent;

    public ServerDataS2CPacket( float cpuTemp, float power, 
                                float pvVoltage, float pvCurrent, float pvPower, 
                                float battVoltage, float battChargeCurrent, float battChargePower, 
                                float lPower, 
                                float battRemaining, float battTemp, float battOverallCurrent ) {
        this.cpuTemp = cpuTemp;
        this.power = power;

        this.pvVoltage = pvVoltage;
        this.pvCurrent = pvCurrent;
        this.pvPower = pvPower;
        
        this.battVoltage = battVoltage;
        this.battChargeCurrent = battChargeCurrent;
        this.battChargePower = battChargePower;
        
        this.lPower = lPower;
        
        this.battRemaining = battRemaining;
        this.battTemp = battTemp;
        this.battOverallCurrent = battOverallCurrent;
    }

    public ServerDataS2CPacket(FriendlyByteBuf buf) {
        cpuTemp = buf.readFloat();
        power = buf.readFloat();

        pvVoltage = buf.readFloat();
        pvCurrent = buf.readFloat();
        pvPower = buf.readFloat();

        battVoltage = buf.readFloat();
        battChargeCurrent = buf.readFloat();
        battChargePower = buf.readFloat();

        lPower = buf.readFloat();

        battRemaining = buf.readFloat();
        battTemp = buf.readFloat();
        battOverallCurrent = buf.readFloat();
        
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(cpuTemp);
        buf.writeFloat(power);

        buf.writeFloat(pvVoltage);
        buf.writeFloat(pvCurrent);
        buf.writeFloat(pvPower);

        buf.writeFloat(battVoltage);
        buf.writeFloat(battChargeCurrent);
        buf.writeFloat(battChargePower);

        buf.writeFloat(lPower);

        buf.writeFloat(battRemaining);
        buf.writeFloat(battTemp);
        buf.writeFloat(battOverallCurrent);

    }

    public static boolean handle(ServerDataS2CPacket packet, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork( () ->{
                // On client side
                SolarServerData.setCpuTemp(cpuTemp);
                SolarServerData.setPower(power);

                SolarServerData.setPvVoltage(pvVoltage);
                SolarServerData.setPvCurrent(pvCurrent);
                SolarServerData.setPvPower(pvPower);

                SolarServerData.setBattVoltage(battVoltage);
                SolarServerData.setBattChargeCurrent(battChargeCurrent);
                SolarServerData.setBattChargePower(battChargePower);

                SolarServerData.setlPower(lPower);

                SolarServerData.setBattRemaining(battRemaining);
                SolarServerData.setBattTemp(battTemp);
                SolarServerData.setBattOverallCurrent(battOverallCurrent);

        });
        return true;
    }
}
