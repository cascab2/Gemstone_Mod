package com.caleb.gemstonemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class SaphiriteAxeItem extends DiggerItem {
    public SaphiriteAxeItem(Tier pTier, Properties pProperties) {
        super(pTier, BlockTags.MINEABLE_WITH_AXE, pProperties);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if(traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }



        if(traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH ||
                traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST ||
                traceResult.getDirection() == Direction.UP || traceResult.getDirection() == Direction.DOWN) {
            for(int x = 1; x <= range; x++) {
                positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + x, initalBlockPos.getZ()));
            }
        }



        return positions;
    }
}
