package net.phonich.glitchworld.events.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.util.ModTags;
import net.phonich.glitchworld.util.MyMethods;

// общий класс для всех Glitch инструментов, с подписками на все возможные события и переводом после в класс ToolsEvents, где уже запускается механика для конкретного предмета
@EventBusSubscriber(modid = Glitchworld.MODID)
public class GeneralToolsEvents {
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(ModTags.Items.GLITCH_TOOLS) && MyMethods.getStateOfGlitchItem(stack)) {
            event.getToolTip().add(Component.translatable("tooltip.glitchworld.state_active.tooltip"));
        } else if (stack.is(ModTags.Items.GLITCH_TOOLS) && !MyMethods.getStateOfGlitchItem(stack)) {
            event.getToolTip().add(Component.translatable("tooltip.glitchworld.state_deactive.tooltip"));
        }
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(ModTags.Items.GLITCH_TOOLS)) {
            ToolsEvents.onRightClickTool(event.getEntity(), event.getLevel(), stack);
        }
    }

    @SubscribeEvent
    public static void onEntityDamage(LivingDamageEvent.Pre event) {
        if (event.getSource().getWeaponItem().is(ModTags.Items.GLITCH_TOOLS)) { // Первое - если ударил игрок, второе - обязательно проверка, чтобы не вызвался дважды
            ToolsEvents.onEntityDamageTool(event);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (stack.is(ModTags.Items.GLITCH_TOOLS)) { // каждые пол секунды, если предмет в руке - глитч + если состояние true
                ToolsEvents.onPlayerTickTool(event);
            }
        }
    }