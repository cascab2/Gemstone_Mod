package com.caleb.gemstonemod.item.custom;

import com.caleb.gemstonemod.block.ModBlocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class AmberiteCatalystItem extends Item {
    private static final Map<Block, Block> AMBERITE_CATALYST_MAP =
            Map.of(
                    ModBlocks.AMBERITE_ORE, ModBlocks.AMBERITE_BLOCK,
                    ModBlocks.DEEPSLATE_AMBERITE_ORE, ModBlocks.AMBERITE_BLOCK.get()
            );

    public AmberiteCatalystItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();



        return super.useOn(pContext);
    }
}
