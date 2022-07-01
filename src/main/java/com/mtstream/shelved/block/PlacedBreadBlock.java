package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedBreadBlock extends WaterPlacedItemBlock{
	
	public static final IntegerProperty COUNT = IntegerProperty.create("count", 1, 3);
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public PlacedBreadBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(COUNT, 1).setValue(WATERLOGGED, false));
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos,
			CollisionContext con) {
		return state.getValue(COUNT) < 3 ? Shapes.box(0.09375, 0, 0.09375, 0.90625, 0.25, 0.90625) : Shapes.box(0.09375, 0, 0.09375, 0.90625, 0.5, 0.90625);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext con) {
		return super.getStateForPlacement(con).setValue(FACING, con.getHorizontalDirection().getAxis()==Axis.X?Direction.EAST:Direction.SOUTH);
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING, COUNT,WATERLOGGED);
	}
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla,
			InteractionHand han, BlockHitResult res) {
		int i = state.getValue(COUNT) + 1;
		if(pla.getItemInHand(han).is(Items.BREAD)&&i <= 3) {
			if(!lev.isClientSide) {
				lev.setBlock(pos, state.setValue(COUNT, i), 3);
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}else if(!pla.getItemInHand(han).is(Items.BREAD)){
			if(!lev.isClientSide) {
				lev.setBlock(pos, state.getValue(COUNT) == 1 ? Blocks.AIR.defaultBlockState() : state.setValue(COUNT, i - 2), 3);
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}else {
			return InteractionResult.FAIL;
		}
	}
}
