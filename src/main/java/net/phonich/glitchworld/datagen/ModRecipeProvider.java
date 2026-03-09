package net.phonich.glitchworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.block.ModBlocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> p_323846_) {
        super(p_248933_, p_323846_);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> GLITCH_SMELTABLES = List.of(ModItems.RAW_GLITCH,
                ModBlocks.GLITCH_ORE);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLITCH_UPGRADER.get())
                .pattern(" %@")
                .pattern(" #%")
                .pattern("#  ")
                .define('@', ModItems.RAW_GLITCH.get())
                .define('#', Items.STICK)
                .define('%', Items.DIAMOND)
                .unlockedBy("has_raw_glitch", has(ModItems.RAW_GLITCH.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GLITCH_BLOCK.get().asItem())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.GLITCH_INGOT.get())
                .unlockedBy("has_glitch_ingot", has(ModItems.GLITCH_INGOT.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GLITCH_INGOT.get(), 9)
                .requires(ModBlocks.GLITCH_BLOCK).unlockedBy("has_glitch_ingot", has(ModItems.GLITCH_INGOT.get())).save(recipeOutput);

        oreSmelting(recipeOutput, GLITCH_SMELTABLES, RecipeCategory.MISC, ModItems.GLITCH_INGOT.get(), 1, 300, "glitch_ingot");
        oreBlasting(recipeOutput, GLITCH_SMELTABLES, RecipeCategory.MISC, ModItems.GLITCH_INGOT.get(), 1, 150, "glitch_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GLITCH_COAL_BLOCK.get().asItem())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.GLITCH_COAL.get())
                .unlockedBy("has_glitch_coal", has(ModItems.GLITCH_COAL.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GLITCH_COAL.get(), 9)
                .requires(ModBlocks.GLITCH_COAL_BLOCK).unlockedBy("has_glitch_ingot", has(ModItems.GLITCH_INGOT.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLITCH_IGNITER.get())
                .pattern("  @")
                .pattern(" # ")
                .pattern("   ")
                .define('#', ModItems.GLITCH_COAL.get())
                .define('@', ModItems.RAW_GLITCH.get())
                .unlockedBy("has_raw_glitch", has(ModItems.RAW_GLITCH.get())).save(recipeOutput);


    }
}
