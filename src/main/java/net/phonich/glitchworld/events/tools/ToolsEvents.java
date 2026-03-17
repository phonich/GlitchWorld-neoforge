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

                if (stack.is(ModItems.GLITCH_SWORD.get())) { // МЕЧ
                    if (active) {
                        player.removeEffect(MobEffects.MOVEMENT_SPEED);
                    }

                } else if (stack.is(ModItems.GLITCH_PICKAXE.get())) { // КИРКА
                    if (active) {
                        player.removeEffect(MobEffects.DIG_SPEED);
                    }

                }
            } else {
                if (stack.is(ModItems.GLITCH_SWORD.get()) && MyMethods.getStateOfGlitchItem(stack)) { // МЕЧ
                    Arrow arrow = new Arrow(EntityType.ARROW, level);
                    arrow.moveTo(MyMethods.getPosForArrow(player, 1));
                    arrow.setOwner(player);
                    arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
                    level.addFreshEntity(arrow);
                    if (level instanceof ServerLevel level1) {
                        stack.hurtAndBreak(3, level1, player, item -> {
                            player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                        });
                    }
                }

                if (stack.is(ModItems.GLITCH_PICKAXE.get()) && MyMethods.getStateOfGlitchItem(stack)) {
                    boolean active = stack.getOrDefault(ModDataComponents.IS_BIGDICK_ON, false);
                    stack.set(ModDataComponents.IS_BIGDICK_ON, !active);
                    if (!active) {
                        player.displayClientMessage(Component.translatable("tooltip.glitchworld.glitch_pickaxe_on.tooltip"), true);
                    }
                    else {
                        player.displayClientMessage(Component.translatable("tooltip.glitchworld.glitch_pickaxe_off.tooltip"), true);
                    }
                }
            }
        }
    }

    public static void onPlayerTickTool(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (player.tickCount % 10 == 0) {
            if (stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false)) {
                if (stack.is(ModItems.GLITCH_SWORD.get())) { // МЕЧ
                    MyMethods.updateDurationOfEffect(MobEffects.MOVEMENT_SPEED, player);
                }

                else if (stack.is(ModItems.GLITCH_PICKAXE.get())) { // КИРКА
                    MyMethods.updateDurationOfEffect(MobEffects.DIG_SPEED, player);
                }

            }
        }
    }
    public static void onEntityDamageTool(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player && !event.getEntity().level().isClientSide) {
            ItemStack stack = player.getMainHandItem();

            if (MyMethods.getStateOfGlitchItem(stack)) {// если режим активирован

                if (stack.is(ModItems.GLITCH_SWORD.get())) { // МЕЧ
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

    public static void onBlockBreakTool(BlockEvent.BreakEvent event, ItemStack stack) {
        if (stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false)) {
            if (stack.is(ModItems.GLITCH_PICKAXE.get())) { // КИРКА
                Player player = event.getPlayer();
                Level level = event.getPlayer().level();
                BlockPos pos = event.getPos();
                BlockState state = event.getState();
                if (stack.getOrDefault(ModDataComponents.IS_BIGDICK_ON, false)) {
                    MyMethods.bigDick(player, level, pos, state, BlockTags.MINEABLE_WITH_PICKAXE);
                }

            }
        }
    }
}