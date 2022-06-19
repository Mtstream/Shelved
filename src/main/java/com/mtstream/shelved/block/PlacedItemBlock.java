package com.mtstream.shelved.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

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
}
