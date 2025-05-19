package com.caleb.gemstonemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class PearlOnAStickItem extends FishingRodItem {

    public PearlOnAStickItem(Properties pProperties) {super(pProperties);}
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pPlayer.fishing != null && !pLevel.isClientSide) {
            pLevel.playSound(null, new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY(), pPlayer.getBlockZ()), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
            pPlayer.teleportTo(pPlayer.fishing.getX(), pPlayer.fishing.getY(), pPlayer.fishing.getZ());
            pPlayer.fishing.kill();
            pPlayer.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        }
        else if (pPlayer.fishing == null && !pLevel.isClientSide) {
            pLevel.playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.FISHING_BOBBER_THROW,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (pLevel instanceof ServerLevel serverlevel) {
                int j = (int)(EnchantmentHelper.getFishingTimeReduction(serverlevel, itemstack, pPlayer) * 20.0F);
                int k = EnchantmentHelper.getFishingLuckBonus(serverlevel, itemstack, pPlayer);
                pLevel.addFreshEntity(new FishingHook(pPlayer, pLevel, k, j));
            }

            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            pPlayer.gameEvent(GameEvent.ITEM_INTERACT_START);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

}