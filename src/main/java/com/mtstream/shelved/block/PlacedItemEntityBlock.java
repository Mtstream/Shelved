package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlacedItemEntityBlock extends BaseEntityBlock{
	
	public Item item;

	protected PlacedItemEntityBlock(Properties prop, Item item) {
		super(prop);
		this.item = item;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return null;
	}
	

	@Override
	public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
		return new ItemStack(item);
	}
	
}
