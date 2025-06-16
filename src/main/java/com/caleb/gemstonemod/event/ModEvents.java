package com.caleb.gemstonemod.event;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.component.ModDataComponentTypes;
import com.caleb.gemstonemod.enchantment.ModEnchantments;
import com.caleb.gemstonemod.entity.ModEntities;
import com.caleb.gemstonemod.entity.custom.TriceratopsEntity;
import com.caleb.gemstonemod.item.ModItems;
import com.caleb.gemstonemod.item.custom.AmberitePickaxeItem;
import com.caleb.gemstonemod.item.custom.BridgeBuilderItem;
import com.caleb.gemstonemod.item.custom.OpalitePickaxeItem;
import com.caleb.gemstonemod.item.custom.SaphiriteAxeItem;
import com.caleb.gemstonemod.villager.ModVillagers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.VillagerTradingManager;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = GemstoneMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onAmberitePickaxeUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof AmberitePickaxeItem amberitePickaxe && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : AmberitePickaxeItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !amberitePickaxe.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
    @SubscribeEvent
    public static void onOpalitePickaxeUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof OpalitePickaxeItem opalitePickaxe && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : OpalitePickaxeItem.getBlocksToBeDestroyed(4, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !opalitePickaxe.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
    @SubscribeEvent
    public static void onSaphiriteAxeUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof SaphiriteAxeItem saphiriteAxe && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : SaphiriteAxeItem.getBlocksToBeDestroyed(100, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !saphiriteAxe.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
    @SubscribeEvent
    public static void onItemHeldInOffhand (PlayerEvent.ItemPickupEvent event) {
        Player player = event.getEntity();
        ItemStack offHandItem = player.getOffhandItem();
        if (player instanceof ServerPlayer && offHandItem.getItem().equals(ModItems.NIGHT_VISION_ITEM.get())) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 1));
        }
    }
    @SubscribeEvent
    public static void onOpaliteSwordNotHeld (LivingEvent.LivingTickEvent event) {
        ItemStack mainHandItem = event.getEntity().getMainHandItem();
        ItemStack offHandItem = event.getEntity().getOffhandItem();
        if (event.getEntity() instanceof ServerPlayer && event.getEntity() instanceof Player) {
            if (!(mainHandItem.getItem().equals(ModItems.OPALITE_SWORD.get()) || offHandItem.getItem().equals(ModItems.OPALITE_SWORD.get()))) {
                event.getEntity().getAttribute(Attributes.ATTACK_SPEED)
                        .setBaseValue(4.0f);
                event.getEntity().getAttribute(Attributes.ATTACK_DAMAGE)
                        .setBaseValue(1.0f);
            }
        }
        if (event.getEntity() instanceof ServerPlayer && ((Player) event.getEntity()).getItemBySlot(EquipmentSlot.FEET).getItem().equals(ModItems.GEMSTONE_BOOTS.get()) && ((Player) event.getEntity()).getOffhandItem().getItem().equals(ModItems.SWIFTNESS_SINGULARITY.get())) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 2, false, false, false));
        }
    }
    @SubscribeEvent
    public static void onGemstoneBowHit (ProjectileImpactEvent event) {
        Entity target = event.getEntity();
        BlockPos pos = target.blockPosition();
        Level world = target.level();
        Entity shooter = event.getProjectile().getOwner();

        if (shooter instanceof ServerPlayer serverPlayer) {
            ItemStack item = shooter.getWeaponItem();
            if (item.getItem().equals(ModItems.GEMSTONE_BOW.get())) {
                world.explode(shooter, null, null, pos.getX(), pos.getY(), pos.getZ(), 1.7f, false, Level.ExplosionInteraction.MOB);
                event.getProjectile().discard();
            }
        }
    }
    public static void setTimeOfDay(Level level, long time) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.setDayTime(time);
        }
    }
    @SubscribeEvent
    public static void onSunBroken(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof ServerPlayer && event.getState().getBlock() == ModBlocks.SUN.get()) {
            Level world = event.getPlayer().level();
            setTimeOfDay(world, 14000);
        }
    }
    @SubscribeEvent
    public static void onMoonBroken(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof ServerPlayer && event.getState().getBlock() == ModBlocks.MOON.get()) {
            Level world = event.getPlayer().level();
            setTimeOfDay(world, 1000);
        }
    }
    @SubscribeEvent
    public static void onRainBroken(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof ServerPlayer && event.getLevel() instanceof ServerLevel && event.getState().getBlock() == ModBlocks.RAIN.get()) {
            ServerLevel world = ((ServerPlayer) event.getPlayer()).serverLevel();
            world.setWeatherParameters(6000, 0, false, false);
        }
    }
    @SubscribeEvent
    public static void onCloudBroken(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof ServerPlayer && event.getLevel() instanceof ServerLevel && event.getState().getBlock() == ModBlocks.CLOUD.get()) {
            ServerLevel world = ((ServerPlayer) event.getPlayer()).serverLevel();
            world.setWeatherParameters(0, 6000, true, false);
        }
    }
    @SubscribeEvent
    public static void wings(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() != null) {
            if (event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ModItems.GEMSTONE_WINGS.get())) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 1, false, false));
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 1, false, false));
            }
            if (event.getEntity().getAttribute(Attributes.SCALE).getBaseValue() == 2) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 2, false, false));
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 2, false, false));
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 1, false, false));
            }
            if (event.getEntity().getAttribute(Attributes.SCALE).getBaseValue() == 0.5) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0, false, false));
                if (event.getEntity().getEffect(MobEffects.NIGHT_VISION) != null) {
                    if (event.getEntity().getEffect(MobEffects.NIGHT_VISION).getDuration() <= 201) {
                        event.getEntity().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
                    }
                } else {
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
                }
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0, false, false));
            }
            if (!event.getEntity().getOffhandItem().getItem().equals(ModItems.SCALE_SINGULARITY.get())) {
                event.getEntity().getAttribute(Attributes.SCALE).setBaseValue(1);
            }
            Registry<Enchantment> enchantmentRegistry = event.getEntity().level().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            Enchantment enchantment = enchantmentRegistry.get(ModEnchantments.ATTACK_SPEED);
            if (enchantment == null && event.getEntity().getAttribute(Attributes.ATTACK_SPEED) != null) {
                event.getEntity().getAttribute(Attributes.ATTACK_SPEED).setBaseValue(4);
            }
        }
    }
    @SubscribeEvent
    public static void jump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof ServerPlayer && event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem().equals(ModItems.GEMSTONE_BOOTS.get()) && event.getEntity().isCrouching() && event.getEntity().getItemInHand(InteractionHand.OFF_HAND).getItem().equals(ModItems.SWIFTNESS_SINGULARITY.get())) {
            Level world = event.getEntity().level();
            WindCharge windCharge = new WindCharge(world, event.getEntity().getX(), event.getEntity().getY() + 0.07, event.getEntity().getZ(), Vec3.directionFromRotation(90, 0));
            BlockPos pos = new BlockPos(event.getEntity().getBlockX(), event.getEntity().getBlockY() - 1, event.getEntity().getBlockZ());
            world.addFreshEntity(windCharge);
        }
    }
    @SubscribeEvent
    public static void graniteBroken(BlockEvent.BreakEvent event) {
        Registry<Enchantment> enchantmentRegistry = event.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Enchantment enchantment = enchantmentRegistry.get(ModEnchantments.ARCHAEOLOGY);
        if (event.getState().getBlock().equals(Blocks.GRANITE) && enchantment != null) {
            Level world = event.getPlayer().level();
            double rand = Math.random();
            if (rand < 0.15) {
                world.addFreshEntity(new ItemEntity(world, (double) event.getPos().getX(), (double) event.getPos().getY(), (double) event.getPos().getZ(), new ItemStack(ModItems.GRANITE_SINGULARITY.get())));
            }
        }
    }
    @SubscribeEvent
    public static void dioriteBroken(BlockEvent.BreakEvent event) {
        Registry<Enchantment> enchantmentRegistry = event.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Enchantment enchantment = enchantmentRegistry.get(ModEnchantments.ARCHAEOLOGY);
        if (event.getState().getBlock().equals(Blocks.DIORITE) && enchantment != null) {
            Level world = event.getPlayer().level();
            double rand = Math.random();
            if (rand < 0.15) {
                world.addFreshEntity(new ItemEntity(world, (double) event.getPos().getX(), (double) event.getPos().getY(), (double) event.getPos().getZ(), new ItemStack(ModItems.DIORITE_SINGULARITY.get())));
            }
        }
    }
    @SubscribeEvent
    public static void andesiteBroken(BlockEvent.BreakEvent event) {
        Registry<Enchantment> enchantmentRegistry = event.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Enchantment enchantment = enchantmentRegistry.get(ModEnchantments.ARCHAEOLOGY);
        if (event.getState().getBlock().equals(Blocks.ANDESITE) && enchantment != null) {
            Level world = event.getPlayer().level();
            double rand = Math.random();
            if (rand < 0.15) {
                world.addFreshEntity(new ItemEntity(world, (double) event.getPos().getX(), (double) event.getPos().getY(), (double) event.getPos().getZ(), new ItemStack(ModItems.ANDESITE_SINGULARITY.get())));
            }
        }
    }
    static int num = 0;
    @SubscribeEvent
    public static void jumpSize(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem().equals(ModItems.GEMSTONE_LEGGINGS.get()) && event.getEntity().isCrouching() && event.getEntity().getItemInHand(InteractionHand.OFF_HAND).getItem().equals(ModItems.SCALE_SINGULARITY.get())) {
            switch (num) {
               case 0 -> {
                   player.getAttribute(Attributes.SCALE).setBaseValue(2);
                   num++;
               }
               case 1 -> {
                   player.getAttribute(Attributes.SCALE).setBaseValue(0.5);
                   num++;
               }
               case 2 -> {
                   player.getAttribute(Attributes.SCALE).setBaseValue(1);
                   num = 0;
               }
           }
        }
    }
    @SubscribeEvent
    public static void dragon(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof EnderDragon && event.getEntity() instanceof ServerPlayer) {
            ((EnderDragon) event.getSource().getEntity()).heal(2);
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 1));
        }
    }
    @SubscribeEvent
    public static void endBed(BlockEvent.EntityPlaceEvent event) {
        if (event.getState().getBlock().equals(Blocks.BROWN_BED) || event.getState().getBlock().equals(Blocks.BLACK_BED)
        || event.getState().getBlock().equals(Blocks.BLUE_BED) || event.getState().getBlock().equals(Blocks.CYAN_BED)
        || event.getState().getBlock().equals(Blocks.GRAY_BED) || event.getState().getBlock().equals(Blocks.GREEN_BED)
        || event.getState().getBlock().equals(Blocks.RED_BED) || event.getState().getBlock().equals(Blocks.YELLOW_BED)
        || event.getState().getBlock().equals(Blocks.ORANGE_BED) || event.getState().getBlock().equals(Blocks.LIME_BED)
        || event.getState().getBlock().equals(Blocks.LIGHT_BLUE_BED) || event.getState().getBlock().equals(Blocks.LIGHT_GRAY_BED)
        || event.getState().getBlock().equals(Blocks.WHITE_BED) || event.getState().getBlock().equals(Blocks.PURPLE_BED)
        || event.getState().getBlock().equals(Blocks.PINK_BED) || event.getState().getBlock().equals(Blocks.MAGENTA_BED)
        || event.getState().getBlock().equals(Blocks.RESPAWN_ANCHOR)) {
            if (event.getLevel().dimensionType().minY() == 0) {
                event.getLevel().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 0);
            }
        }
    }

    static int ticks = 0;
    @SubscribeEvent
    public static void dragonTicks(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof EnderDragon && !event.getEntity().level().isClientSide) {
            ticks++;
            if (ticks % 15 == 0) {
                event.getEntity().heal(1);
            }
            event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(300.0);
            if (event.getEntity().getY() > 140) {
                event.getEntity().teleportTo(event.getEntity().getX(), 140, event.getEntity().getZ());
            }
        }
    }
    @SubscribeEvent
    public static void copperArmorTicks(LivingEvent.LivingTickEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem().equals(ModItems.COPPER_BOOTS.get())) {
            ItemStack item = event.getEntity().getItemBySlot(EquipmentSlot.FEET);
            if (item.get(ModDataComponentTypes.OXIDIZATION.get()) == null) {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), 0);
            } else {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), item.get(ModDataComponentTypes.OXIDIZATION.get()) + 1);
                if (item.get(ModDataComponentTypes.OXIDIZATION.get()) > 24000) {
                    ItemStack newItem = new ItemStack(ModItems.OXIDIZED_BOOTS.get());
                    event.getEntity().setItemSlot(EquipmentSlot.FEET, newItem);
                }
            }
        }
        if (event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ModItems.COPPER_CHESTPLATE.get())) {
            ItemStack item = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
            if (item.get(ModDataComponentTypes.OXIDIZATION.get()) == null) {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), 0);
            } else {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), item.get(ModDataComponentTypes.OXIDIZATION.get()) + 1);
                if (item.get(ModDataComponentTypes.OXIDIZATION.get()) > 24000) {
                    ItemStack newItem = new ItemStack(ModItems.OXIDIZED_CHESTPLATE.get());
                    event.getEntity().setItemSlot(EquipmentSlot.CHEST, newItem);
                }
            }
        }
        if (event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem().equals(ModItems.COPPER_LEGGINGS.get())) {
            ItemStack item = event.getEntity().getItemBySlot(EquipmentSlot.LEGS);
            if (item.get(ModDataComponentTypes.OXIDIZATION.get()) == null) {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), 0);
            } else {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), item.get(ModDataComponentTypes.OXIDIZATION.get()) + 1);
                if (item.get(ModDataComponentTypes.OXIDIZATION.get()) > 24000) {
                    ItemStack newItem = new ItemStack(ModItems.OXIDIZED_LEGGINGS.get());
                    event.getEntity().setItemSlot(EquipmentSlot.LEGS, newItem);
                }
            }
        }
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem().equals(ModItems.COPPER_HELMET.get())) {
            ItemStack item = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
            if (item.get(ModDataComponentTypes.OXIDIZATION.get()) == null) {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), 0);
            } else {
                item.set(ModDataComponentTypes.OXIDIZATION.get(), item.get(ModDataComponentTypes.OXIDIZATION.get()) + 1);
                if (item.get(ModDataComponentTypes.OXIDIZATION.get()) > 24000) {
                    ItemStack newItem = new ItemStack(ModItems.OXIDIZED_HELMET.get());
                    event.getEntity().setItemSlot(EquipmentSlot.HEAD, newItem);
                }
            }
        }
    }
    @SubscribeEvent
    public static void onEyeOfEnder(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().getItem().equals(Items.ENDER_EYE) && !event.getLevel().isClientSide
                && !(event.getEntity().getItemInHand(InteractionHand.OFF_HAND).getItem().equals(ModItems.OVERWORLD_ZENITH.get())
        || event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem().equals(ModItems.OVERWORLD_ZENITH.get()))) {
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onSculkCatalystBroken(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock().equals(Blocks.SCULK_CATALYST)) {
            event.setCanceled(true);
            event.getLevel().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 0);
        }
    }
    public static int pTicks = 0;
    @SubscribeEvent
    public static void bridgeBuilder(LivingEvent.LivingTickEvent event) {
        if (event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem().equals(ModItems.BRIDGE_BUILDER.get()) && !event.getEntity().level().isClientSide && event.getEntity() != null) {
            if (event.getEntity().getMainHandItem().get(ModDataComponentTypes.OXIDIZATION.get()) != null && event.getEntity() instanceof Player) {
                if (pTicks < ((BridgeBuilderItem) event.getEntity().getMainHandItem().getItem()).getBlocksToPlace().size()) {
                    BlockPos pos = ((BridgeBuilderItem) event.getEntity().getMainHandItem().getItem()).getBlocksToPlace().get(pTicks);
                    event.getEntity().level().setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 2);
                    ((Player) event.getEntity()).getCooldowns().addCooldown(ModItems.BRIDGE_BUILDER.get(), 400);
                    event.getEntity().level().playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS);
                    pTicks++;
                }
                if (pTicks > ((BridgeBuilderItem) event.getEntity().getMainHandItem().getItem()).getBlocksToPlace().size() - 1) {
                    ((BridgeBuilderItem) event.getEntity().getMainHandItem().getItem()).resetBlocksToPlace();
                    event.getEntity().getMainHandItem().set(ModDataComponentTypes.OXIDIZATION.get(), null);
                    pTicks = 0;
                }
            }
        }
    }
    @SubscribeEvent
    public static void jumpExplode(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof ServerPlayer && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ModItems.GEMSTONE_CHESTPLATE.get()) && event.getEntity().getOffhandItem().getItem().equals(ModItems.EXPLOSION_SINGULARITY.get())) {
            if (!((ServerPlayer) event.getEntity()).getCooldowns().isOnCooldown(ModItems.EXPLOSION_SINGULARITY.get()) && event.getEntity().isCrouching()) {
                event.getEntity().level().explode(event.getEntity(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), 6, Level.ExplosionInteraction.TRIGGER);
                ((ServerPlayer) event.getEntity()).getCooldowns().addCooldown(ModItems.EXPLOSION_SINGULARITY.get(), 500);
            }
        }
    }
    @SubscribeEvent
    public static void jumpTeleport(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof ServerPlayer && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem().equals(ModItems.GEMSTONE_HELMET.get()) && event.getEntity().getOffhandItem().getItem().equals(ModItems.TELEPORTATION_SINGULARITY.get()) && event.getEntity() != null) {
            if (!(((ServerPlayer) event.getEntity()).getCooldowns().isOnCooldown(ModItems.TELEPORTATION_SINGULARITY.get())) && event.getEntity().isCrouching()) {
                if (Math.abs(Objects.requireNonNull(Objects.requireNonNull(((ServerPlayer) event.getEntity())).getRespawnPosition()).getX() - event.getEntity().getX()) <= 10 &&
                        (Math.abs(Objects.requireNonNull(((ServerPlayer) event.getEntity())).getRespawnPosition().getY() - event.getEntity().getY())) <= 10 &&
                        (Math.abs(Objects.requireNonNull(((ServerPlayer) event.getEntity())).getRespawnPosition().getZ() - event.getEntity().getZ())) <= 10) {
                    if (event.getEntity().getOffhandItem().get(ModDataComponentTypes.COORDINATES.get()) != null) {
                        event.getEntity().teleportTo(Objects.requireNonNull(event.getEntity().getOffhandItem().get(ModDataComponentTypes.COORDINATES.get())).getX(), Objects.requireNonNull(event.getEntity().getOffhandItem().get(ModDataComponentTypes.COORDINATES.get())).getY() + 1, Objects.requireNonNull(event.getEntity().getOffhandItem().get(ModDataComponentTypes.COORDINATES.get())).getZ());
                        ((ServerPlayer) event.getEntity()).getCooldowns().addCooldown(ModItems.TELEPORTATION_SINGULARITY.get(), 1200);
                        event.getEntity().level().playSound(null, event.getEntity().getOnPos(), SoundEvents.PLAYER_TELEPORT, SoundSource.AMBIENT);
                    }
                } else {
                    event.getEntity().getOffhandItem().set(ModDataComponentTypes.COORDINATES.get(), event.getEntity().getOnPos());
                    ((ServerPlayer) event.getEntity()).teleportTo(((ServerPlayer) event.getEntity()).getRespawnPosition().getX(), ((ServerPlayer) event.getEntity()).getRespawnPosition().getY() + 1, ((ServerPlayer) event.getEntity()).getRespawnPosition().getZ());
                    ((ServerPlayer) event.getEntity()).getCooldowns().addCooldown(ModItems.TELEPORTATION_SINGULARITY.get(), 1200);
                    event.getEntity().level().playSound(null, event.getEntity().getOnPos(), SoundEvents.PLAYER_TELEPORT, SoundSource.AMBIENT);
                }
            }
        }
    }
    @SubscribeEvent
    public static void changeDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
        for (ItemStack stack : event.getEntity().getInventory().items) {
            stack.set(ModDataComponentTypes.COORDINATES.get(), null);
        }
    }
    static int stage = -1;
    @SubscribeEvent
    public static void dragonStage(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof EnderDragon && event.getEntity() != null) {
            if (stage == -1) {
                event.getEntity().heal(500f);
            }
            if (stage == -1 && event.getEntity().getHealth() >= 300f) {
                stage = 0;
            }
            if (event.getEntity().getHealth() <= 200.0f && stage == 0) {
                event.getEntity().heal(500.0f);
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 4));
                stage = 1;
                summonDragonWave1(event.getEntity().level());
            }
            if (event.getEntity().getHealth() <= 100.0f && stage == 1) {
                event.getEntity().heal(500f);
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 4));
                stage = 2;
                summonDragonWave2(event.getEntity().level());
            }
        }
    }
    public static void summonDragonWave1(Level pLevel) {
        BlockPos pPos1 = new BlockPos(0, 80, 0);
        while (pLevel.getBlockState(pPos1).getBlock() != Blocks.BEDROCK) {
            pPos1 = pPos1.below(1);
        }
        pPos1 = pPos1.above(1);
        wave1Mob(pLevel, pPos1);
        wave1Mob(pLevel, pPos1);
        wave1Mob(pLevel, pPos1);
        wave1Mob(pLevel, pPos1);
        wave1Mob(pLevel, pPos1);
        BlockPos pPos2 = new BlockPos(30, 80, 0);
        while (pLevel.getBlockState(pPos2).getBlock() != Blocks.END_STONE) {
            pPos2 = pPos2.below(1);
        }
        pPos2 = pPos2.above(1);
        wave1Mob(pLevel, pPos2);
        wave1Mob(pLevel, pPos2);
        BlockPos pPos3 = new BlockPos(-30, 80, 0);
        while (pLevel.getBlockState(pPos3).getBlock() != Blocks.END_STONE) {
            pPos3 = pPos3.below(1);
        }
        pPos3 = pPos3.above(1);
        wave1Mob(pLevel, pPos3);
        wave1Mob(pLevel, pPos3);
        BlockPos pPos4 = new BlockPos(0, 80, 30);
        while (pLevel.getBlockState(pPos4).getBlock() != Blocks.END_STONE) {
            pPos4 = pPos4.below(1);
        }
        pPos4 = pPos4.above(1);
        wave1Mob(pLevel, pPos4);
        wave1Mob(pLevel, pPos4);
        BlockPos pPos5 = new BlockPos(0, 80, -30);
        while (pLevel.getBlockState(pPos5).getBlock() != Blocks.END_STONE) {
            pPos5 = pPos5.below(1);
        }
        pPos5 = pPos5.above(1);
        wave1Mob(pLevel, pPos5);
        wave1Mob(pLevel, pPos5);
    }
    public static void summonDragonWave2(Level pLevel) {
        BlockPos pPos = new BlockPos(0, 80, 0);
        while (pLevel.getBlockState(pPos).getBlock() != Blocks.BEDROCK) {
            pPos = pPos.below(1);
        }
        pPos = pPos.above(1);
        wave2Mob(pLevel, pPos);
        wave2Mob(pLevel, pPos);
        wave2Mob(pLevel, pPos);
        wave2Mob(pLevel, pPos);
        wave2Mob(pLevel, pPos);
        wave2Mob(pLevel, pPos);
        wave2Mob(pLevel, pPos);
        BlockPos pPos2 = new BlockPos(30, 80, 0);
        while (pLevel.getBlockState(pPos2).getBlock() != Blocks.END_STONE) {
            pPos2 = pPos2.below(1);
        }
        pPos2 = pPos2.above(1);
        wave2Mob(pLevel, pPos2);
        wave2Mob(pLevel, pPos2);
        BlockPos pPos3 = new BlockPos(-30, 80, 0);
        while (pLevel.getBlockState(pPos3).getBlock() != Blocks.END_STONE) {
            pPos3 = pPos3.below(1);
        }
        pPos3 = pPos3.above(1);
        wave2Mob(pLevel, pPos3);
        wave2Mob(pLevel, pPos3);
        BlockPos pPos4 = new BlockPos(0, 80, 30);
        while (pLevel.getBlockState(pPos4).getBlock() != Blocks.END_STONE) {
            pPos4 = pPos4.below(1);
        }
        pPos4 = pPos4.above(1);
        wave2Mob(pLevel, pPos4);
        wave2Mob(pLevel, pPos4);
        BlockPos pPos5 = new BlockPos(0, 80, -30);
        while (pLevel.getBlockState(pPos5).getBlock() != Blocks.END_STONE) {
            pPos5 = pPos5.below(1);
        }
        pPos5 = pPos5.above(1);
        wave2Mob(pLevel, pPos5);
        wave2Mob(pLevel, pPos5);
        BlockPos pPos6 = new BlockPos(20, 80, 20);
        while (pLevel.getBlockState(pPos6).getBlock() != Blocks.END_STONE) {
            pPos6 = pPos6.below(1);
        }
        pPos6 = pPos6.above(1);
        wave2Mob(pLevel, pPos6);
        wave2Mob(pLevel, pPos6);
        BlockPos pPos7 = new BlockPos(-20, 80, -20);
        while (pLevel.getBlockState(pPos7).getBlock() != Blocks.END_STONE) {
            pPos7 = pPos7.below(1);
        }
        pPos7 = pPos7.above(1);
        wave2Mob(pLevel, pPos7);
        wave2Mob(pLevel, pPos7);
        BlockPos pPos8 = new BlockPos(20, 80, -20);
        while (pLevel.getBlockState(pPos8).getBlock() != Blocks.END_STONE) {
            pPos8 = pPos8.below(1);
        }
        pPos8 = pPos8.above(1);
        wave2Mob(pLevel, pPos8);
        wave2Mob(pLevel, pPos8);
        BlockPos pPos9 = new BlockPos(-20, 80, 20);
        while (pLevel.getBlockState(pPos9).getBlock() != Blocks.END_STONE) {
            pPos9 = pPos9.below(1);
        }
        pPos9 = pPos9.above(1);
        wave2Mob(pLevel, pPos9);
        wave2Mob(pLevel, pPos9);
    }
    public static void wave1Mob(Level pLevel, BlockPos pPos) {
        TriceratopsEntity triceratops5 = new TriceratopsEntity(ModEntities.TRICERATOPS.get(), pLevel);
        triceratops5.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        pLevel.addFreshEntity(triceratops5);
        Vindicator vindicator5 = new Vindicator(EntityType.VINDICATOR, pLevel);
        vindicator5.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        vindicator5.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.DIAMOND_AXE));
        vindicator5.startRiding(triceratops5);
        pLevel.addFreshEntity(vindicator5);
        Phantom phantom5 = new Phantom(EntityType.PHANTOM, pLevel);
        phantom5.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        pLevel.addFreshEntity(phantom5);
        Stray stray = new Stray(EntityType.STRAY, pLevel);
        stray.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        stray.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
        stray.startRiding(phantom5);
        pLevel.addFreshEntity(stray);
        stray.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0));
    }
    public static void wave2Mob(Level pLevel, BlockPos pPos) {
        TriceratopsEntity triceratops5 = new TriceratopsEntity(ModEntities.TRICERATOPS.get(), pLevel);
        triceratops5.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        pLevel.addFreshEntity(triceratops5);
        Vindicator vindicator5 = new Vindicator(EntityType.VINDICATOR, pLevel);
        vindicator5.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        vindicator5.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.NETHERITE_AXE));
        vindicator5.startRiding(triceratops5);
        pLevel.addFreshEntity(vindicator5);
        vindicator5.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0));
        Phantom phantom5 = new Phantom(EntityType.PHANTOM, pLevel);
        phantom5.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        pLevel.addFreshEntity(phantom5);
        Stray stray = new Stray(EntityType.STRAY, pLevel);
        stray.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        stray.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
        stray.startRiding(phantom5);
        pLevel.addFreshEntity(stray);
        stray.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1));
        Breeze breeze = new Breeze(EntityType.BREEZE, pLevel);
        breeze.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
        pLevel.addFreshEntity(breeze);
        breeze.addEffect(new MobEffectInstance(MobEffects.GLOWING, 600, 0));
    }
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == ModVillagers.END_VILLAGER.get()) {
            var trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 6),
                    new ItemStack(ModItems.GEMSTONE_LOCATOR.get(), 1), 6, 4, 0.05f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.ROCK_SINGULARITY.get(), 1),
                    new ItemStack(Items.EMERALD, 1), 16, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 32),
                    new ItemStack(Items.WITHER_SKELETON_SKULL, 1), 6, 8, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.AMETHYST_SHARD, 7),
                    new ItemStack(Items.EMERALD, 1), 6, 8, 0.05f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 18),
                    new ItemStack(ModItems.GEMSTONE_UPGRADE_TEMPLATE.get(), 1), 6, 12, 0.05f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 48),
                    new ItemStack(Items.SCULK_SHRIEKER, 1), 6, 12, 0.05f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 22),
                    new ItemStack(ModItems.DIAMOND_APPLE.get(), 1), 8, 16, 0.05f));

            ItemStack ominous = new ItemStack(Items.OMINOUS_BOTTLE, 1);
            ominous.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER, 4);
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 24),
                    ominous, 8, 16, 0.05f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 52),
                    new ItemStack(Blocks.END_PORTAL_FRAME, 1), 12, 16, 0.05f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 64),
                    new ItemStack(Items.HEAVY_CORE, 1), 6, 16, 0.05f));
        }
    }
}
