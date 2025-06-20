package com.caleb.gemstonemod.villager;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.Immutable;
import com.ibm.icu.impl.locale.XCldrStub;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, GemstoneMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, GemstoneMod.MOD_ID);

    public static final RegistryObject<PoiType> END_POI = POI_TYPES.register("end_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.COOL_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> END_VILLAGER = VILLAGER_PROFESSIONS.register("end_villager",
            () -> new VillagerProfession("end_villager", holder -> holder.value() == END_POI.get(),
                    holder -> holder.value() == END_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.AMETHYST_BLOCK_CHIME));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
