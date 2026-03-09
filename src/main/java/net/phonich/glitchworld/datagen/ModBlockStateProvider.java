package net.phonich.glitchworld.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Glitchworld.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.GLITCH_BLOCK);
        blockWithItem(ModBlocks.GLITCH_CHAOS_BLOCK);
        blockWithItem(ModBlocks.GLITCH_COAL_BLOCK);
        blockWithItem(ModBlocks.GLITCH_CONVERTER);
        blockWithItem(ModBlocks.GLITCH_ORE);
        blockWithItem(ModBlocks.GLITCH_STONE);
        blockWithItem(ModBlocks.GLITCH_PORTAL_FRAME);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
