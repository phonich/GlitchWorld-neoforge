package net.phonich.glitchworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Glitchworld.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GLITCH_ORE.get())
                .add(ModBlocks.GLITCH_BLOCK.get())
                .add(ModBlocks.GLITCH_CONVERTER.get())
                .add(ModBlocks.GLITCH_COAL_BLOCK.get());
    tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.GLITCH_ORE.get())
            .add(ModBlocks.GLITCH_BLOCK.get())
            .add(ModBlocks.GLITCH_CONVERTER.get())
            .add(ModBlocks.GLITCH_COAL_BLOCK.get());
    }
}
