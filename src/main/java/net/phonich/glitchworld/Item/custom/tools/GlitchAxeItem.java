package net.phonich.glitchworld.Item.custom.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.phonich.glitchworld.component.ModDataComponents;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class GlitchAxeItem extends AxeItem {
    public GlitchAxeItem(Tier tier, Properties properties) {
        super(tier, properties.attributes(AxeItem.createAttributes(tier, 11f, -3f) // создаем базовые атрибуты (урон, скорость атаки и т.д.)
                .withModifierAdded(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(UUIDs.BLOCK_INTERACTION_RANGE_MODIFIER_UUID, 3.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)));
                // добавляем атрибут
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        if (!player.getCooldowns().isOnCooldown(stack.getItem()) && stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE.get(), false)) {
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
            lightningBolt.moveTo(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
            level.addFreshEntity(lightningBolt);
            player.getCooldowns().addCooldown(stack.getItem(), 600);
        }


        return InteractionResult.SUCCESS;
    }
}
