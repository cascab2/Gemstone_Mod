package com.caleb.gemstonemod.item.custom;

import com.caleb.gemstonemod.item.ModToolTiers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class OpaliteSwordItem extends SwordItem {
    public OpaliteSwordItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pAttacker.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(pAttacker.getAttributeBaseValue(Attributes.ATTACK_SPEED) + 0.075f);
        if (pAttacker.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() <= 13) {
            pAttacker.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(pAttacker.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) + 0.25f);
        }
        return super.hurtEnemy(pStack, pTarget,pAttacker);
    }

}