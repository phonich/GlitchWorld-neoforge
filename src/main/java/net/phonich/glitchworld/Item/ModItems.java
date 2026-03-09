package net.phonich.glitchworld.Item;

import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.custom.*;

import net.minecraft.world.entity.monster.Creeper;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Glitchworld.MODID);

    public static final DeferredItem<Item> RAW_GLITCH = ITEMS.register("raw_glitch",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_APPLE = ITEMS.register("glitch_apple",
            () -> new GlitchAppleItem(new Item.Properties().food(ModFoods.GLITCH_APPLE)));

    public static final DeferredItem<Item> GLITCH_IGNITER = ITEMS.register("glitch_igniter",
            ()-> new GlitchIgniterItem(new Item.Properties().stacksTo(1).durability(42)));

    public static final DeferredItem<Item> GLITCH_COAL = ITEMS.register("glitch_coal",
           () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_POUCH = ITEMS.register("glitch_pouch",
            () -> new GlitchPouchItem(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_INGOT = ITEMS.register("glitch_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_DUST = ITEMS.register("glitch_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLITCH_UPGRADER = ITEMS.register("glitch_upgrader",
            () -> new GlitchUpgraderItem(new Item.Properties().durability(42)));

    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer",
            () -> new HammerItem(new Item.Properties().durability(100)));





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus); // говорим неофордж, что пора регать
    }
}
