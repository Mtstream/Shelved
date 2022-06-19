package com.mtstream.shelved.feature;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.mtstream.shelved.block.PlacedNautilusShellBlock;
import com.mtstream.shelved.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.Heightmap;

public class NautilusShellFeature extends Feature<ProbabilityFeatureConfiguration>{

	public NautilusShellFeature(Codec<ProbabilityFeatureConfiguration> codec) {
		super(codec);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> con) {
		boolean flag = false;
	      Random random = con.random();
	      WorldGenLevel worldgenlevel = con.level();
	      BlockPos blockpos = con.origin();
	      int i = random.nextInt(8) - random.nextInt(8);
	      int j = random.nextInt(8) - random.nextInt(8);
	      int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + i, blockpos.getZ() + j);
	      BlockPos blockpos1 = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
	      if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER)) {
	         BlockState blockstate = BlockInit.PLACED_NAUTILUS_SHELL.get().defaultBlockState().setValue(PlacedNautilusShellBlock.WATERLOGGED, true);
	         if (blockstate.canSurvive(worldgenlevel, blockpos1)&&random.nextInt(3)==0) {
	               worldgenlevel.setBlock(blockpos1, blockstate, 2);
	            flag = true;
	         }
	      }

	      return flag;
	}

}
