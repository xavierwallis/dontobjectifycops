package org.communicationsmajor.dontobjectifycops.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BallOfGoop extends Item {
    public BallOfGoop(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getDamage(ItemStack stack) {
        return 300;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 100000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()) { super.use(pLevel, pPlayer, pUsedHand); }
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (itemStack.isEnchanted()) {
            if ( pPlayer.isInvulnerable() ) {
                pPlayer.displayClientMessage(Component.nullToEmpty("you a bitch now"), false);
            }
            pPlayer.setInvulnerable(false);
            pPlayer.setItemInHand(pUsedHand, new ItemStack(ModItems.BALL_OF_GOOP.get(), itemStack.getCount()));
        }
        else {
            if ( !pPlayer.isInvulnerable() ) {
                pPlayer.displayClientMessage(Component.nullToEmpty("giga chad mode activated"), false);
            }
            pPlayer.setInvulnerable(true);
            pPlayer.getItemInHand(pUsedHand).enchant(Enchantments.FISHING_LUCK, 120);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
