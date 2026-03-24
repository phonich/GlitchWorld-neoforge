package net.phonich.glitchworld.Item.custom.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.phonich.glitchworld.component.ModDataComponents;
import net.phonich.glitchworld.util.MyMethods;

public class GlitchSwordItem extends SwordItem {
    public GlitchSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player && !entity.level().isClientSide) {
            if (MyMethods.getStateOfGlitchItem(stack)) {// если режим активирован// МЕЧ
                    entity.hurt(player.damageSources().playerAttack(player), 5f); // увеличиваем урон
                    BlockPos pos = entity.blockPosition();
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2)); // даем эффект на противника
                    int hits = stack.getOrDefault(ModDataComponents.HITS_TO_ANOMALY, 10);
                    hits -= 1;
                    stack.set(ModDataComponents.HITS_TO_ANOMALY, hits);
                    if (hits < 1) { // взрывы
                        entity.level().explode(null, pos.getX(), pos.getY(), pos.getZ(), 1F, false, Level.ExplosionInteraction.NONE);
                        stack.set(ModDataComponents.HITS_TO_ANOMALY, 10);
                }
            }
        }
        return super.hurtEnemy(stack, entity, livingEntity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (MyMethods.getStateOfGlitchItem(stack) && !player.getCooldowns().isOnCooldown(stack.getItem()) && !player.isShiftKeyDown()) { // МЕЧ
            Arrow arrow = new Arrow(EntityType.ARROW, level);
            arrow.moveTo(MyMethods.getPosForArrow(player, 1));
            arrow.setOwner(player);
            arrow.pickup = AbstractArrow.Pickup.DISALLOWED; // нельзя поднимать стрелу
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
            level.addFreshEntity(arrow);
            if (level instanceof ServerLevel level1) {
                stack.hurtAndBreak(3, level1, player, item -> {
                    player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                });
            }
            player.getCooldowns().addCooldown(stack.getItem(), 30); // ставим кулдаун
        }
        return super.use(level, player, interactionHand);
    }
}

