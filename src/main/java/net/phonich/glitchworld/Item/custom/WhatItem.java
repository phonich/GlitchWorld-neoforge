package net.phonich.glitchworld.Item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class WhatItem extends Item {
    public WhatItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        player.startUsingItem(interactionHand);

        return InteractionResultHolder.consume(player.getItemInHand(interactionHand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof Player player && !level.isClientSide) {

            level.explode(null, player.getX(), player.getY(), player.getZ(), 20F, true, Level.ExplosionInteraction.TNT);



            for (int i = 1; i < 20; i++) {
                BlockPos pos = player.getOnPos();
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                lightning.moveTo(pos.getX(), pos.getY(), pos.getZ());
                level.addFreshEntity(lightning);

                for (int x = 1; x < 3; x++) {

                    BlockPos newPos = pos.east(level.random.nextInt(10));

                    Cat cat = EntityType.CAT.create(level);
                    cat.moveTo(newPos.getX(), newPos.getY(), newPos.getZ());
                    level.addFreshEntity(cat);
                }
                Warden warden = EntityType.WARDEN.create(level);
                warden.moveTo(pos.getX(), pos.getY(), pos.getZ());
                level.addFreshEntity(warden);

                pos = pos.west(level.random.nextInt(10)).above(2);
                Ghast ghast = EntityType.GHAST.create(level);
                ghast.moveTo(pos.getX(), pos.getY(), pos.getZ());
                level.addFreshEntity(ghast);
            }
            int y = 0;
            while (y < player.getInventory().getContainerSize()) {
                ItemStack itemStack = player.getInventory().getItem(y);
                if (itemStack.getCount() != 0) {
                    player.drop(itemStack.copy(), true, true);
                    player.getInventory().setItem(y, ItemStack.EMPTY);
                }
                y++;
            }
        }

        super.releaseUsing(stack, level, livingEntity, timeLeft);
    }

//    @Override
//    public boolean useOnRelease(ItemStack stack) {
//        return super.useOnRelease(stack);
//    }
}
