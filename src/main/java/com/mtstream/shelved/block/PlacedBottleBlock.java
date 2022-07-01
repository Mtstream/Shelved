package com.mtstream.shelved.block;

import com.mtstream.shelved.util.VoxelShapes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedBottleBlock extends WaterPlacedItemBlock{
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos,
			CollisionContext con) {
		return VoxelShapes.SHAPE4048D8;
	}

	public PlacedBottleBlock(Properties prop, Item item) {
		super(prop, item);
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
}
