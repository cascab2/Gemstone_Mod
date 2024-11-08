package com.caleb.gemstonemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties AMBERITE_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 1), 1.0f).alwaysEdible().build();
    public static final FoodProperties SAPHIRITE_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,600, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 1), 1.0f).alwaysEdible().build();
    public static final FoodProperties OPALITE_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,600, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 1), 1.0f).alwaysEdible().build();
    public static final FoodProperties DIAMOND_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,2400, 2), 1.0f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 2400, 2), 1.0f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 2), 1.0f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 2400, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2400, 1), 1.0f)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 1), 1.0f).alwaysEdible().build();
}
