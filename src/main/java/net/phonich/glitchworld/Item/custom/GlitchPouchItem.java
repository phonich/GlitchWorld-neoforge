package net.phonich.glitchworld.Item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.phonich.glitchworld.Item.ModItems;


import java.util.List;
import java.util.Random;

import static net.phonich.glitchworld.Item.ModItems.RAW_GLITCH;


public class GlitchPouchItem extends Item {
    public GlitchPouchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();

         // объект для рандома
        Item[] someItems = {RAW_GLITCH.get(), Items.DIAMOND, Items.EMERALD}; // список предметов
        Item randItem = someItems[level.random.nextInt(someItems.length)];
        if (!level.isClientSide()) {
            player.addItem(new ItemStack(randItem, 1));
            player.getItemInHand(pContext.getHand()).setCount(pContext.getItemInHand().getCount() - 1);
            level.playSound(null, pContext.getClickedPos(), SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.MASTER, 1.0F, 1.0F);
        }
        return InteractionResult.SUCCESS;
    }
}
// Не доделано, хочу седня успеть запушить:(