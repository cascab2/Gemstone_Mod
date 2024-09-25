package com.caleb.gemstonemod.item;

import com.caleb.gemstonemod.GemstoneMod;
import net.minecraft.world.item.Item;
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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
