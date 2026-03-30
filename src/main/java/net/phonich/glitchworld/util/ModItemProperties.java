package net.phonich.glitchworld.util;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.component.ModDataComponents;

public class ModItemProperties {
    public static void addCustomProperties() {
        ItemProperties.register(ModItems.GLITCH_PICKAXE.get(), ResourceLocation.fromNamespaceAndPath(Glitchworld.MODID, "on"),
                (itemStack, clientLevel, livingEntity, i) -> Boolean.TRUE.equals(itemStack.get(ModDataComponents.IS_BIGDICK_ON)) ? 1f : 0f); // если true, то 1
        ItemProperties.register(ModItems.GLITCH_SHOVEL.get(), ResourceLocation.fromNamespaceAndPath(Glitchworld.MODID, "on"),
                (itemStack, clientLevel, livingEntity, i) -> Boolean.TRUE.equals(itemStack.get(ModDataComponents.IS_BIGDICK_ON)) ? 1f: 0f);
    }
}
