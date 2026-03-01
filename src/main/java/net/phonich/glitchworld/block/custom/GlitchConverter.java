package net.phonich.glitchworld.block.custom;

import com.mojang.serialization.DataResult;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.phonich.glitchworld.Item.ModItems;

public class GlitchConverter extends Block {
    public GlitchConverter(Properties pProperties) {
        super(pProperties);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (itemStack.is(Items.COAL)) {
                ItemStack newItemStack = new ItemStack(ModItems.GLITCH_COAL.get(), 1);
                player.addItem(newItemStack);
                player.getItemInHand(interactionHand).setCount(itemStack.getCount() - 1);
                player.hurt(player.damageSources().magic(), 1.0F);
                player.giveExperiencePoints(4);
            }

        }
        return ItemInteractionResult.SUCCESS;
    }
}

