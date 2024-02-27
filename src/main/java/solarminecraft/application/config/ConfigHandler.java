package solarminecraft.application.config;

import java.util.ArrayList;
import java.util.List;

import solarminecraft.application.client.OverlaySide;

import net.minecraftforge.common.ForgeConfigSpec;

// All the config code is from Matt Czyr's Explorer's Compass. Thanks Matt! :)
public class ConfigHandler {

	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

	public static final Client CLIENT = new Client(CLIENT_BUILDER);

	public static final ForgeConfigSpec CLIENT_SPEC = CLIENT_BUILDER.build();

	public static class Client {
		public final ForgeConfigSpec.BooleanValue displayWithChatOpen;
		public final ForgeConfigSpec.EnumValue<OverlaySide> overlaySide;
		public final ForgeConfigSpec.IntValue overlayLineOffset;

		Client(ForgeConfigSpec.Builder builder) {
			String desc;
			builder.push("Client");

			desc = "Displays Solar Server information on the HUD even while chat is open.";
			displayWithChatOpen = builder.comment(desc).define("displayWithChatOpen", true);

			desc = "The line offset for information rendered on the HUD.";
			overlayLineOffset = builder.comment(desc).defineInRange("overlayLineOffset", 1, 0, 50);

			desc = "The side for information rendered on the HUD. Ex: LEFT, RIGHT";
			overlaySide = builder.comment(desc).defineEnum("overlaySide", OverlaySide.LEFT);

			builder.pop();
		}
	}

}
