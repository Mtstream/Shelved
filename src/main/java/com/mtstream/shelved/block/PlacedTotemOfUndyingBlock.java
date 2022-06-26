package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedTotemOfUndyingBlock extends WaterPlacedItemBlock{
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public PlacedTotemOfUndyingBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos,
			CollisionContext con) {
		return state.getValue(FACING).getAxis()==Axis.X?Shapes.box(0.1875, 0, 0.0625, 0.8125, 1, 0.9375):Shapes.box(0.0625, 0, 0.1875, 0.9375, 1, 0.8125);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext con) {
		return super.getStateForPlacement(con).setValue(FACING, con.getHorizontalDirection().getOpposite());
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING,WATERLOGGED);
	}
}
