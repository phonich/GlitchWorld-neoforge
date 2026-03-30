package net.phonich.glitchworld.Item.custom.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.phonich.glitchworld.Item.ModItems;
import net.phonich.glitchworld.component.ModDataComponents;
import net.phonich.glitchworld.util.MyMethods;

public class GlitchPickaxeItem extends PickaxeItem {
    public GlitchPickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (MyMethods.getStateOfGlitchItem(stack) && !player.isShiftKeyDown()) { // КИРКА
            MyMethods.changeBigDickState(stack, player, level);
        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
        if (!level.isClientSide && stack.getOrDefault(ModDataComponents.IS_GLITCH_STATE_ACTIVE, false) && stack.getOrDefault(ModDataComponents.IS_BIGDICK_ON, false)) {
                    MyMethods.bigDick(player, level, pos, state, BlockTags.MINEABLE_WITH_PICKAXE);
            }
        }
        return super.mineBlock(stack, level, state, pos, livingEntity);
    }
}
