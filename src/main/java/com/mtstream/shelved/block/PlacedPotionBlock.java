package com.mtstream.shelved.block;

import java.util.ArrayList;
import java.util.List;

import com.mtstream.shelved.blockEntity.PlacedPotionBlockEntity;
import com.mtstream.shelved.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndGatewayBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;

public class PlacedPotionBlock extends WaterPlacedItemEntityBlock{
	
	public static final BooleanProperty HAS_EFFECT = BooleanProperty.create("has_effect");
	
	public PlacedPotionBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(HAS_EFFECT, false));
	}
	@Override
	public RenderShape getRenderShape(BlockState p_49232_) {
		return RenderShape.MODEL;
	}
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla,
			InteractionHand han, BlockHitResult p_60508_) {
		BlockEntity blkEnt = lev.getBlockEntity(pos);
		if(!lev.isClientSide) {
			if(blkEnt instanceof PlacedPotionBlockEntity potionBlkEnt) {
				potionBlkEnt.setPotion(pla.getItemInHand(han), pos);
				potionBlkEnt.showPotion(PotionUtils.getPotion(pla.getItemInHand(han)).getName(""), pla);
			}
			return InteractionResult.CONSUME;
		}else {
			return InteractionResult.SUCCESS;
		}
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(HAS_EFFECT, WATERLOGGED);
	}
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityInit.PLACED_POTION.get().create(pos, state);
	}
	
	@Override
	public List<ItemStack> getDrops(BlockState state,
			net.minecraft.world.level.storage.loot.LootContext.Builder builder) {
		List<ItemStack> drops = new ArrayList<>();
		drops.add(PotionUtils.setPotion(new ItemStack(Items.POTION), ((PlacedPotionBlockEntity)builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY)).getPotion()));
		return drops;
	}
	@Override
	public void onPlace(BlockState state, Level lev, BlockPos pos, BlockState state1, boolean bln) {
		BlockEntity blkEnt = lev.getBlockEntity(pos);
		if(blkEnt instanceof PlacedPotionBlockEntity potionBlkEnt) {
			lev.setBlock(pos, state.setValue(HAS_EFFECT, potionBlkEnt.hasEffect()), 2);
		}
	}
	@Override
	public ItemStack getCloneItemStack(BlockGetter lev, BlockPos pos, BlockState state) {
		BlockEntity blkEnt = lev.getBlockEntity(pos);
		if(blkEnt instanceof PlacedPotionBlockEntity potionBlkEnt) {
			return PotionUtils.setPotion(new ItemStack(Items.POTION), potionBlkEnt.getPotion());
		}else {
			return new ItemStack(Items.SNOWBALL);
		}
	}
}
