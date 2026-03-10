package net.phonich.glitchworld.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.GLITCH_BLOCK.get());
        dropSelf(ModBlocks.GLITCH_CONVERTER.get());
        dropSelf(ModBlocks.GLITCH_COAL_BLOCK.get());
        dropSelf(ModBlocks.GLITCH_STONE.get());
        add(ModBlocks.GLITCH_ORE.get(),
                block -> createOreDrop(ModBlocks.GLITCH_ORE.get(), ModItems.RAW_GLITCH.get()));
        dropSelf(ModBlocks.GLITCH_LAMP.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
