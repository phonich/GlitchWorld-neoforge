package net.phonich.glitchworld.events.tools;

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
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.component.ModDataComponents;

@EventBusSubscriber(modid = Glitchworld.MODID) // говорим,что этот класс будет слушать события, которые происходят в игре
public class SwordEvents {
    @SubscribeEvent  // Теперь говорим, что нужно вызвать этот метод, когда произойдет событие
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        Level level = event.getLevel();
        if (!level.isClientSide) {
            if (stack.getItem() == ModItems.GLITCH_SWORD.get()) {
                boolean active = stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false); // переменная active - если у предмета нет значения, то задаем false, а если есть, то то, которое есть
                stack.set(ModDataComponents.IS_GLITCH_STATE_ACTIVE, !active); // задаем в компонент противоположное значение

                if (!active) {
                    level.playSound(null, player.getOnPos(), SoundEvents.BEACON_ACTIVATE, SoundSource.MASTER);
                    player.hurt(player.damageSources().magic(), 1.0F);

                } else {

                    player.removeEffect(MobEffects.MOVEMENT_SPEED);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (stack.getItem() == ModItems.GLITCH_SWORD.get() && player.tickCount % 10 == 0 && stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false)) { // каждые пол секунды, если предмет в руке - глитч меч + если состояние true
            if (player.getEffect(MobEffects.MOVEMENT_SPEED) == null || player.getEffect(MobEffects.MOVEMENT_SPEED).getDuration() < 2) { // если эффекта нет или эффект длится меньше 2 секунд
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30), null);

            }
        }
    }

//    @SubscribeEvent
//    public static void onEntityDamage(LivingDamageEvent.Pre event) {
//
//    }  при получении урона сущность
}