package net.phonich.glitchworld.util;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.phonich.glitchworld.Glitchworld;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Glitchworld.MODID);

    public static final Supplier<AttachmentType<Integer>> CORRUPTION = ATTACHMENTS.register(
            "corruption", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT.fieldOf("corruption").codec()).build()
    );

}
