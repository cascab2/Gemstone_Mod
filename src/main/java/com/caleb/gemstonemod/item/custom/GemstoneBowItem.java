package com.caleb.gemstonemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class GemstoneBowItem extends BowItem {

    public GemstoneBowItem(Properties pProperties) {super(pProperties);}
    @Override
    protected void shootProjectile(
            LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @Nullable LivingEntity pTarget
    ) {
        pProjectile.shootFromRotation(pShooter, pShooter.getXRot(), pShooter.getYRot() + pAngle, 0.0F, pVelocity, pInaccuracy);
        if (pTarget != null) {
            BlockPos pos = pTarget.blockPosition();
            Level world = pTarget.level();
            world.explode(pShooter, null, null, pos.getX(), pos.getY(), pos.getZ(), 1.7f, false, Level.ExplosionInteraction.MOB);
        }
    }

}