package com.caleb.gemstonemod.datagen;

import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.AMBERITE_BLOCK.get());
        dropSelf(ModBlocks.SAPHIRITE_BLOCK.get());

        this.add(ModBlocks.AMBERITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_AMBERITE_ORE.get(), ModItems.RAW_AMBERITE.get()));
        this.add(ModBlocks.DEEPSLATE_AMBERITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_AMBERITE_ORE.get(), ModItems.RAW_AMBERITE.get()));
        this.add(ModBlocks.SAPHIRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.SAPHIRITE_ORE.get(), ModItems.RAW_SAPHIRITE.get()));
        this.add(ModBlocks.DEEPSLATE_SAPHIRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_SAPHIRITE_ORE.get(), ModItems.RAW_SAPHIRITE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
