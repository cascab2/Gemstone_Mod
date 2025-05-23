package com.caleb.gemstonemod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record AttackSpeedEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AttackSpeedEnchantmentEffect> CODEC = MapCodec.unit(AttackSpeedEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        Player player = (Player) pEntity;

        if(pEnchantmentLevel == 1) {
            player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(4.25);
        }

        if(pEnchantmentLevel == 2) {
            player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(4.5);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
