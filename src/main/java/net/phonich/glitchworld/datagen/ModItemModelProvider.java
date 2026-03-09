package net.phonich.glitchworld.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Glitchworld.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.GLITCH_COAL.get());
        basicItem(ModItems.GLITCH_APPLE.get());
        basicItem(ModItems.GLITCH_DUST.get());
        basicItem(ModItems.GLITCH_IGNITER.get());
        basicItem(ModItems.GLITCH_INGOT.get());
        basicItem(ModItems.GLITCH_POUCH.get());
        basicItem(ModItems.GLITCH_UPGRADER.get());
        basicItem(ModItems.HAMMER.get());
        basicItem(ModItems.RAW_GLITCH.get());
    }
}
