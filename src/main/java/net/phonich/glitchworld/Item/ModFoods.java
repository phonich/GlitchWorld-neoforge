package net.phonich.glitchworld.Item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties GLITCH_APPLE = // почему-то FoodProperties - странно, но так надо
            new FoodProperties.Builder().nutrition(10).saturationModifier(0.3f) // nutrition - скок голода восстанавливает, saturation - насыщение
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 4000), 0.5f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 4000), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4000), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 400), 0.2f)
                    .alwaysEdible().build(); // чтобы можно было есть при полном голоде
}
