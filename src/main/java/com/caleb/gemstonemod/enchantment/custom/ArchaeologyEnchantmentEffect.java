package com.caleb.gemstonemod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ArchaeologyEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ArchaeologyEnchantmentEffect> CODEC = MapCodec.unit(ArchaeologyEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        Player player = (Player) pEntity;
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
