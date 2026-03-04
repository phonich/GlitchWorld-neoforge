package net.phonich.glitchworld.Item;

import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.custom.GlitchAppleItem;
import net.phonich.glitchworld.Item.custom.GlitchIgniterItem;
import net.phonich.glitchworld.Item.custom.GlitchPouchItem;
import net.phonich.glitchworld.Item.custom.GlitchUpgraderItem;
import net.phonich.glitchworld.block.custom.FuelItem;
import net.minecraft.world.entity.monster.Creeper;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Glitchworld.MODID);

    public static final DeferredItem<Item> RAW_GLITCH = ITEMS.register("raw_glitch",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_APPLE = ITEMS.register("glitch_apple",
            () -> new GlitchAppleItem(new Item.Properties().food(ModFoods.GLITCH_APPLE)));

    public static final DeferredItem<Item> GLITCH_IGNITER = ITEMS.register("glitch_igniter",
            ()-> new FlintAndSteelItem(new Item.Properties().stacksTo(1).durability(42)));

    public static final DeferredItem<Item> GLITCH_COAL = ITEMS.register("glitch_coal",
           () -> new FuelItem(new Item.Properties(), 2000));

    public static final DeferredItem<Item> GLITCH_POUCH = ITEMS.register("glitch_pouch",
            () -> new GlitchPouchItem(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_INGOT = ITEMS.register("glitch_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_UPGRADER = ITEMS.register("glitch_upgrader",
            () -> new GlitchUpgraderItem(new Item.Properties().durability(42)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus); // говорим неофордж, что пора регать
    }
}
