package net.phonich.glitchworld.Item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.phonich.glitchworld.util.ModTags;

public class ModToolTiers{
    public static final Tier GLITCH = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_GLITCH_TOOL, 2400, 12f, 0f, 20,
            () -> Ingredient.of(ModItems.GLITCH_INGOT));
}
