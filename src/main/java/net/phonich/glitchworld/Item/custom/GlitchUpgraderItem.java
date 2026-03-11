package net.phonich.glitchworld.Item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.phonich.glitchworld.component.ModDataComponents;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GlitchUpgraderItem extends Item {
    private static final Map<Block, Block> blocksUpgradedVar = Map.of(
            Blocks.COAL_BLOCK, Blocks.IRON_BLOCK,
            Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK,
            Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK,
            Blocks.DIAMOND_BLOCK, Blocks.ANCIENT_DEBRIS
    );
    private static final Map<Block, Block> blocksDegradedVar = Map.of(
            Blocks.COAL_BLOCK, Blocks.GRASS_BLOCK,
            Blocks.IRON_BLOCK, Blocks.COAL_BLOCK,
            Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
            Blocks.DIAMOND_BLOCK, Blocks.GOLD_BLOCK
    );

    public GlitchUpgraderItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        int num = level.random.nextInt(3);
        BlockPos blockPos = context.getClickedPos();
        Block blockClicked = level.getBlockState(blockPos).getBlock();
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            if (blocksUpgradedVar.containsKey(blockClicked)) {
                if (num == 1) {
                    level.setBlockAndUpdate(blockPos, blocksUpgradedVar.get(blockClicked).defaultBlockState());
                    level.playSound(null, blockPos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

                }
                else {
                    if (blockClicked.equals(Blocks.COAL_BLOCK)) {
                        level.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 2.0F, true, Level.ExplosionInteraction.BLOCK);
                        return InteractionResult.SUCCESS;
                    }
                    level.setBlockAndUpdate(blockPos, blocksDegradedVar.get(blockClicked).defaultBlockState());
                    level.playSound(null, blockPos, SoundEvents.ANVIL_DESTROY, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                context.getItemInHand().hurtAndBreak(1, serverLevel, context.getPlayer(), item -> {
                    context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                });
            }
        }


        return InteractionResult.SUCCESS;
    }
}
