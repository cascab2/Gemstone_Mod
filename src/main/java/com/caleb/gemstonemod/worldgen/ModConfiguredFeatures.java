package com.caleb.gemstonemod.worldgen;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.block.ModBlocks;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_AMBERITE_ORE_KEY = registerKey("amberite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPHIRITE_ORE_KEY = registerKey("saphirite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_OPALITE_ORE_KEY = registerKey("opalite_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> amberiteOres = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.AMBERITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_AMBERITE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> saphiriteOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.SAPHIRITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_SAPHIRITE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> opaliteOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.OPALITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_OPALITE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_AMBERITE_ORE_KEY, Feature.ORE, new OreConfiguration(amberiteOres, 2));
        register(context, OVERWORLD_SAPHIRITE_ORE_KEY, Feature.ORE, new OreConfiguration(saphiriteOres, 2));
        register(context, OVERWORLD_OPALITE_ORE_KEY, Feature.ORE, new OreConfiguration(opaliteOres, 2));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(GemstoneMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
