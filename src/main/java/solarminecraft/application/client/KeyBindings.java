package solarminecraft.application.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import solarminecraft.SolarMinecraft;

public final class KeyBindings {
    public static final KeyBindings INSTANCE = new KeyBindings();

    private int solarStatsKey = InputConstants.KEY_G;

    public final KeyMapping showSolarStats = new KeyMapping(
            "key." + SolarMinecraft.MODID + ".show_solar_stats",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(solarStatsKey, -1),
            KeyMapping.CATEGORY_MISC );

    private KeyBindings() {}

}
