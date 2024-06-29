package solarminecraft.application.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import solarminecraft.SolarMinecraft;
import solarminecraft.services.setup.ClientSetup;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SolarMinecraft.MODID);
    public static final RegistryObject<Item> SOLAR_SWORD = ITEMS.register("solar_sword",
            () -> new SwordItem(Tiers.NETHERITE, Math.min(4, (int) ClientSetup.serverData.getPvVoltage()), 2, new Item.Properties()));
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
