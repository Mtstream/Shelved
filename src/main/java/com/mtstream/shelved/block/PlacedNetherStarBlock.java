package com.mtstream.shelved.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;

public class PlacedNetherStarBlock extends WaterPlacedItemBlock{

	public PlacedNetherStarBlock(Properties prop, Item item) {
		super(prop, item);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	@Override
	public void animateTick(BlockState state, Level lev, BlockPos pos, Random ran) {
		lev.addParticle(ParticleTypes.END_ROD, pos.getX()+ran.nextDouble(), pos.getY()+ran.nextDouble(), pos.getZ()+ran.nextDouble(), 0, 0, 0);
		lev.addParticle(ParticleTypes.ELECTRIC_SPARK, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 0, 0, 0);
		if(lev.isClientSide)lev.playSound(null, pos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 1f, 1f);
	}

}
