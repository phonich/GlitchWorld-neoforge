package net.phonich.glitchworld.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.block.ModBlocks;
import net.phonich.glitchworld.block.custom.GlitchLampBlock;

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
        registerLamp(ModBlocks.GLITCH_LAMP.get(), "glitch_lamp");
    }
    private void registerLamp(Block block, String name) {
        var onTexture = ResourceLocation.fromNamespaceAndPath(Glitchworld.MODID, "block/" + name + "_on");
        var offTexture = ResourceLocation.fromNamespaceAndPath(Glitchworld.MODID, "block/" + name + "_off");

        getVariantBuilder(block).forAllStates(state -> {
            boolean clicked = state.getValue(GlitchLampBlock.CLICKED);

            var model = models().cubeAll(
                    name + (clicked ? "_on" : "_off"),
                    clicked ? onTexture : offTexture
            );

            return new ConfiguredModel[]{ new ConfiguredModel(model) };
        });

        simpleBlockItem(block, models().cubeAll(name + "_on", onTexture));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
