package net.phonich.glitchworld.Item.custom.food;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.phonich.glitchworld.effect.ModEffects;

public class GlitchAppleItem extends Item {
    public GlitchAppleItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            BlockPos position = player.blockPosition();
            int random = level.random.nextInt(1, 6);
            if (!level.isClientSide) {
                if (!player.hasEffect(ModEffects.GLITCH_ADDICTION_EFFECT)) {
                    if (random > 3) {
                        player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.WITHER, 300, 1));
                    }
                    else {
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 4000, 0));
                        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 4000, 0));

                    }
                }
                else {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 4000, 1));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 4000, 1));
                    return super.finishUsingItem(stack, level, livingEntity);


                }
                if (random == 1) {
                    level.explode(null, position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 2, 2.0f, false, Level.ExplosionInteraction.MOB);
                } else if (random == 2) {
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

                } else if (random == 3) {

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
                else if (random == 4) {
                    ItemStack stackDiamonds = Items.DIAMOND.getDefaultInstance();
                    stackDiamonds.setCount(4);
                    player.addItem(stackDiamonds);
                    level.playSound(null, position, SoundEvents.DECORATED_POT_INSERT, SoundSource.PLAYERS, 1.0f, 1.0f);
                } else if (random == 5) {
                    for (int i = 1; i < 10; i++) { // задумывается как много телепортаций за секунды 2, а не за 1 тик. Как сделать задержку - пока не понимаю
                        int newX = position.getX() + level.random.nextInt(-10, 10);
                        int newZ = position.getZ() + level.random.nextInt(-10, 10);
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

