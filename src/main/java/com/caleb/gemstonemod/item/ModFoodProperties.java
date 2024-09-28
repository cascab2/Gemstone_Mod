package com.caleb.gemstonemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties AMBERITE_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600, 2), 1.0f).alwaysEdible().build();
    public static final FoodProperties SAPHIRITE_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,600, 1), 1.0f).alwaysEdible().build();
}
