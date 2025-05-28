package com.caleb.gemstonemod.item.custom;

import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;

public class GemstoneLocatorItem extends Item {

    public GemstoneLocatorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        boolean found = false;
        if (pUsedHand.equals(InteractionHand.MAIN_HAND) && pPlayer.getItemInHand(InteractionHand.OFF_HAND).getItem().equals(Items.AMETHYST_SHARD)) {
            found = false;
            for (int x = pPlayer.getBlockX() - 30; x <= pPlayer.getBlockX() + 30; x++) {
                for (int y = pPlayer.getBlockY() - 30; y <= pPlayer.getBlockY() + 30; y++) {
                    for (int z = pPlayer.getBlockZ() - 30; z <= pPlayer.getBlockZ() + 30; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (pLevel.getBlockState(pos).getBlock().equals(ModBlocks.AMBERITE_ORE.get()) ||
                                pLevel.getBlockState(pos).getBlock().equals(ModBlocks.DEEPSLATE_AMBERITE_ORE.get()) ||
                                pLevel.getBlockState(pos).getBlock().equals(ModBlocks.SAPHIRITE_ORE.get()) ||
                                pLevel.getBlockState(pos).getBlock().equals(ModBlocks.DEEPSLATE_SAPHIRITE_ORE.get()) ||
                                pLevel.getBlockState(pos).getBlock().equals(ModBlocks.OPALITE_ORE.get()) ||
                                pLevel.getBlockState(pos).getBlock().equals(ModBlocks.DEEPSLATE_OPALITE_ORE.get())) {
                            pPlayer.getItemInHand(pUsedHand).set(ModDataComponentTypes.COORDINATES.get(), pos);
                            found = true;
                            pLevel.playSound(pPlayer, pPlayer.getOnPos(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS);
                        }
                    }
                }
            }
        }
        if (found) {
            pPlayer.getItemInHand(InteractionHand.OFF_HAND).shrink(1);
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
        } else {
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pToolTipFlag) {
        if (pStack.get(ModDataComponentTypes.COORDINATES.get()) != null) {
            pTooltipComponents.add(Component.literal("Ore Located at " + ("" + pStack.get(ModDataComponentTypes.COORDINATES.get())).substring(9, ("" + pStack.get(ModDataComponentTypes.COORDINATES.get())).length() - 1)));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pToolTipFlag);
    }
}
