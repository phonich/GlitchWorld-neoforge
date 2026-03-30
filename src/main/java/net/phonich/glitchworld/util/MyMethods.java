package net.phonich.glitchworld.util;

import com.ibm.icu.text.StringTransform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.phonich.glitchworld.component.ModDataComponents;
import net.phonich.glitchworld.effect.ModEffects;
import net.phonich.glitchworld.sound.ModSounds;

import java.util.List;
import java.util.Optional;

public class MyMethods {
    public static boolean getStateOfGlitchItem(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false);
    }

    public static void updateDurationOfEffect(Holder<MobEffect> mobEffect, Player player) {
        if (player.getEffect(mobEffect) == null || player.getEffect(mobEffect).getDuration() < 250) {
            player.addEffect(new MobEffectInstance(mobEffect, 300, 1), null);
        }
    }

    public static Vec3 getPosForArrow(Player player, double offset) {
        double dx = -Math.sin(Math.toRadians(player.getYRot())) * Math.cos(Math.toRadians(player.getXRot()));
        double dy = -Math.sin(Math.toRadians(player.getXRot()));
        double dz = Math.cos(Math.toRadians(player.getYRot())) * Math.cos(Math.toRadians(player.getXRot()));

        return new Vec3(
                player.getX() + dx * offset,
                player.getEyeY() + dy * offset,
                player.getZ() + dz * offset
        );
    }

    public static void bigDick(Player player, Level level, BlockPos pos, BlockState state, TagKey<Block> tag) {
        if (state.canHarvestBlock(level, pos, player)) {

            Direction directon = player.getDirection();
            float pitch = player.getXRot();

            for (int a = -1; a < 2; a++) {
                for (int b = -1; b < 2; b++) {
                    BlockPos newPos;
                    if (directon.getAxis() == Direction.Axis.Z) {
                        newPos = pos.offset(a, b, 0); // XY
                    } else if (pitch > 40 || pitch < -40) {
                        newPos = pos.offset(a, 0, b); // XZ
                    } else {
                        newPos = pos.offset(0, a, b); // YZ
                    }

                    if (level.getBlockState(newPos).is(tag)) {

                        level.destroyBlock(newPos, true, player);
                    }
                }
            }
        }
    }

    public static void changeBigDickState(ItemStack stack, Player player, Level level) {
        boolean active = stack.getOrDefault(ModDataComponents.IS_BIGDICK_ON, false);
        stack.set(ModDataComponents.IS_BIGDICK_ON, !active);
        if (!active) {
            player.displayClientMessage(Component.translatable("tooltip.glitchworld.glitch_3x3_on.tooltip"), true);
            level.playSound(null, player.getOnPos(), ModSounds.BIG_DICK_CHANGE.get(), SoundSource.MASTER);
        } else {
            player.displayClientMessage(Component.translatable("tooltip.glitchworld.glitch_3x3_off.tooltip"), true);
        }
    }

    public static void corruptPlayer(Player player, Level level, int amplifier) {
        player.addEffect(new MobEffectInstance(ModEffects.GLITCH_CORRUPTION_EFFECT, -1, amplifier));
        level.playSound(null, player.getOnPos(), ModSounds.SCARY_GLITCH.get(), SoundSource.PLAYERS, 1f, 1f);
    }

    public static void toCorruption(Player player, int i) {
        if (!player.level().isClientSide) {
            int infection = player.getData(ModAttachments.CORRUPTION);
            if (infection <= 600 && infection >= 0) {
                player.setData(ModAttachments.CORRUPTION, infection + i);
                if (i > 0) {
                    switch (infection) {
                        case 60:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_1"));
                            break;
                        case 120:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_2"));
                            break;
                        case 180:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_3"));
                            break;
                        case 240:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_4"));
                            break;
                        case 300:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_5"));
                            break;
                        case 360:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_6"));
                            break;
                        case 420:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_7"));
                            break;
                        case 480:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_8"));
                            break;
                        case 540:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_9"));
                            break;
                        case 600:
                            player.sendSystemMessage(Component.translatable("tooltip.glitchworld.warning_10"));
                            MyMethods.corruptPlayer(player, player.level(), 0);
                            break;
                    }
                }
            }
        }
    }
}
