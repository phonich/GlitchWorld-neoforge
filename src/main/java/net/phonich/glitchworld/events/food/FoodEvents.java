package net.phonich.glitchworld.events.food;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.effect.ModEffects;
import net.phonich.glitchworld.util.ModTags;

@EventBusSubscriber(modid = Glitchworld.MODID)
public class FoodEvents {
    @SubscribeEvent
    public static void onEat(net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack stack = event.getResultStack();
            Level level = player.level();
            if (event.getEntity().hasEffect(ModEffects.GLITCH_ADDICTION_EFFECT) && !stack.is(ModTags.Items.GLITCH_FOOD)) {
                WhenEat.onEatNotGLitchFood(level, player, ModEffects.GLITCH_ADDICTION_EFFECT.value());
            }
            else if (event.getEntity().hasEffect(ModEffects.GLITCH_CORRUPTION_EFFECT) && !stack.is(ModTags.Items.GLITCH_FOOD)) {
                WhenEat.onEatNotGLitchFood(level, player, ModEffects.GLITCH_CORRUPTION_EFFECT.value());
            }
            else {
                if (event.getResultStack().is(ModTags.Items.GLITCH_FOOD)) {
                    WhenEat.corruptionByEating(player, level);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event) {

        if (event.getEffect() == ModEffects.GLITCH_CORRUPTION_EFFECT || event.getEffect() == ModEffects.GLITCH_ADDICTION_EFFECT) {

            event.setCanceled(true); // отменяем снятие эффекта
        }
    }
}
