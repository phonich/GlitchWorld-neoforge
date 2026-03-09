package net.phonich.glitchworld.Item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;


import java.util.List;


public class GlitchIgniterItem extends Item {
    public GlitchIgniterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.glitchworld.glitch_igniter.tooltip").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel(); // получили мир
        BlockPos pos = pContext.getClickedPos(); // позиция блока, на который мы кликнули
        Direction face = pContext.getClickedFace(); // сторона, по которой кликнули
        BlockPos firePos = pos.relative(face); // к позиции блока, по которому мы кликнули мы добавляем смещение (face - так как мы кликнули по верхней части, то он добавит по y + 1)

        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) { // изменения обязательно для сервера
            level.setBlock(firePos, Blocks.FIRE.defaultBlockState(), 11); // 11 - флаг обновления мира. Константа
            pContext.getItemInHand().hurtAndBreak(1, serverLevel, pContext.getPlayer(), e -> {
                pContext.getPlayer().onEquippedItemBroken(pContext.getItemInHand().getItem(), EquipmentSlot.MAINHAND);}); // 1 - снимаем прочность, потом получаем игрока, который тыкнул (Майнкрафту нужно знать, кому уменьшать прочность), после действие, которое делаем когда предмет сломался - проигрываем анимацию слома
        }
             return InteractionResult.SUCCESS;
    }

}
