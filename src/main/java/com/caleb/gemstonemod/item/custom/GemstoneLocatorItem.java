package com.caleb.gemstonemod.item.custom;

import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GemstoneLocatorItem extends CompassItem {

    public GemstoneLocatorItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        InteractionHand pUsedHand = pContext.getHand();
        Level pLevel = pContext.getLevel();
        boolean found = false;
        BlockPos pos = null;
        if (pUsedHand.equals(InteractionHand.MAIN_HAND) && pPlayer.getItemInHand(InteractionHand.OFF_HAND).getItem().equals(Items.AMETHYST_SHARD)) {
            found = false;
            for (int x = pPlayer.getBlockX() - 30; x <= pPlayer.getBlockX() + 30; x++) {
                for (int y = pPlayer.getBlockY() - 30; y <= pPlayer.getBlockY() + 30; y++) {
                    for (int z = pPlayer.getBlockZ() - 30; z <= pPlayer.getBlockZ() + 30; z++) {
                        BlockPos pos1 = new BlockPos(x, y, z);
                        if (pLevel.getBlockState(pos1).getBlock().equals(ModBlocks.AMBERITE_ORE.get()) ||
                                pLevel.getBlockState(pos1).getBlock().equals(ModBlocks.DEEPSLATE_AMBERITE_ORE.get()) ||
                                pLevel.getBlockState(pos1).getBlock().equals(ModBlocks.SAPHIRITE_ORE.get()) ||
                                pLevel.getBlockState(pos1).getBlock().equals(ModBlocks.DEEPSLATE_SAPHIRITE_ORE.get()) ||
                                pLevel.getBlockState(pos1).getBlock().equals(ModBlocks.OPALITE_ORE.get()) ||
                                pLevel.getBlockState(pos1).getBlock().equals(ModBlocks.DEEPSLATE_OPALITE_ORE.get())) {
                            found = true;
                            pos = pos1;
                            pLevel.playSound(pPlayer, pPlayer.getOnPos(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS);
                        }
                    }
                }
            }
        }
        if (pos == null) {
            found = false;
        }
        if (found) {
            pPlayer.getItemInHand(pUsedHand).set(ModDataComponentTypes.COORDINATES.get(), pos);
            pPlayer.getItemInHand(InteractionHand.OFF_HAND).shrink(1);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pToolTipFlag) {
        if (pStack.get(ModDataComponentTypes.COORDINATES.get()) != null) {
            pTooltipComponents.add(Component.literal("Ore Located at " + ("" + pStack.get(ModDataComponentTypes.COORDINATES.get())).substring(9, ("" + pStack.get(ModDataComponentTypes.COORDINATES.get())).length() - 1)).withColor(0x80FF20));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pToolTipFlag);
    }
}
