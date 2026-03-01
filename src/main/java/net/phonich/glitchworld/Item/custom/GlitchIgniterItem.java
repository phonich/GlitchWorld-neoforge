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
import org.jetbrains.annotations.Nullable;
import org.openjdk.nashorn.internal.runtime.arrays.IteratorAction;

import java.util.List;
import java.util.function.Consumer;

public class GlitchIgniterItem extends Item {
    public GlitchIgniterItem(Properties pProperties) {
        super(pProperties);
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



   // @Override
    //public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
        //p_339594_(Component.translatable("tooltip.mymod.glitch_igniter.tooltip").withStyle(ChatFormatting.GRAY));
        //super.appendHoverText(p_41421_, p_339594_, p_41423_, p_41424_);
   // }
}
