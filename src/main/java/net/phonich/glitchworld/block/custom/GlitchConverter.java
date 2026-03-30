package net.phonich.glitchworld.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.block.ModBlocks;
import net.phonich.glitchworld.sound.ModSounds;

import java.util.Map;

public class GlitchConverter extends Block {
    public GlitchConverter(Properties pProperties) {
        super(pProperties);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            Map<Item, Item> convertible_items = Map.of(
                    Items.COAL_BLOCK, ModBlocks.GLITCH_COAL_BLOCK.asItem(),
                    Items.COAL.asItem(), ModItems.GLITCH_COAL.get().asItem()
            ); // создаем коллекцию внутри метода, после инициализации регистра, иначе все ломается

            if (convertible_items.containsKey(itemStack.getItem())) {
                ItemStack newItemStack = new ItemStack(convertible_items.get(itemStack.getItem()), 1);
                player.addItem(newItemStack);
                player.getItemInHand(interactionHand).setCount(itemStack.getCount() - 1);
                player.hurt(player.damageSources().magic(), 1.0F);
                player.giveExperiencePoints(4);
                level.playSound(null, pos, ModSounds.GLITCH_CONVERTER_CONVERT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }

        }
        return ItemInteractionResult.SUCCESS;
    }
}

