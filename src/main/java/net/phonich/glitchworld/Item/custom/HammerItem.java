package net.phonich.glitchworld.Item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.block.ModBlocks;

import java.util.List;

public class HammerItem extends Item {

    public HammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.glitchworld.hammer.tooltip").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), ModItems.GLITCH_DUST.get().getDefaultInstance());
        if (!level.isClientSide) {
            BlockState blockState = level.getBlockState(pos);
            if (blockState.getBlock() == ModBlocks.GLITCH_ORE.get()) {
                level.destroyBlock(pos, false); // для звука и партиклов
                level.setBlock(pos, Blocks.STONE.defaultBlockState(), 11);
                level.playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS);
                level.addFreshEntity(itemEntity);
                if (level instanceof ServerLevel serverLevel) {
                    context.getItemInHand().hurtAndBreak(1, serverLevel, context.getPlayer(), e -> {
                        context.getPlayer().onEquippedItemBroken(context.getItemInHand().getItem(), EquipmentSlot.MAINHAND);
                    });
                }
            }
        }
        return super.useOn(context);
    }
}
