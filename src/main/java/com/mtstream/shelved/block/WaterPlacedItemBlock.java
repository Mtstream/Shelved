package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class WaterPlacedItemBlock extends PlacedItemBlock implements SimpleWaterloggedBlock{
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	@Override
	public BlockState updateShape(BlockState state, Direction dir, BlockState dirstate, LevelAccessor lev,
			BlockPos pos, BlockPos dirpos) {
		if (state.getValue(WATERLOGGED))
			lev.scheduleTick(pos, Fluids.WATER,Fluids.WATER.getTickDelay(lev));
		return state;
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState state = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(WATERLOGGED, state.is(FluidTags.WATER)&&state.isSource());
	}

	public WaterPlacedItemBlock(Properties prop, Item item) {
		super(prop, item);
	}
	
	
	
}
