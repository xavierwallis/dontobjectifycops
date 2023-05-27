package org.communicationsmajor.dontobjectifycops.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class BallOfGoop extends Item {
    public BallOfGoop(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (itemStack.isEnchanted()) {
            pPlayer.setInvulnerable(false);
            pPlayer.setItemInHand(pUsedHand, new ItemStack(ModItems.BALL_OF_GOOP.get(), itemStack.getCount()));
        }
        else {
            pPlayer.setInvulnerable(true);
            pPlayer.getItemInHand(pUsedHand).enchant(Enchantments.FISHING_LUCK, 120);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
