package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedItemBlock extends Block{
	
	public Item item;

	public PlacedItemBlock(Properties p_49795_, Item item) {
		super(p_49795_);
		this.item = item;
	}
	
	@Override
	public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
		return new ItemStack(item);
	}
	
	public Item getItem() {
		return item;
	}
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla,
			InteractionHand han, BlockHitResult res) {
		return super.use(state, lev, pos, pla, han, res);
	}
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos,
			CollisionContext con) {
		return super.getShape(state, lev, pos, con);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext con) {
		return super.getStateForPlacement(con);
	}
}
