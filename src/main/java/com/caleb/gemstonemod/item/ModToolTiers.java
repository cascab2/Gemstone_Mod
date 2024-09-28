package com.caleb.gemstonemod.item;

import com.caleb.gemstonemod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier AMBERITE = new ForgeTier(3031, 10.0f, 5.0f, 15,
            ModTags.Blocks.NEEDS_MOD_TOOL, () -> Ingredient.of(ModItems.AMBERITE.get()),
            ModTags.Blocks.INCORRECT_FOR_MOD_TOOL);
}
