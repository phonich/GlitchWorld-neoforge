package net.phonich.glitchworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }
    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.GLITCH_COAL.getId(), new FurnaceFuel(1200), false)
                .add(ModBlocks.GLITCH_COAL_BLOCK.getId(), new FurnaceFuel(2000), false);
    }
}
