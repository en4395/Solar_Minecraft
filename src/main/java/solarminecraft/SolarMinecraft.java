package solarminecraft;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import solarminecraft.application.config.ConfigHandler;
import solarminecraft.application.item.ModItems;
import solarminecraft.application.network.ModPackets;
import solarminecraft.application.network.packets.ServerDataS2CPacket;
import solarminecraft.domain.SolarServerData;
import solarminecraft.services.setup.ClientSetup;
import solarminecraft.services.setup.CommonSetup;
import solarminecraft.services.setup.ServerSetup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Mod(SolarMinecraft.MODID)
public class SolarMinecraft {
	public static final String MODID = "solarminecraft";

	public static final Logger LOGGER = LogManager.getLogger(MODID);


	public SolarMinecraft() {

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModItems.register(modEventBus);

		modEventBus.addListener(CommonSetup::init);
		modEventBus.addListener(this::addCreative);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.COMBAT) {
			event.accept(ModItems.SOLAR_SWORD);
		}
	}
}
