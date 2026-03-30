package net.phonich.glitchworld.Item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Glitchworld.MODID);

    public static final Supplier<CreativeModeTab> GLITCH_TAB =
            CREATIVE_MODE_TABS.register("glitch_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.GLITCH_INGOT.get()))
                            .title(Component.translatable("creativetab.glitchworld_tab"))
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.GLITCH_IGNITER.get());
                                output.accept(ModItems.GLITCH_INGOT.get());
                                output.accept(ModBlocks.GLITCH_BLOCK.get());
                                output.accept(ModBlocks.GLITCH_PORTAL_FRAME.get());
                                output.accept(ModBlocks.GLITCH_ORE.get());
                                output.accept(ModItems.RAW_GLITCH.get());
                                output.accept(ModItems.GLITCH_APPLE.get());
                                output.accept(ModItems.GLITCH_COAL.get());
                                output.accept(ModBlocks.GLITCH_CHAOS_BLOCK.get());
                                output.accept(ModBlocks.GLITCH_CONVERTER.get());
                                output.accept(ModItems.GLITCH_POUCH.get());
                                output.accept(ModItems.GLITCH_UPGRADER.get());
                                output.accept(ModBlocks.GLITCH_COAL_BLOCK.get());
                                output.accept(ModBlocks.GLITCH_STONE.get());
                                output.accept(ModItems.GLITCH_DUST.get());
                                output.accept(ModItems.HAMMER.get());
                                output.accept(ModBlocks.GLITCH_LAMP.get());
                                output.accept(ModItems.GLITCH_AXE.get());
                                output.accept(ModItems.GLITCH_PICKAXE.get());
                                output.accept(ModItems.GLITCH_SHOVEL.get());
                                output.accept(ModItems.GLITCH_SWORD.get());
                                output.accept(ModItems.GLITCH_BOOTS.get());
                                output.accept(ModItems.GLITCH_LEGGINGS.get());
                                output.accept(ModItems.GLITCH_CHESTPLATE.get());
                                output.accept(ModItems.GLITCH_HELMET.get());
                                output.accept(ModItems.GLITCH_CARROT.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}