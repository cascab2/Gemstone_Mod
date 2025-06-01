package com.caleb.gemstonemod.event;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.enchantment.ModEnchantments;
import com.caleb.gemstonemod.item.ModItems;
import com.caleb.gemstonemod.item.custom.AmberitePickaxeItem;
import com.caleb.gemstonemod.item.custom.OpalitePickaxeItem;
import com.caleb.gemstonemod.item.custom.SaphiriteAxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.w3c.dom.Attr;

import java.util.HashSet;
import java.util.Set;

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
}
