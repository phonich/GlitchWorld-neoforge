package net.phonich.glitchworld.events.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.phonich.glitchworld.Glitchworld;
import net.phonich.glitchworld.util.ModTags;
import net.phonich.glitchworld.util.MyMethods;

@EventBusSubscriber(modid = Glitchworld.MODID)
public class GeneralToolsEvents {
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(ModTags.Items.GLITCH_TOOLS) && MyMethods.getStateOfGlitchItem(stack)) {
            event.getToolTip().add(Component.translatable("tooltip.glitchworld.state_active.tooltip"));
        }
        else if (stack.is(ModTags.Items.GLITCH_TOOLS) && !MyMethods.getStateOfGlitchItem(stack)) {
            event.getToolTip().add(Component.translatable("tooltip.glitchworld.state_deactive.tooltip"));
        }
    }
}
