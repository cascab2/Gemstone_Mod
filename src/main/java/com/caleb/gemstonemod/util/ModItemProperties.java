package com.caleb.gemstonemod.util;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.item.ModItems;
import com.caleb.gemstonemod.item.custom.GemstoneLocatorItem;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;

public class ModItemProperties {
    public static void addCustomItemProperties () {
        ItemProperties.register(ModItems.OPALITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, "overclocked"),
                (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null ? 1f : 0f);
        ItemProperties.register(ModItems.GEMSTONE_LOCATOR.get(), ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, "angled"),
                (itemStack, clientLevel, livingEntity, i) -> {
                    if (itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null) {
                        BlockPos targetPos = itemStack.get(ModDataComponentTypes.COORDINATES.get());

// Vector from player to target (XZ-plane only)
                        Vec3 toTarget = new Vec3(
                                targetPos.getX() + 0.5 - livingEntity.getX(),
                                0,
                                targetPos.getZ() + 0.5 - livingEntity.getZ()
                        ).normalize();

// Player's current facing direction (XZ-plane only)
                        Vec3 look = livingEntity.getLookAngle();
                        Vec3 lookFlat = new Vec3(look.x, 0, look.z).normalize();

// Angle between vectors (signed)
                        double dot = lookFlat.dot(toTarget);
                        double det = lookFlat.x * toTarget.z - lookFlat.z * toTarget.x; // cross product Z
                        double angle = Math.atan2(det, dot); // returns angle in radians (-π to π)

// Normalize to [0, 1) — and shift so 0.0 means "straight ahead"
                        angle = (angle / (2 * Math.PI) + 1) % 1.0;

// Rotate so 0.0 = South, if needed — only if your model expects it
// angle = (angle + 0.25) % 1.0;

                        int steps = 32;
                        float stepSize = 1.0f / steps;
                        int index = Math.floorMod(Math.round(angle * steps), steps);
                        return index * stepSize;
                    }
                    return 0f;
        });

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
