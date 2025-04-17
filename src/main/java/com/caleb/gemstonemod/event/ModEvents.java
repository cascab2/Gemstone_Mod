package com.caleb.gemstonemod.event;

import com.caleb.gemstonemod.GemstoneMod;
import com.caleb.gemstonemod.block.ModBlocks;
import com.caleb.gemstonemod.enchantment.ModEnchantments;
import com.caleb.gemstonemod.item.ModItems;
import com.caleb.gemstonemod.item.custom.AmberitePickaxeItem;
import com.caleb.gemstonemod.item.custom.OpalitePickaxeItem;
import com.caleb.gemstonemod.item.custom.SaphiriteAxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
        if (event.getEntity() instanceof ServerPlayer && event.getEntity() instanceof Player) {
            if (!(mainHandItem.getItem().equals(ModItems.OPALITE_SWORD.get()))) {
                event.getEntity().getAttribute(Attributes.ATTACK_SPEED)
                        .setBaseValue(4.0f);
                event.getEntity().getAttribute(Attributes.ATTACK_DAMAGE)
                        .setBaseValue(1.0f);
            }
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
}
