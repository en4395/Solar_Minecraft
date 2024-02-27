package solarminecraft.services.setup;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import solarminecraft.application.client.ClientEventHandler;
import solarminecraft.domain.SolarServerData;

public class ClientSetup {
    public static SolarServerData serverData;
    public static void init(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
