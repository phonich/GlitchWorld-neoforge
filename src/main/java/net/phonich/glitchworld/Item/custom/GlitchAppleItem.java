package net.phonich.glitchworld.Item.custom;

import net.minecraft.client.particle.FireworkParticles;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GlitchAppleItem extends Item {
    public GlitchAppleItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            BlockPos position = player.blockPosition();
            Random random = new Random();
            int num = level.random.nextInt(1, 6);
            if (!level.isClientSide) {
                if (num == 1) {
                    level.explode(null, position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 2, 2.0f, false, Level.ExplosionInteraction.MOB);
                } else if (num == 2) {
                    int i = 0;
                    stack.setCount(stack.getCount() - 1); // удаляем яблоко, иначе оно дюпается
                    while (i < player.getInventory().getContainerSize()) {
                        ItemStack itemStack = player.getInventory().getItem(i);
                        if (itemStack.getCount() != 0) {
                            player.drop(itemStack.copy(), true, true);
                            player.getInventory().setItem(i, ItemStack.EMPTY);
                        }
                        i++;
                    }
                    level.playSound(null, position, SoundEvents.ANCIENT_DEBRIS_BREAK, SoundSource.MASTER, 2.0F, 2.0F);

                } else if (num == 3) {

                    for (int i = 1; i < 5; i++) {
                        BlockPos positionAbove = position.above(i);
                        if (!level.isEmptyBlock(positionAbove)) {
                            if (level.getBlockState(positionAbove).getDestroySpeed(level, positionAbove) < 0) {
                                player.hurt(player.damageSources().magic(), 10f);
                                return super.finishUsingItem(stack, level, livingEntity);
                            }
                            level.destroyBlock(positionAbove, true);
                        }
                    }
                    level.setBlock(position.above(4), Blocks.ANVIL.defaultBlockState(), 11);
                }
                else if (num == 4) {
                    ItemStack stackDiamonds = Items.DIAMOND.getDefaultInstance();
                    stackDiamonds.setCount(4);
                    player.addItem(stackDiamonds);
                    level.playSound(null, position, SoundEvents.DECORATED_POT_INSERT, SoundSource.PLAYERS, 1.0f, 1.0f);
                } else if (num == 5) {
                    for (int i = 1; i < 10; i++) { // задумывается как много телепортаций за секунды 2, а не за 1 тик. Как сделать задержку - пока не понимаю
                        int newX = position.getX() + random.nextInt(-10, 10);
                        int newZ = position.getZ() + random.nextInt(-10, 10);
                        int oldY = position.getY();
                        while (!level.isEmptyBlock(new BlockPos(newX, oldY, newZ))) {
                            oldY++;
                        }
                        player.teleportTo(newX, oldY, newZ);
                    }
                }
            }
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
