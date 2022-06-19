package com.mtstream.shelved.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedEnchantedAppleBlock extends WaterPlacedItemBlock{
	
	public static final DirectionProperty FACING = BlockStateProperties.VERTICAL_DIRECTION;
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_,
			CollisionContext p_60558_) {
		return state.getValue(FACING)==Direction.DOWN?Shapes.box(0.25, 0, 0.25, 0.75, 0.5, 0.75):Shapes.box(0.25, 0.3125, 0.25, 0.75, 0.8125, 0.75);
	}
	
	@Override
	public void animateTick(BlockState state, Level lev, BlockPos pos, Random ran) {
		lev.addParticle(ParticleTypes.ENCHANT, pos.getX()+ran.nextDouble(), pos.getY()+ran.nextDouble(), pos.getZ()+ran.nextDouble(), 0, 0, 0);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext con) {
		Direction dir = con.getClickedFace();
		dir = dir==Direction.DOWN||dir==Direction.UP?dir:Direction.DOWN;
		return super.getStateForPlacement(con).setValue(FACING, dir.getOpposite());
	}
	
	public PlacedEnchantedAppleBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, false));
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

}
