package net.phonich.glitchworld.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.phonich.glitchworld.block.ModBlocks;


import java.util.Map;
import java.util.Random;

public class GlitchChaosBlock extends Block {
    public GlitchChaosBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
                Map<Block, Block> blocksConvert = Map.of(
                        Blocks.GRASS_BLOCK, Blocks.DIRT,
                        Blocks.STONE, ModBlocks.GLITCH_STONE.get(),
                        Blocks.DIAMOND_ORE, ModBlocks.GLITCH_ORE.get()
                );
                for (int x = -5; x < 5; x++) {
                    for (int z = -5; z < 5; z++) {
                        int someX = (pos.getX()) + x;
                        int someZ = (pos.getZ()) + z;
                        int someY = pos.getY();
                        BlockPos somePos = new BlockPos(someX, someY, someZ);
                        if (level.isEmptyBlock(somePos)) {
                            somePos = somePos.below(1);
                            if (level.isEmptyBlock(somePos)) {
                                continue;
                            }
                        }
                        Block newBlock = level.getBlockState(somePos).getBlock();
                        if (blocksConvert.containsKey(newBlock)) {
                            level.destroyBlock(somePos, false);
                            level.setBlock(somePos, blocksConvert.get(newBlock).defaultBlockState(), 11);
                        }
                    }
            }
                }
        return InteractionResult.SUCCESS;
    }
}
