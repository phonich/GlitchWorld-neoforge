package net.phonich.glitchworld.Item.custom.food;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.phonich.glitchworld.effect.ModEffects;

public class GlitchCarrotItem extends Item {
    public GlitchCarrotItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {

        if (!level.isClientSide && livingEntity instanceof Player player) {
            int random = level.random.nextInt(1, 3);
            if (!player.hasEffect(ModEffects.GLITCH_ADDICTION_EFFECT)) {
                if (random == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 1));
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400, 2));
                }
            } else return super.finishUsingItem(stack, level, livingEntity);
            BlockPos pos = player.getOnPos();
            if (random == 1) {
                int i = 1;
                while (level.isEmptyBlock(pos.above(i)) && i < 60) {
                    i++;
                }
                player.teleportTo(player.getX(), pos.above(i - 1).getY(), player.getZ());
            }
            else if (random == 2) {
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
                lightningBolt.moveTo(pos.getX(), pos.getY(), pos.getZ());
                level.addFreshEntity(lightningBolt);

            }
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
