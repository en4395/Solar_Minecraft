package solarminecraft.services.setup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import solarminecraft.SolarMinecraft;
import solarminecraft.application.network.ModPackets;
import solarminecraft.application.network.packets.ServerDataS2CPacket;
import solarminecraft.services.DataQueryProcess;

public class ServerSetup {

    public static MinecraftServer server;

    public static ExecutorService executor = Executors.newSingleThreadExecutor();

    @Mod.EventBusSubscriber(modid = SolarMinecraft.MODID)
    public class ServerEvents {

        /*
            The server starting initiates a thread which calls DataQueryProcess methods
            every second
        */
        @SubscribeEvent
        public static void onServerStarting(ServerStartingEvent event) {

            // I got all of the thread code from joanlittlewood... Thanks Stuart :)
            ServerSetup.server = ServerLifecycleHooks.getCurrentServer();

            Future<?> future = ServerSetup.executor.submit(() -> {
                float currentTemp;
                float currentPower;
                float currentPVVoltage;
                float currentPVCurrent;
                float currentPVPower;
                float currentbattVoltage;
                float currentbattChargeCurrent;
                float currentbattChargePower;
                float currentlPower;
                float currentbattRemaining;
                float currentbattTemp;
                float currentbattOverallCurrent;
                String timeRemaining;

                while (!Thread.currentThread().isInterrupted()) {
                    currentTemp = DataQueryProcess.GetCPUTemp();
                    currentPower = DataQueryProcess.GetSysPower();

                    currentPVVoltage = DataQueryProcess.GetPVVoltage();
                    currentPVCurrent = DataQueryProcess.GetPVCurrent();
                    currentPVPower = DataQueryProcess.GetPVPower();
                    
                    currentbattVoltage = DataQueryProcess.GetBattVoltage();
                    currentbattChargeCurrent = DataQueryProcess.GetBattChargeCurrent();
                    currentbattChargePower = DataQueryProcess.GetBattChargePower();
                    
                    currentlPower = DataQueryProcess.GetLPower();
                    
                    currentbattRemaining = DataQueryProcess.GetBattRemaining();
                    currentbattTemp = DataQueryProcess.GetBattTemp();
                    currentbattOverallCurrent = DataQueryProcess.GetBattOverallCurrent();
                    timeRemaining = DataQueryProcess.GetTimeRemaining();

                    ModPackets.sendToClients(new ServerDataS2CPacket(currentTemp, currentPower, currentPVVoltage, currentPVCurrent, currentPVPower, currentbattVoltage, currentbattChargeCurrent, currentbattChargePower, currentlPower, currentbattRemaining, currentbattTemp, currentbattOverallCurrent, timeRemaining));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Preserve interrupt status
                        System.out.println("Interrupted!");
                        break;
                    }
                }
            });
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if(!event.getLevel().isClientSide()) {
                if(event.getEntity() instanceof ServerPlayer player) {
                    ServerPlayer eventPlayer = (ServerPlayer) event.getEntity();
                    ModPackets.sendToPlayer(new ServerDataS2CPacket(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, ""), player);
                }
            }
        }

        // When world is unloaded, thread is shut down
        @SubscribeEvent
        public void onServerShutdown(LevelEvent.Unload event) {
            ServerSetup.executor.shutdownNow();
        }
    }
}