package com.mtstream.shelved.block;

import com.mtstream.shelved.blockEntity.PlacedEnchantedBookBlockEntity;
import com.mtstream.shelved.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedEnchantedBookBlock extends WaterPlacedItemEntityBlock{

	public static final BooleanProperty OPENED = BooleanProperty.create("opened");
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public PlacedEnchantedBookBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(OPENED, false).setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(OPENED, FACING, WATERLOGGED);
	}
	
	@Override
	public void setPlacedBy(Level lev, BlockPos pos, BlockState state, LivingEntity ent,
			ItemStack stack) {
		if(lev.getBlockEntity(pos) instanceof PlacedEnchantedBookBlockEntity blkEnt) {
			blkEnt.setEnchantment(stack);
		}
	}
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityInit.PLACED_ENCHANTED_BOOK.get().create(pos, state);
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos, CollisionContext con) {
		return state.getValue(FACING).getAxis() == Axis.Z?state.getValue(OPENED)?Shapes.box(0, 0, 0.125, 1, 0.125, 0.875):Shapes.box(0.25, 0, 0.125, 0.8125, 0.25, 0.875):
			state.getValue(OPENED)?Shapes.box(0.125, 0, 0, 0.875, 0.125, 1):Shapes.box(0.15625, 0, 0.21875, 0.90625, 0.25, 0.78125);
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext con) {
		return super.getStateForPlacement(con).setValue(FACING, con.getHorizontalDirection());
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
	@Override
	public ItemStack getCloneItemStack(BlockGetter lev, BlockPos pos, BlockState state) {
		ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
		if(lev.getBlockEntity(pos) instanceof PlacedEnchantedBookBlockEntity blkEnt) stack.getOrCreateTag().put(EnchantedBookItem.TAG_STORED_ENCHANTMENTS, blkEnt.getEnchantment());
		return stack;
	}
}
