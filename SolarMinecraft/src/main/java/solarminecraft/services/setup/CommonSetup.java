package solarminecraft.services.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import solarminecraft.application.network.ModPackets;

public class CommonSetup {
    public static void init(FMLCommonSetupEvent event) {
        ModPackets.register();
    }
}
