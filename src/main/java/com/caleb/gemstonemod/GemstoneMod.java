package com.caleb.gemstonemod;

import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.enchantment.ModEnchantmentEffects;
import com.caleb.gemstonemod.item.ModItems;
import com.caleb.gemstonemod.util.ModItemProperties;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GemstoneMod.MOD_ID)
public class GemstoneMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "gemstonemod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public GemstoneMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModDataComponentTypes.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.AMBERITE);
            event.accept(ModItems.RAW_AMBERITE);
            event.accept(ModItems.SAPHIRITE);
            event.accept(ModItems.RAW_SAPHIRITE);
            event.accept(ModItems.OPALITE);
            event.accept(ModItems.RAW_OPALITE);
            event.accept(ModItems.GEMSTONE_ESSENCE);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.AMBERITE_BLOCK);
            event.accept(ModBlocks.SAPHIRITE_BLOCK);
            event.accept(ModBlocks.OPALITE_BLOCK);

        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.AMBERITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_AMBERITE_ORE);
            event.accept(ModBlocks.SAPHIRITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_SAPHIRITE_ORE);
            event.accept(ModBlocks.OPALITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_OPALITE_ORE);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.AMBERITE_CATALYST);
            event.accept(ModItems.SAPHIRITE_CATALYST);
            event.accept(ModItems.AMBERITE_PICKAXE);
            event.accept(ModItems.OPALITE_CATALYST);
            event.accept(ModItems.OPALITE_PICKAXE);
            event.accept(ModItems.SAPHIRITE_AXE);
            event.accept(ModItems.SAPHIRITE_PICKAXE);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.AMBERITE_APPLE);
            event.accept(ModItems.SAPHIRITE_APPLE);
            event.accept(ModItems.OPALITE_APPLE);
            event.accept(ModItems.DIAMOND_APPLE);
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.AMBERITE_SWORD);
            event.accept(ModItems.SAPHIRITE_SWORD);
            event.accept(ModItems.OPALITE_AXE);
            event.accept(ModItems.OPALITE_SWORD);
            event.accept(ModItems.AMBERITE_AXE);
            event.accept(ModItems.GEMSTONE_HELMET);
            event.accept(ModItems.GEMSTONE_CHESTPLATE);
            event.accept(ModItems.GEMSTONE_LEGGINGS);
            event.accept(ModItems.GEMSTONE_BOOTS);
        }



    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();
        }
    }
}
