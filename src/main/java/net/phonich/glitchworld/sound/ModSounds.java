package net.phonich.glitchworld.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phonich.glitchworld.Glitchworld;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Glitchworld.MODID);

    public static final Supplier<SoundEvent> GLITCH_SWORD_SHOOT = registerSoundEvent("glitch_sword_shoot");

    public static final Supplier<SoundEvent> GLITCH_CONVERTER_BREAK = registerSoundEvent("glitch_converter_break");
    public static final Supplier<SoundEvent> GLITCH_CONVERTER_PLACE = registerSoundEvent("glitch_converter_place");
    public static final Supplier<SoundEvent> GLITCH_CONVERTER_STEP = registerSoundEvent("glitch_converter_step");
    public static final Supplier<SoundEvent> GLITCH_CONVERTER_HIT = registerSoundEvent("glitch_converter_hit");
    public static final Supplier<SoundEvent> GLITCH_CONVERTER_FALL = registerSoundEvent("glitch_converter_fall");

    public static final Supplier<SoundEvent> BIG_DICK_CHANGE = registerSoundEvent("big_dick_change");
    public static final Supplier<SoundEvent> GLITCH_POUCH_OPEN = registerSoundEvent("glitch_pouch_open");
    public static final Supplier<SoundEvent> HAMMER_USE = registerSoundEvent("hammer_use");
    public static final Supplier<SoundEvent> GLITCH_UPGRADER_FAILURE = registerSoundEvent("glitch_upgrader_failure");
    public static final Supplier<SoundEvent> GLITCH_UPGRADER_SUCCESS = registerSoundEvent("glitch_upgrader_success");
    public static final Supplier<SoundEvent> GLITCH_CONVERTER_CONVERT = registerSoundEvent("glitch_converter_convert");

    public static final Supplier<SoundEvent> SCARY_GLITCH = registerSoundEvent("scary_glitch");




    public static final DeferredSoundType GLITCH_CONVERTER_SOUNDS = new DeferredSoundType(1f, 1f,
            ModSounds.GLITCH_CONVERTER_BREAK, ModSounds.GLITCH_CONVERTER_STEP , ModSounds.GLITCH_CONVERTER_PLACE, ModSounds.GLITCH_CONVERTER_HIT, ModSounds.GLITCH_CONVERTER_FALL);


    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Glitchworld.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
