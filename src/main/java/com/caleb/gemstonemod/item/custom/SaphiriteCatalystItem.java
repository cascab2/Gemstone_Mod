package com.caleb.gemstonemod.item.custom;

import com.caleb.gemstonemod.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class SaphiriteCatalystItem extends Item {
    private static final Map<Block, Block> SAPHIRITE_CATALYST_MAP =
            Map.of(
                    ModBlocks.SAPHIRITE_ORE.get(), ModBlocks.SAPHIRITE_BLOCK.get(),
                    ModBlocks.DEEPSLATE_SAPHIRITE_ORE.get(), ModBlocks.SAPHIRITE_BLOCK.get()
            );

    public SaphiriteCatalystItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(SAPHIRITE_CATALYST_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(), SAPHIRITE_CATALYST_MAP.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return super.useOn(pContext);
    }
}
