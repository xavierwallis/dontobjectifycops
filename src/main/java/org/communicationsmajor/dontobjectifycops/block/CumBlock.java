package org.communicationsmajor.dontobjectifycops.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.event.ColorHandlerEvent;

public class CumBlock extends Block {
    public static final BooleanProperty ENABLED = BooleanProperty.create("enabled");
    public CumBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ENABLED);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide() || pHand != InteractionHand.MAIN_HAND) { return InteractionResult.FAIL; }
        boolean stateValue = pState.getValue(ENABLED);
        if (pPlayer.isCrouching()) {
            pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), 128f, true, Explosion.BlockInteraction.DESTROY);
        }
        else {
            if (!stateValue) { return InteractionResult.FAIL; }
            pLevel.setBlock(pPos, pState.setValue(ENABLED, !stateValue), 3);
            Entity entity = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
            entity.moveTo(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ()), 0, 0);
            pLevel.addFreshEntity(entity);
            for (int i = -64; i <= pPos.getY(); ++i) {
                for (int x = 0; x < 10; ++x) {
                    for (int z = 0; z < 10; ++z) {
                        pLevel.setBlock(new BlockPos(pPos.getX() - 5 + x, i, pPos.getZ() - 5 + z), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
