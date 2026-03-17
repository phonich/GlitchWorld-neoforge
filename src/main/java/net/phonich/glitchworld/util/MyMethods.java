package net.phonich.glitchworld.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
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
//                        autoSmelting(level, state.getDrops(new LootParams.Builder((ServerLevel) level)
//                                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
//                                .withParameter(LootContextParams.TOOL, player.getMainHandItem())
//                                .withParameter(LootContextParams.THIS_ENTITY, player))); // параметры для дропа
                        level.destroyBlock(newPos, true, player);
                    }
                }
            }
        }
    }

//    public static void autoSmelting(Level level, List<ItemStack> drops) {
//        RecipeManager recipes = level.getRecipeManager(); // тут лежат все крафты
//        for (ItemStack drop : drops) {
//            Optional<RecipeHolder<Recipe<RecipeInput>>> recipe = recipes.getRecipeFor(RecipeType.SMELTING, new SimpleContainer(drop), level);
//        }
//    }
}