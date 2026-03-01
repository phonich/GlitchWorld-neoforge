package net.phonich.glitchworld;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.component.FireworkExplosion;

import java.util.ArrayList;
import java.util.List;

public class FireworkExps {
    public static List<FireworkExplosion> getFireworkConf() {
        IntList intList = new IntArrayList();
            intList.add(DyeColor.RED.getFireworkColor());
            intList.add(DyeColor.GREEN.getFireworkColor());
        FireworkExplosion exp1 = new FireworkExplosion(FireworkExplosion.Shape.LARGE_BALL, intList, null, true,true);
        List<FireworkExplosion> exp1list = new ArrayList<>();
        exp1list.add(exp1);
    return exp1list;
    }
}
