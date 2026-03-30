package net.phonich.glitchworld.events.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.component.ModDataComponents;
import net.phonich.glitchworld.util.MyMethods;


public class ToolsEvents {

    public static void onRightClickTool(PlayerInteractEvent event) {
        Level level = event.getLevel();
        if (!level.isClientSide) {
            ItemStack stack = event.getItemStack();
            Player player = event.getEntity();
            if (player.isShiftKeyDown()) {
                boolean active = stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false); // переменная active - если у предмета нет значения, то задаем false, а если есть, то то, которое есть
                stack.set(ModDataComponents.IS_GLITCH_STATE_ACTIVE, !active); // задаем в компонент противоположное значение

                if (!active) {
                    level.playSound(null, player.getOnPos(), SoundEvents.BEACON_ACTIVATE, SoundSource.MASTER);
                }
                if (active) {
                    if (stack.is(ModItems.GLITCH_SWORD.get())) { // МЕЧ
                        player.removeEffect(MobEffects.MOVEMENT_SPEED);
                    } else if (stack.is(ModItems.GLITCH_PICKAXE.get())) { // КИРКА
                        player.removeEffect(MobEffects.NIGHT_VISION);
                    } else if (stack.is(ModItems.GLITCH_SHOVEL.get())) { // ЛОПАТА
                        player.removeEffect(MobEffects.DIG_SPEED);
                    }
                }
            }
        }
    }


    public static void onPlayerTickTool(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (player.tickCount % 20 == 0) {
            if (stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false)) {
                if (stack.is(ModItems.GLITCH_SWORD.get())) { // МЕЧ
                    MyMethods.updateDurationOfEffect(MobEffects.MOVEMENT_SPEED, player);

                } else if (stack.is(ModItems.GLITCH_PICKAXE.get())) { // КИРКА
                    MyMethods.updateDurationOfEffect(MobEffects.NIGHT_VISION, player);
                } else if (stack.is(ModItems.GLITCH_SHOVEL.get())) { // ЛОПАТА
                    MyMethods.updateDurationOfEffect(MobEffects.DIG_SPEED, player);
                }
                MyMethods.toCorruption(player, 2);
            }
            else {
                MyMethods.toCorruption(player, -1);
            }
        }
    }
}
//    public static void onEntityDamageTool(LivingDamageEvent.Pre event) {
//    }
//
//    public static void onBlockBreakTool(BlockEvent.BreakEvent event, ItemStack stack) {
//        }
