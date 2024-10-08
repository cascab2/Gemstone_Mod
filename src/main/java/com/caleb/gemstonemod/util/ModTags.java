package com.caleb.gemstonemod.util;

import com.caleb.gemstonemod.GemstoneMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> NEEDS_MOD_TOOL = createTag("needs_mod_tool");
        public static final TagKey<Block> INCORRECT_FOR_MOD_TOOL = createTag("incorrect_for_mod_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> AMBERITE_TOOLS = createTag("amberite_tools");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, name));
        }
    }
}
