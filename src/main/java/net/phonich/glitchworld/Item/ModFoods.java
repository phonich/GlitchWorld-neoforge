package net.phonich.glitchworld.Item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties GLITCH_APPLE = // почему-то FoodProperties - странно, но так надо
            new FoodProperties.Builder().nutrition(10).saturationModifier(0.3f) // nutrition - скок голода восстанавливает, saturation - насыщение
                    .alwaysEdible() // чтобы можно было есть при полном голоде
                    .build();
    public static final FoodProperties GLITCH_CARROT = new FoodProperties.Builder().nutrition(9).saturationModifier(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 4000), 1.0F).alwaysEdible().build();
}
