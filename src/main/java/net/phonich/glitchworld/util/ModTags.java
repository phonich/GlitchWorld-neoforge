package net.phonich.glitchworld.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.phonich.glitchworld.Glitchworld;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_GLITCH_TOOL = createTag("needs_glitch_tool");
        public static final TagKey<Block> INCORRECT_FOR_GLITCH_TOOL = createTag("incorrect_for_glitch_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Glitchworld.MODID, name));
        }
    }
    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Glitchworld.MODID, name));
        }
        public static final TagKey<Item> GLITCH_FOOD = createTag("glitch_food");
        public static final TagKey<Item> GLITCH_TOOLS = createTag("glitch_tools");
    }
}
