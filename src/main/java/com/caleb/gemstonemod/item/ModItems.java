package com.caleb.gemstonemod.item;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.entity.ModEntities;
import com.caleb.gemstonemod.item.custom.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GemstoneMod.MOD_ID);

    public static final RegistryObject<Item> AMBERITE = ITEMS.register("amberite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_AMBERITE = ITEMS.register("raw_amberite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAPHIRITE = ITEMS.register("saphirite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPHIRITE = ITEMS.register("raw_saphirite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBERITE_CATALYST = ITEMS.register("amberite_catalyst",
            () -> new AmberiteCatalystItem(new Item.Properties().durability(10)));
    public static final RegistryObject<Item> SAPHIRITE_CATALYST = ITEMS.register("saphirite_catalyst",
            () -> new SaphiriteCatalystItem(new Item.Properties().durability(10)));
    public static final RegistryObject<Item> OPALITE_CATALYST = ITEMS.register("opalite_catalyst",
            () -> new OpaliteCatalystItem(new Item.Properties().durability(10)));
    public static final RegistryObject<Item> AMBERITE_APPLE = ITEMS.register("amberite_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.AMBERITE_APPLE)));
    public static final RegistryObject<Item> SAPHIRITE_APPLE = ITEMS.register("saphirite_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SAPHIRITE_APPLE)));
    public static final RegistryObject<Item> OPALITE_APPLE = ITEMS.register("opalite_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.OPALITE_APPLE)));
    public static final RegistryObject<Item> DIAMOND_APPLE = ITEMS.register("diamond_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DIAMOND_APPLE)));
    public static final RegistryObject<Item> AMBERITE_SWORD = ITEMS.register("amberite_sword",
            () -> new AmberiteSwordItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, -5, -2.4f))));
    public static final RegistryObject<Item> AMBERITE_PICKAXE = ITEMS.register("amberite_pickaxe",
            () -> new AmberitePickaxeItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 1, -2.8f))));
    public static final RegistryObject<Item> OPALITE_PICKAXE = ITEMS.register("opalite_pickaxe",
            () -> new OpalitePickaxeItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 1, -2.8f))));
    public static final RegistryObject<Item> SAPHIRITE_AXE = ITEMS.register("saphirite_axe",
            () -> new SaphiriteAxeItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 4, -3))));
    public static final RegistryObject<Item> SAPHIRITE_SWORD = ITEMS.register("saphirite_sword",
            () -> new SaphiriteSwordItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 3, -2.4f))));
    public static final RegistryObject<Item> OPALITE_AXE = ITEMS.register("opalite_axe",
            () -> new OpaliteAxeItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.AMBERITE, 5, -3))));
    public static final RegistryObject<Item> OPALITE = ITEMS.register("opalite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_OPALITE = ITEMS.register("raw_opalite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NIGHT_VISION_ITEM = ITEMS.register("night_vision_item",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAPHIRITE_PICKAXE = ITEMS.register("saphirite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPHIRITE_PICKAXE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SAPHIRITE_PICKAXE, 1, -2.8f))));
    public static final RegistryObject<Item> OPALITE_SWORD = ITEMS.register("opalite_sword",
            () -> new OpaliteSwordItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, -4, -3.6f))));
    public static final RegistryObject<Item> AMBERITE_AXE = ITEMS.register("amberite_axe",
            () -> new TridentItem(new Item.Properties()
                    .durability(3031)
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 5, -3f))
                    .component(DataComponents.TOOL, TridentItem.createToolProperties())));
    public static final RegistryObject<Item> GEMSTONE_HELMET = ITEMS.register("gemstone_helmet",
            () -> new ModArmorItem(ModArmorMaterials.GEMSTONE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(45))));
    public static final RegistryObject<Item> GEMSTONE_CHESTPLATE = ITEMS.register("gemstone_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.GEMSTONE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));
    public static final RegistryObject<Item> GEMSTONE_LEGGINGS = ITEMS.register("gemstone_leggings",
            () -> new ModArmorItem(ModArmorMaterials.GEMSTONE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));
    public static final RegistryObject<Item> GEMSTONE_BOOTS = ITEMS.register("gemstone_boots",
            () -> new ModArmorItem(ModArmorMaterials.GEMSTONE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    public static final RegistryObject<Item> GEMSTONE_ESSENCE = ITEMS.register("gemstone_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEMSTONE_BOW = ITEMS.register("gemstone_bow",
            () -> new GemstoneBowItem(new Item.Properties().durability(500)));
    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRICERATOPS_SPAWN_EGG = ITEMS.register("triceratops_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TRICERATOPS, 0x53524b, 0xdac741, new Item.Properties()));
    public static final RegistryObject<Item> KOHLRABI_SEEDS = ITEMS.register("kohlrabi_seeds",
            () -> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> TRICERATOPS_BONE = ITEMS.register("triceratops_bone",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEMSTONE_WINGS = ITEMS.register("gemstone_wings",
            () -> new ElytraItem(new Item.Properties().durability(864)));
    public static final RegistryObject<Item> ANDESITE_SINGULARITY = ITEMS.register("andesite_singularity",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRANITE_SINGULARITY = ITEMS.register("granite_singularity",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIORITE_SINGULARITY = ITEMS.register("diorite_singularity",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROCK_SINGULARITY = ITEMS.register("rock_singularity",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SWIFTNESS_SINGULARITY = ITEMS.register("swiftness_singularity",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCALE_SINGULARITY = ITEMS.register("scale_singularity",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PEARL_ON_A_STICK = ITEMS.register("pearl_on_a_stick",
            () -> new PearlOnAStickItem(new Item.Properties().durability(512)));
    public static final RegistryObject<Item> GEMSTONE_LOCATOR = ITEMS.register("gemstone_locator",
            () -> new GemstoneLocatorItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
