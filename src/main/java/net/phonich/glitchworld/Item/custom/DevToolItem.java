package net.phonich.glitchworld.Item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.phonich.glitchworld.component.ModDataComponents;

import java.util.List;

public class DevToolItem extends Item {

    public DevToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockState blockState = level.getBlockState(context.getClickedPos());
        if (!level.isClientSide) {
            if (context.getPlayer().isShiftKeyDown()) {
                BlockState blockStateSaved = context.getItemInHand().get(ModDataComponents.THE_BLOCK_WAS).getBlock().defaultBlockState();
                level.setBlock(context.getClickedPos(), blockStateSaved, 11);
            } else {
                context.getItemInHand().set(ModDataComponents.THE_BLOCK_WAS, blockState);
            }
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag) {


        if (itemStack.get(ModDataComponents.THE_BLOCK_WAS) != null && Screen.hasShiftDown()) {
            components.add(Component.translatable("Block saved: " + itemStack.get(ModDataComponents.THE_BLOCK_WAS).getBlock().getDescriptionId()));
        }

        super.appendHoverText(itemStack, tooltipContext, components, tooltipFlag);
    }
}
