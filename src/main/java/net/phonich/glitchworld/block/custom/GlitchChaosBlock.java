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


import java.util.Random;

public class GlitchChaosBlock extends Block {
    public GlitchChaosBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            Random random = new Random();
            int num = random.nextInt(1, 3);
            if (num == 1) {
                int y = 1;
                while (!level.isEmptyBlock(pos.above(y))) {
                    BlockState newState = level.getBlockState(pos.above(y));
                    Block blockAbove = newState.getBlock(); // узнаем уже какой блок там стоит
                    y++;
                    if (blockAbove != Blocks.NETHER_BRICK_FENCE) {
                        return InteractionResult.SUCCESS;
                    }
                }

                level.setBlock(pos.above(y), Blocks.NETHER_BRICK_FENCE.defaultBlockState(), 11);
            } else if (num == 2) {

                int x = 1;
                int z = 0;
                Block[] someBlocks = {Blocks.ACACIA_LOG, Blocks.DIAMOND_BLOCK, Blocks.ANVIL};
                Block randBlock = someBlocks[random.nextInt(someBlocks.length)];
                //Block randBlock = ModBlocks.GLITCH_BLOCK.get();
                while (1 > 0) {
                    if (!level.getBlockState(pos.below(1).east(x)).is(randBlock)) {

                        level.setBlock(pos.below().east(x), randBlock.defaultBlockState(), 11);
                        return InteractionResult.SUCCESS;

                    } else if (!level.getBlockState(pos.below(1).north(x)).is(randBlock)) {

                        level.setBlock(pos.below().north(x), randBlock.defaultBlockState(), 11);
                        return InteractionResult.SUCCESS;

                    } else if (!level.getBlockState(pos.below(1).west(x)).is(randBlock)) {

                        level.setBlock(pos.below().west(x), randBlock.defaultBlockState(), 11);
                        return InteractionResult.SUCCESS;

                    } else if (!level.getBlockState(pos.below(1).south(x)).is(randBlock)) {

                        level.setBlock(pos.below().south(x), randBlock.defaultBlockState(), 11);
                        return InteractionResult.SUCCESS;

                    }
                    x++;
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
