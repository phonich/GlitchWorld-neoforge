package net.phonich.glitchworld.effect;

import com.google.common.eventbus.EventBus;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.NeutralMob;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phonich.glitchworld.Glitchworld;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Glitchworld.MODID);

    public static final Holder<MobEffect> GLITCH_CORRUPTION_EFFECT = MOB_EFFECTS.register("glitch_corruption",
            () -> new GlitchCorruptionEffect(MobEffectCategory.HARMFUL, 0x871f78));
    public static final Holder<MobEffect> GLITCH_ADDICTION_EFFECT = MOB_EFFECTS.register("glitch_addiction",
            () -> new GlitchAddictionEffect(MobEffectCategory.NEUTRAL, 0x871f71));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
