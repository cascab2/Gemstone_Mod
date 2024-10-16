package com.caleb.gemstonemod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class OpaliteAxeItem extends AxeItem {
    public OpaliteAxeItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pAttacker.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1));
        return super.hurtEnemy(pStack, pTarget,pAttacker);
    }

}