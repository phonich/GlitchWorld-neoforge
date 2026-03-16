package net.phonich.glitchworld.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.component.ModDataComponents;

public class MyMethods {
    public static boolean getStateOfGlitchItem(ItemStack stack) {
            return (stack.is(ModTags.Items.GLITCH_TOOLS) && stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false));
    }
}
