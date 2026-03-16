package net.phonich.glitchworld.events.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.component.ModDataComponents;
import net.phonich.glitchworld.util.MyMethods;

@EventBusSubscriber(modid = Glitchworld.MODID) // говорим,что этот класс будет слушать события, которые происходят в игре
public class SwordEvents {

    @SubscribeEvent  // Теперь говорим, что нужно вызвать этот метод, когда произойдет событие
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        Level level = event.getLevel();
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

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (player.tickCount % 10 == 0 && stack.is(ModItems.GLITCH_SWORD.get()) && MyMethods.getStateOfGlitchItem(stack)) { // каждые пол секунды, если предмет в руке - глитч меч + если состояние true
            if (player.getEffect(MobEffects.MOVEMENT_SPEED) == null || player.getEffect(MobEffects.MOVEMENT_SPEED).getDuration() < 9) { // если эффекта нет или эффект длится меньше 2 секунд
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1), null);

            }
        }
    }

    @SubscribeEvent
    public static void onEntityDamage(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player && !event.getEntity().level().isClientSide) { // Первое - если ударил игрок, второе - обязательно проверка, чтобы не вызвался дважды
            ItemStack stack = player.getMainHandItem();
            if (MyMethods.getStateOfGlitchItem(stack) && stack.is(ModItems.GLITCH_SWORD.get())) {
                event.setNewDamage(event.getOriginalDamage() + 10);
                LivingEntity livingEntity = event.getEntity();
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
            }
        }
    }
}