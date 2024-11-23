package com.caleb.gemstonemod.util;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

public class ModItemProperties {
    public static void addCustomItemProperties () {
        ItemProperties.register(ModItems.OPALITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, "overclocked"),
                (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null ? 1f : 0f);

        makeCustomBow(ModItems.GEMSTONE_BOW.get());
    }

    private static void makeCustomBow(Item item) {
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (p_340951_, p_340952_, p_340953_, p_340954_) -> {
            if (p_340953_ == null) {
                return 0.0f;
            } else {
                return p_340953_.getUseItem() != p_340951_ ? 0.0f : (float)(p_340951_.getUseDuration(p_340953_) - p_340953_.getUseItemRemainingTicks()) / 20.0f;
            }
        });
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
            return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0f : 0.0f;
        });
    }
}
