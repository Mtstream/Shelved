package com.mtstream.shelved.block;

import com.mtstream.shelved.util.StackShrinker;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedCookieBlock extends WaterPlacedItemBlock{
	
	public static final IntegerProperty COUNT = IntegerProperty.create("count", 1, 8);

	public PlacedCookieBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(COUNT, 1));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(COUNT,WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos,
			CollisionContext con) {
		return Shapes.box(0.25, 0, 0.25, 0.75, 0.0625*state.getValue(COUNT), 0.75);
	}
	
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla,
			InteractionHand han, BlockHitResult res) {
		int i = state.getValue(COUNT)+1;
		if(pla.getItemInHand(han).is(Items.COOKIE)) {
			if(!lev.isClientSide) {
				if(i<9) {
					StackShrinker.shrinkStack(pla, pla.getItemInHand(han));
					lev.setBlock(pos, state.setValue(COUNT, i), 3);
					pushEntitiesUp(state, state.setValue(COUNT, i), lev, pos);
				}
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}else {
			if(!lev.isClientSide) {
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}
	}
}
