package com.mtstream.shelved.block;

import com.mtstream.shelved.util.VoxelShapes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlacedFireChargeBlock extends WaterPlacedItemBlock{
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter lev, BlockPos pos, CollisionContext con) {
		return VoxelShapes.SHAPE404888;
	}

	public PlacedFireChargeBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	@Override
	public InteractionResult use(BlockState state, Level lev, BlockPos pos, Player pla, InteractionHand han,
			BlockHitResult res) {
		if(pla.getItemInHand(han).getItem() instanceof FlintAndSteelItem) {
			if(!lev.isClientSide) {
				lev.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				Fireball fireball = EntityType.FIREBALL.create(lev);
				fireball.setPos(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5);
				lev.addFreshEntity(fireball);
				lev.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
				return InteractionResult.CONSUME;
			}else {
				return InteractionResult.SUCCESS;
			}
		}else {
			return InteractionResult.PASS;
		}
	}

}
