package com.caleb.gemstonemod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record HealthyEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<HealthyEnchantmentEffect> CODEC = MapCodec.unit(HealthyEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        Player player = (Player) pEntity;

        if(pEnchantmentLevel == 1) {
            player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 100, 0));
        }

        if(pEnchantmentLevel == 2) {
            player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 100, 1));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
