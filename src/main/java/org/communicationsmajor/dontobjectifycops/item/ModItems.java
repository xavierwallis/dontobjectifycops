package org.communicationsmajor.dontobjectifycops.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.communicationsmajor.dontobjectifycops.Dontobjectifycops;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dontobjectifycops.MOD_ID);
    public static final RegistryObject<Item> DIRT_BALL = ITEMS.register("dirt_ball",
            () -> new Item(
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_MATERIALS)
                            .stacksTo(69)
            )
    );

    public static final RegistryObject<Item> MAGIC_MIC = ITEMS.register("magic_mic",
            () -> new MagicMicItem(
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_COMBAT)
                            .stacksTo(7)
            )
    );

    public static final RegistryObject<Item> BALL_OF_GOOP = ITEMS.register("ball_of_goop",
            ()-> new BallOfGoop(
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_FOOD)
                            .stacksTo(13)
                            .food((new FoodProperties.Builder()).nutrition(12).saturationMod(2.0f).build())

            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
