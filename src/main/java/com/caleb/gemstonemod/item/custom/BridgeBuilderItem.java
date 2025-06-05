package com.caleb.gemstonemod.item.custom;

import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class BridgeBuilderItem extends CompassItem {

    public BridgeBuilderItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        InteractionHand pUsedHand = pContext.getHand();
        Level pLevel = pContext.getLevel();
        if (player.getMainHandItem().getItem() != null && player.getOffhandItem().getItem() != null) {
            Item mhi = player.getMainHandItem().getItem();
            Item ohi = player.getOffhandItem().getItem();
            float yaw = player.getYHeadRot() % 360;
            String direction;
            if (yaw < 0) yaw += 360;

            if (yaw >= 315 || yaw < 45) {
                direction = "South";
            } else if (yaw >= 45 && yaw < 135) {
                direction = "West";
            } else if (yaw >= 135 && yaw < 225) {
                direction = "North";
            } else {
                direction = "East";
            }
            BlockPos pos = pContext.getClickedPos();
            if (mhi.equals(ModItems.BRIDGE_BUILDER.get())) {
                if (ohi.equals(Blocks.COBBLESTONE.asItem())) {
                    if (direction.equals("North")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.north(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getOffhandItem().getCount() > 0) {
                                player.getOffhandItem().setCount(player.getOffhandItem().getCount() - 1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    if (direction.equals("South")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.south(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getOffhandItem().getCount() > 0) {
                                player.getOffhandItem().shrink(1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    if (direction.equals("East")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.east(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getOffhandItem().getCount() > 0) {
                                player.getOffhandItem().shrink(1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    if (direction.equals("West")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.west(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getOffhandItem().getCount() > 0) {
                                player.getOffhandItem().shrink(1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                } else {
                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                }
            } else {
                if (mhi.equals(Blocks.COBBLESTONE.asItem())) {
                    if (direction.equals("North")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.north(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getMainHandItem().getCount() > 0) {
                                player.getMainHandItem().shrink(1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    if (direction.equals("South")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.south(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getMainHandItem().getCount() > 0) {
                                player.getMainHandItem().shrink(1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    if (direction.equals("East")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.east(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getMainHandItem().getCount() > 0) {
                                player.getMainHandItem().shrink(1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    if (direction.equals("West")) {
                        for (int i = 0; i < 30; i++) {
                            pos = pos.west(1);
                            if (pLevel.getBlockState(pos).getBlock().equals(Blocks.AIR) && player.getMainHandItem().getCount() > 0) {
                                player.getMainHandItem().shrink(1);
                                pLevel.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 0);
                            }
                        }
                    }
                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                } else {
                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                }
            }
        } else {
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }
}
