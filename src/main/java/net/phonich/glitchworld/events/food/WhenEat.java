package net.phonich.glitchworld.events.food;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.phonich.glitchworld.effect.ModEffects;
import net.phonich.glitchworld.sound.ModSounds;

import static net.phonich.glitchworld.util.MyMethods.corruptPlayer;

public class WhenEat {
    public static void onEatNotGLitchFood(Level level, Player player, MobEffect effect) {
        if (effect.equals(ModEffects.GLITCH_ADDICTION_EFFECT.value())) {
            level.explode(null, player.getX(), player.getY(), player.getZ(), 5.0F, Level.ExplosionInteraction.TRIGGER);
        }
        else {
            if (player.getEffect(ModEffects.GLITCH_CORRUPTION_EFFECT.getDelegate()).getAmplifier() >= 1) {
            level.explode(null, player.getX(), player.getY(), player.getZ(), 0.2F, Level.ExplosionInteraction.TRIGGER);
            }
        }
    }
    public static void corruptionByEating(Player player, Level level) {
        int random = level.random.nextInt(1, 15);
        if (random == 10) {
            corruptPlayer(player, level, 0);
        }
    }
}
