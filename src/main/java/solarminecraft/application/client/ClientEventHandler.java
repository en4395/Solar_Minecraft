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

				String tempString = "CPU temperature: " + ClientSetup.serverData.getCpuTemp() + "ºC"; 
				String powerString = "Power Draw: " + ClientSetup.serverData.getPower() + "W"; 
				
				String pvVoltageString = "Solar Voltage: " + ClientSetup.serverData.getPvVoltage() + "V";
				String pvCurrentString = "Solar Current: " + ClientSetup.serverData.getPvCurrent() + "A";
				String pvPowerString = "Solar Power: " + ClientSetup.serverData.getPvPower() + "W";
				
				String battVoltString = "Battery Voltage: " + ClientSetup.serverData.getBattVoltage() + "V";
				String battChrgCurrString = "Battery Charge Current: " + ClientSetup.serverData.getBattChargeCurrent() + "A";
				String battChrgPwrString = "Battery Charge Power: " + ClientSetup.serverData.getBattChargePower() + "W";
				
				String lPowerString = "L Power: " + ClientSetup.serverData.getlPower();
				
				String battRemainingString = "Remaining Battery: " + ClientSetup.serverData.getBattRemaining() + "%";
				String battTempString = "Battery Temperature: " + ClientSetup.serverData.getBattTemp() + "ºC";
				String battOverallCurrentString = "Overall Battery Current: " + ClientSetup.serverData.getBattOverallCurrent() + "A";

				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), tempString, 5, 5, 0xFFFFFF, 0);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), powerString, 5, 5, 0xFFFFFF, 1);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), pvVoltageString, 5, 5, 0xFFFFFF, 2);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), pvCurrentString, 5, 5, 0xFFFFFF, 3);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), pvPowerString, 5, 5, 0xFFFFFF, 4);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battVoltString, 5, 5, 0xFFFFFF, 5);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battChrgCurrString, 5, 5, 0xFFFFFF, 6);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battChrgPwrString, 5, 5, 0xFFFFFF, 7);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), lPowerString, 5, 5, 0xFFFFFF, 8);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battRemainingString, 5, 5, 0xFFFFFF, 9);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battTempString, 5, 5, 0xFFFFFF, 10);
				RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), battOverallCurrentString, 5, 5, 0xFFFFFF, 11);

				// RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), ClientSetup.serverData.getCpuTemp() + "ºC", 5, 5, 0xAAAAAA, 1);
				// RenderUtils.drawConfiguredStringOnHUD(event.getGuiGraphics(), ClientSetup.serverData.getPower() + "W", 5, 5, 0xAAAAAA, 4);
			}
	}
}