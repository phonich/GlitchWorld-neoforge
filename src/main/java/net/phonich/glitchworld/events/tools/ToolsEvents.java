package net.phonich.glitchworld.events.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.component.ModDataComponents;
import net.phonich.glitchworld.util.MyMethods;

public class ToolsEvents {

    public static void onRightClickTool(Player player, Level level, ItemStack stack) {
        if (!level.isClientSide) {
            if (stack.is(ModItems.GLITCH_SWORD.get())) {
                boolean active = stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false); // переменная active - если у предмета нет значения, то задаем false, а если есть, то то, которое есть
                stack.set(ModDataComponents.IS_GLITCH_STATE_ACTIVE, !active); // задаем в компонент противоположное значение

                if (!active) {
                    level.playSound(null, player.getOnPos(), SoundEvents.BEACON_ACTIVATE, SoundSource.MASTER);
                } else {

                    player.removeEffect(MobEffects.MOVEMENT_SPEED);
                }
            }
        }
    }

    public static void onPlayerTickTool(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false)) {
            if (stack.is(ModItems.GLITCH_SWORD.get()) && player.tickCount % 10 == 0) {
                if (player.getEffect(MobEffects.MOVEMENT_SPEED) == null || player.getEffect(MobEffects.MOVEMENT_SPEED).getDuration() < 250) { // если эффекта нет или эффект длится меньше 2 секунд
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1), null);
            }
        }
    }
}
    public static void onEntityDamageTool(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player && !event.getEntity().level().isClientSide) {
            ItemStack stack = player.getMainHandItem();

            if (MyMethods.getStateOfGlitchItem(stack)) {// если режим активирован

                if (stack.is(ModItems.GLITCH_SWORD.get())) { // для меча
                    event.setNewDamage(event.getOriginalDamage() + 5); // увеличиваем урон
                    LivingEntity livingEntity = event.getEntity();
                    BlockPos pos = livingEntity.blockPosition();
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2)); // даем эффект на противника
                    int hits = stack.getOrDefault(ModDataComponents.HITS_TO_ANOMALY, 10);
                    hits -= 1;
                    stack.set(ModDataComponents.HITS_TO_ANOMALY, hits);
                    if (hits < 1) { // взрывы
                        event.getEntity().level().explode(null, pos.getX(), pos.getY(), pos.getZ(), 1F, false, Level.ExplosionInteraction.NONE);
                        stack.set(ModDataComponents.HITS_TO_ANOMALY, 10);
                    }
                }
            }


        }
    }
}