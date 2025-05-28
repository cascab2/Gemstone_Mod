package com.caleb.gemstonemod.util;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;

public class ModItemProperties {
    public static void addCustomItemProperties () {
        ItemProperties.register(ModItems.OPALITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, "overclocked"),
                (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null ? 1f : 0f);
        ItemProperties.register(ModItems.GEMSTONE_LEGGINGS.get(), ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, "located"),
                (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null ? 1f : 0f);

        makeCustomBow(ModItems.GEMSTONE_BOW.get());
        makeCustomRod(ModItems.PEARL_ON_A_STICK.get());
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
    private static void makeCustomRod(Item item) {
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("cast"), (p_174585_, p_174586_, p_174587_, p_174588_) -> {
            if (p_174587_ == null) {
                return 0.0F;
            } else {
                boolean flag = p_174587_.getMainHandItem() == p_174585_;
                boolean flag1 = p_174587_.getOffhandItem() == p_174585_;
                if (p_174587_.getMainHandItem().getItem() instanceof FishingRodItem) {
                    flag1 = false;
                }

                return (flag || flag1) && p_174587_ instanceof Player && ((Player)p_174587_).fishing != null ? 1.0F : 0.0F;
            }
        });
    }
}
