package com.caleb.gemstonemod.util;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

public class ModItemProperties {
    public static void addCustomItemProperties () {
        ItemProperties.register(ModItems.OPALITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, "overclocked"),
                (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null ? 1f : 0f);
    }

}
