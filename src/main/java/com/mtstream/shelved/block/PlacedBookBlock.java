package com.mtstream.shelved.block;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedBookBlock extends WaterPlacedItemBlock{
	
	public static final BooleanProperty OPENED = BooleanProperty.create("opened");
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	
	public PlacedBookBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(OPENED, false).setValue(FACING, Direction.NORTH));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(OPENED, WATERLOGGED, FACING);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext con) {
		return super.getStateForPlacement(con).setValue(FACING, con.getHorizontalDirection());
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos, CollisionContext con) {
		return state.getValue(FACING).getAxis() == Axis.Z?state.getValue(OPENED)?Shapes.box(0, 0, 0.125, 1, 0.125, 0.875):Shapes.box(0.25, 0, 0.125, 0.8125, 0.25, 0.875):
			state.getValue(OPENED)?Shapes.box(0.125, 0, 0, 0.875, 0.125, 1):Shapes.box(0.15625, 0, 0.21875, 0.90625, 0.25, 0.78125);
	}
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla, InteractionHand han,
			BlockHitResult res) {
		if(!lev.isClientSide) {
			lev.setBlockAndUpdate(pos, state.cycle(OPENED));
			return InteractionResult.CONSUME;
		}else {
			return InteractionResult.SUCCESS;
		}
	}

}
