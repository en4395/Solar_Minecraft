package solarminecraft.application.client;

import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
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
	public static boolean showAllSolarStats = false;

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

			if (showAllSolarStats) {
				SolarStats(event);
			} else {
				RenderUtils.drawStringBottomLeft(event.getGuiGraphics(), solarStats, solarStatsColor, 1); // lowest line is 1.
				RenderUtils.drawStringTopLeft(event.getGuiGraphics(), powerString, powerConsumptionColor, 0);
				RenderUtils.drawStringCenter(event.getGuiGraphics(), timeRemainingString, 0xFFFFFF, 0);
			}

		}
	}

	void SolarStats(RenderGuiOverlayEvent.Post event) {

		float CPUTemp = ClientSetup.serverData.getCpuTemp();
		float CPUPower = ClientSetup.serverData.getPower(); // CPU power consumption
		float powerConsumption = ClientSetup.serverData.getlPower();

		float solarVolts = ClientSetup.serverData.getPvVoltage();
		float solarCurrent = ClientSetup.serverData.getPvCurrent();
		float solarPower = ClientSetup.serverData.getPvPower();

		float batteryVoltage = ClientSetup.serverData.getBattVoltage();
		float batteryPercentage = ClientSetup.serverData.getBattRemaining();
		float batteryChargeCurrent = ClientSetup.serverData.getBattChargeCurrent();

		String cpuPowerString = "CPU State: " + CPUPower  + "Watts | " + CPUTemp + "ºC";
		String systemPowerString = "System Power Consumption: " + powerConsumption  + " Watts";
		String solarPowerString = "Solar Power Generation: " + solarVolts + "V | " + solarCurrent + "A | " + solarPower + "W";
		String batteryPowerString = "Battery: " + batteryVoltage + "V | " + batteryPercentage + "%";

		String chargingState = batteryChargeCurrent > 0 ? "Charging" : "Discharging";
		int chargingStateColor = batteryChargeCurrent > 0 ? 0xFFFFFF : 0xD6520B;

		String powerState = powerConsumption >= solarPower ? "draining. Consumption is higher than solar regeneration" : "Storing Power";
		int powerStateColor = powerConsumption >= solarPower ? 0xD6520B : 0xFFFFFF;

		RenderUtils.drawStringTopLeft(event.getGuiGraphics(), cpuPowerString, 0xFFFFFF, 1);
		RenderUtils.drawStringTopLeft(event.getGuiGraphics(), systemPowerString, 0xFFFFFF, 3);
		RenderUtils.drawStringTopLeft(event.getGuiGraphics(), solarPowerString, 0xFFFFFF, 5);
		RenderUtils.drawStringTopLeft(event.getGuiGraphics(), batteryPowerString, 0xFFFFFF, 7);

		RenderUtils.drawStringTopLeft(event.getGuiGraphics(), "Battery is " + chargingState , chargingStateColor, 9);
		RenderUtils.drawStringTopLeft(event.getGuiGraphics(), "Overall System is " + powerState , powerStateColor, 11);
	}

}