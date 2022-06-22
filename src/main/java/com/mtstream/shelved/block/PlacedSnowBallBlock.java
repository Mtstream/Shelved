package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedSnowBallBlock extends WaterPlacedItemBlock{
	
	public static final IntegerProperty SIZE = IntegerProperty.create("size", 1, 3);

	public PlacedSnowBallBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(SIZE, 1).setValue(WATERLOGGED, false));
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, SIZE);
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_,
			CollisionContext p_60558_) {
		switch(state.getValue(SIZE)) {
		case 1:
			return Shapes.box(0.25, 0, 0.25, 0.75, 0.5, 0.75);
		case 2:
			return Shapes.box(0.15625, 0, 0.15625, 0.84375, 0.6875, 0.84375);
		case 3:
			return Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.875, 0.9375);
			default:
				return Shapes.box(0.25, 0, 0.25, 0.75, 0.5, 0.75);
		}
	}
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla,
			InteractionHand han, BlockHitResult res) {
		Direction dir = res.getDirection().getOpposite();
		if(pla.getItemInHand(han).is(Items.SNOWBALL)) {
			if(!lev.isClientSide) {
				int i = state.getValue(SIZE) + 1;
				lev.setBlock(pos, i < 4?state.setValue(SIZE, i):Blocks.SNOW_BLOCK.defaultBlockState(), 3);
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}else if(lev.isEmptyBlock(pos.relative(dir))) {
			if(!lev.isClientSide) {
				lev.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				lev.setBlock(pos.relative(dir), state, 3);
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}else if(lev.getBlockState(pos.relative(dir)).is(Blocks.SNOW)) {
			int i = state.getValue(SIZE) + 1;
			if(!lev.isClientSide) {
				lev.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				lev.setBlock(pos.relative(dir), i < 4?state.setValue(SIZE, i):Blocks.SNOW_BLOCK.defaultBlockState(), 3);
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}else {
			return InteractionResult.FAIL;
		}
	}
}
