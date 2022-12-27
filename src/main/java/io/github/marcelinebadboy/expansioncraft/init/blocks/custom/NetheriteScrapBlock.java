package io.github.marcelinebadboy.expansioncraft.init.blocks.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class NetheriteScrapBlock extends RotatedPillarBlock {
	public NetheriteScrapBlock(BlockBehaviour.Properties proprties) {
	      super(proprties);
	      this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y));
	   }
}
