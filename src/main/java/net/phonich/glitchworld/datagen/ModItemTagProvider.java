package net.phonich.glitchworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Glitchworld.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.GLITCH_FOOD)
                .add(ModItems.GLITCH_APPLE.get());
        tag(ItemTags.SWORDS) // Добавляем в соответствующие теги наши инструменты (чтобы можно было всю эту историю зачаровывать)
                .add(ModItems.GLITCH_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.GLITCH_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.GLITCH_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(ModItems.GLITCH_AXE.get());

        tag(ModTags.Items.GLITCH_TOOLS)
                .add(ModItems.GLITCH_AXE.get())
                .add(ModItems.GLITCH_SWORD.get())
                .add(ModItems.GLITCH_SHOVEL.get())
                .add(ModItems.GLITCH_PICKAXE.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.GLITCH_BOOTS.get())
                .add(ModItems.GLITCH_CHESTPLATE.get())
                .add(ModItems.GLITCH_LEGGINGS.get())
                .add(ModItems.GLITCH_HELMET.get());
    }

}
