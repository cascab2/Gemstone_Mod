package com.caleb.gemstonemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;

public class AmberiteSwordItem extends SwordItem {

    public AmberiteSwordItem(Tier pTier, Properties pProperties) {super(pTier, pProperties);}
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        BlockPos pos = pTarget.blockPosition();
        Level world = pTarget.level();
        world.explode(pAttacker, null, null, pos.getX(), pos.getY(), pos.getZ(), 1.7f, false, Level.ExplosionInteraction.MOB);

        return super.hurtEnemy(pStack, pTarget,pAttacker);
    }

}