package com.caleb.gemstonemod.enchantment;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.enchantment.custom.AttackSpeedEnchantmentEffect;
import com.caleb.gemstonemod.enchantment.custom.HealthyEnchantmentEffect;
import com.caleb.gemstonemod.enchantment.custom.LightningStrikerEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, GemstoneMod.MOD_ID);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
            ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchantmentEffect.CODEC);
    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> HEALTHY =
            ENTITY_ENCHANTMENT_EFFECTS.register("healthy", () -> HealthyEnchantmentEffect.CODEC);
    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> ATTACK_SPEED =
            ENTITY_ENCHANTMENT_EFFECTS.register("attack_speed", () -> AttackSpeedEnchantmentEffect.CODEC);
    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> CRITICAL =
            ENTITY_ENCHANTMENT_EFFECTS.register("critical", () -> AttackSpeedEnchantmentEffect.CODEC);


    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }

}
