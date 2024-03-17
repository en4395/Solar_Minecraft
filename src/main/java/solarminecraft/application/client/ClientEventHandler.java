package solarminecraft.application.client;

import solarminecraft.application.config.ConfigHandler;
import solarminecraft.application.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import solarminecraft.services.setup.ClientSetup;

// All of this HUD rendering code is from Matt Czyr's Explorer's Compass. Thanks Matt! :)
@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {

	private static final Minecraft mc = Minecraft.getInstance();

	private static int num = 0;
	@SubscribeEvent
	public void onRenderTick(RenderGuiOverlayEvent.Post event) {
			if (mc.player != null && mc.level != null && !mc.options.hideGui && (mc.screen == null || (ConfigHandler.CLIENT.displayWithChatOpen.get() && mc.screen instanceof ChatScreen))) {
				final Player player = mc.player;

				String tempString = "CPU: " + ClientSetup.serverData.getCpuTemp() + "ºC";
				String powerString = "Power Draw: " + ClientSetup.serverData.getPower() + "W"; 
				
				String solarStats = "Solar: " + ClientSetup.serverData.getPvVoltage() + "V | " + ClientSetup.serverData.getPvCurrent() + "A | " + ClientSetup.serverData.getPvPower() + "W";
				String battChrgString = "Battery Charging: " + ClientSetup.serverData.getBattChargeCurrent() + "A | " + ClientSetup.serverData.getBattChargePower() + "W";
				String battString = "Battery: " + ClientSetup.serverData.getBattVoltage() + "V | " + ClientSetup.serverData.getBattRemaining() + "%";
				String battOverallCurrentString = "Overall Battery Current: " + ClientSetup.serverData.getBattOverallCurrent() + "A";

				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), tempString, 5, 5, 0xFFFFFF, 0);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), powerString, 5, 5, 0xFFFFFF, 1);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), solarStats, 5, 5, 0xFFFFFF, 2);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battString, 5, 5, 0xFFFFFF, 3);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battChrgString, 5, 5, 0xFFFFFF, 4);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battOverallCurrentString, 5, 5, 0xFFFFFF, 5);
			}
	}
}