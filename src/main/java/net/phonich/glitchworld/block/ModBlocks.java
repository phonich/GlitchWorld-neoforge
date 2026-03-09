package net.phonich.glitchworld.block;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
//import net.phonich.glitchworld.block.custom.GlitchChaosBlock;
import net.phonich.glitchworld.block.custom.GlitchChaosBlock;
import net.phonich.glitchworld.block.custom.GlitchConverter;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Glitchworld.MODID);

    public static final DeferredBlock<Block> GLITCH_BLOCK = registerBlock("glitch_block",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).lightLevel(blockState -> 7)));// создали блок

    public static final DeferredBlock<Block> GLITCH_ORE = registerBlock("glitch_ore",
            () -> new DropExperienceBlock(UniformInt.of(30, 40), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).lightLevel(blockState -> 11)));

    public static final DeferredBlock<Block> GLITCH_CHAOS_BLOCK = registerBlock("glitch_chaos_block",
            ()-> new GlitchChaosBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel(state -> 7).noLootTable()));

    public static final DeferredBlock<Block> GLITCH_PORTAL_FRAME = registerBlock("glitch_portal_frame",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).noLootTable()));

    public static final DeferredBlock<Block> GLITCH_CONVERTER = registerBlock("glitch_converter",
            () -> new GlitchConverter(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(20, 10)));

    public static final DeferredBlock<Block> GLITCH_COAL_BLOCK = registerBlock("glitch_coal_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_BLOCK)));

    public static final DeferredBlock<Block> GLITCH_STONE = registerBlock("glitch_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
            DeferredBlock<T> toReturn = BLOCKS.register(name, block);
            registerBlockItem(name, toReturn);
            return toReturn;
        }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
