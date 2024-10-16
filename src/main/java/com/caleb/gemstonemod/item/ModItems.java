package com.caleb.gemstonemod.item;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.item.custom.*;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.regex.qual.Regex;

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
            () -> new AmberiteCatalystItem(new Item.Properties().durability(16)));
    public static final RegistryObject<Item> SAPHIRITE_CATALYST = ITEMS.register("saphirite_catalyst",
            () -> new SaphiriteCatalystItem(new Item.Properties().durability(16)));
    public static final RegistryObject<Item> AMBERITE_APPLE = ITEMS.register("amberite_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.AMBERITE_APPLE)));
    public static final RegistryObject<Item> SAPHIRITE_APPLE = ITEMS.register("saphirite_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SAPHIRITE_APPLE)));
    public static final RegistryObject<Item> AMBERITE_SWORD = ITEMS.register("amberite_sword",
            () -> new SwordItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 3, -2.4f))));
    public static final RegistryObject<Item> AMBERITE_PICKAXE = ITEMS.register("amberite_pickaxe",
            () -> new AmberitePickaxeItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 1, -2.8f))));
    public static final RegistryObject<Item> SAPHIRITE_SWORD = ITEMS.register("saphirite_sword",
            () -> new SaphiriteSwordItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.AMBERITE, 3, -2.4f))));
    public static final RegistryObject<Item> OPALITE_AXE = ITEMS.register("opalite_axe",
            () -> new OpaliteAxeItem(ModToolTiers.AMBERITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.AMBERITE, 5, -3))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
