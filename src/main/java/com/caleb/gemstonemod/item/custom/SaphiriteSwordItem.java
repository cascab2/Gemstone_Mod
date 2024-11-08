package com.caleb.gemstonemod.item.custom;

import com.caleb.gemstonemod.event.ModEvents;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class SaphiriteSwordItem extends SwordItem {
    public SaphiriteSwordItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 2));
        return super.hurtEnemy(pStack, pTarget,pAttacker);
    }

}