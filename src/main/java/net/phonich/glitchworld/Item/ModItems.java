package net.phonich.glitchworld.Item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.ArmorTrim;
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

    public static final DeferredItem<Item> DEV_TOOl = ITEMS.register("dev_tool",
            () -> new DevToolItem(new Item.Properties()));

    public static final DeferredItem<SwordItem> GLITCH_SWORD = ITEMS.register("glitch_sword",
            () -> new SwordItem(ModToolTiers.GLITCH, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.GLITCH, 10, -2f))));
    public static final DeferredItem<PickaxeItem> GLITCH_PICKAXE = ITEMS.register("glitch_pickaxe",
            () -> new PickaxeItem(ModToolTiers.GLITCH, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.GLITCH, 6, -2f))));
    public static final DeferredItem<ShovelItem> GLITCH_SHOVEL = ITEMS.register("glitch_shovel",
            () -> new ShovelItem(ModToolTiers.GLITCH, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.GLITCH, 6, -2f))));
    public static final DeferredItem<AxeItem> GLITCH_AXE = ITEMS.register("glitch_axe",
            () -> new AxeItem(ModToolTiers.GLITCH, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.GLITCH, 11, -3f))));

    public static final DeferredItem<ArmorItem> GLITCH_HELMET = ITEMS.register("glitch_helmet",
            () -> new ArmorItem(ModArmorMaterials.GLITCH_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(42))));
    public static final DeferredItem<ArmorItem> GLITCH_CHESTPLATE = ITEMS.register("glitch_chestplate",
            () -> new ArmorItem(ModArmorMaterials.GLITCH_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(42))));
    public static final DeferredItem<ArmorItem> GLITCH_LEGGINGS = ITEMS.register("glitch_leggings",
            () -> new ArmorItem(ModArmorMaterials.GLITCH_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(42))));
    public static final DeferredItem<ArmorItem> GLITCH_BOOTS = ITEMS.register("glitch_boots",
            () -> new ArmorItem(ModArmorMaterials.GLITCH_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(42))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus); // говорим неофордж, что пора регать
    }
}
