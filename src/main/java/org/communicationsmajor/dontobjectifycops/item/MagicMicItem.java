package org.communicationsmajor.dontobjectifycops.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MagicMicItem extends Item {
    public MagicMicItem(Properties properties){
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player user, @NotNull InteractionHand hand) {
        if ( !world.isClientSide() || hand.equals(InteractionHand.OFF_HAND)) { return InteractionResultHolder.fail(user.getItemInHand(hand)); }
        int factor = user.isCrouching() ? -2 : 3;
        var hitResult = Minecraft.getInstance().hitResult;
        if ( hitResult.distanceTo(user) > user.getReachDistance() + 12 ) {
            user.setDeltaMovement(user.getViewVector(0.2f).multiply(factor, factor, factor));
            user.stopFallFlying();
        }
        return InteractionResultHolder.pass(user.getItemInHand(hand));
    }
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if ( !context.getLevel().isClientSide() || context.getHand().equals(InteractionHand.OFF_HAND)) { return super.useOn(context); }
        BlockPos block = context.getClickedPos();
        Level level = context.getLevel();
        Entity entity;
        if (Objects.requireNonNull(context.getPlayer()).isCrouching()) {
            entity = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            context.getPlayer().displayClientMessage(Component.nullToEmpty("UGHGHHHHHHh I love cops"), false);
            entity.moveTo(block, 0f, 0f);
        }
        else {
            entity = new Cow(EntityType.COW, level);
            context.getPlayer().displayClientMessage(Component.nullToEmpty("I want this cow to step on me UGH please step on me i just want it to fucking step on me all over all sexy like please"), false);
            entity.moveTo(block.getX(), block.getY() + 1, block.getZ());
        }
        level.addFreshEntity(entity);
        return super.useOn(context);
    }
}
