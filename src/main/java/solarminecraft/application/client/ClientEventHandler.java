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
			float powerConsumption = ClientSetup.serverData.getlPower();

			String powerString = "Burning " + powerConsumption  + " Watts";
			String solarStats = "Solar: " + ClientSetup.serverData.getPvVoltage() + "V | " + ClientSetup.serverData.getPvCurrent() + "A";
			String timeRemainingString = "Time Remaining: " + ClientSetup.serverData.getTimeRemaining() + " Hours (" + ClientSetup.serverData.getBattRemaining() + "%)";

			int solarStatsColor = mc.level.isNight() ? 0xCAE34B : 0xCAE34B; // same for now.

			// red if high, orange if mid, white if low
			int powerConsumptionColor = powerConsumption > 20 ? 0xD6520B : powerConsumption <= 15 ? 0xFFFFFF : 0xCAE34B;


			RenderUtils.drawStringBottomLeft(event.getGuiGraphics(), solarStats,solarStatsColor, 1); // lowest line is 1.
			RenderUtils.drawStringTopLeft(event.getGuiGraphics(), powerString, powerConsumptionColor, 0);
			RenderUtils.drawStringCenter(event.getGuiGraphics(), timeRemainingString,0xFFFFFF, 0);

		}
	}
}