package com.caleb.gemstonemod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record LightningStrikerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        if(pEnchantmentLevel == 1) {
            EntityType.LIGHTNING_BOLT.spawn(pLevel, pItem.owner().getMainHandItem(), (Player) pItem.owner(), pEntity.getOnPos(), MobSpawnType.TRIGGERED, false, false);
        }

        if(pEnchantmentLevel == 2) {
            EntityType.LIGHTNING_BOLT.spawn(pLevel, pItem.owner().getMainHandItem(), (Player) pItem.owner(), pEntity.getOnPos(), MobSpawnType.TRIGGERED, false, false);
            EntityType.LIGHTNING_BOLT.spawn(pLevel, pItem.owner().getMainHandItem(), (Player) pItem.owner(), pEntity.getOnPos(), MobSpawnType.TRIGGERED, false, false);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
