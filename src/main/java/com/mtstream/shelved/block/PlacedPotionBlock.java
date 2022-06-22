package com.mtstream.shelved.block;

import java.util.ArrayList;
import java.util.List;

import com.mtstream.shelved.blockEntity.PlacedPotionBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;

public class PlacedPotionBlock extends WaterPlacedItemEntityBlock{
	
	public static final BooleanProperty HAS_EFFECT = BooleanProperty.create("has_effect");
	
	public PlacedPotionBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(HAS_EFFECT, false));
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(HAS_EFFECT, WATERLOGGED);
	}
	@Override
	public List<ItemStack> getDrops(BlockState state,
			net.minecraft.world.level.storage.loot.LootContext.Builder builder) {
		List<ItemStack> drops = new ArrayList<>();
		drops.add(PotionUtils.setPotion(new ItemStack(Items.POTION), ((PlacedPotionBlockEntity)builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY)).potion));
		return drops;
	}
	@Override
	public ItemStack getCloneItemStack(BlockGetter lev, BlockPos pos, BlockState state) {
		return PotionUtils.setPotion(new ItemStack(Items.POTION), ((PlacedPotionBlockEntity)builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY)).potion);
	}
}
