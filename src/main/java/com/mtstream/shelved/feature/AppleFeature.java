package com.mtstream.shelved.feature;

import com.mojang.serialization.Codec;
import com.mtstream.shelved.block.PlacedAppleBlock;
import com.mtstream.shelved.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AppleFeature extends Feature<NoneFeatureConfiguration>{

	public AppleFeature(Codec<NoneFeatureConfiguration> p_65786_) {
		super(p_65786_);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> con) {
		WorldGenLevel lev = con.level();
		BlockPos pos = con.origin();
		if(!lev.isEmptyBlock(pos)) {
			return false;
		}else if(!lev.getBlockState(pos.above()).is(Blocks.OAK_LEAVES)){
			return false;
		}else {
			if(con.random().nextInt(50) == 1) {
				lev.setBlock(pos, BlockInit.PLACED_GOLDEN_APPLE.get().defaultBlockState().setValue(PlacedAppleBlock.FACING, Direction.UP), 2);
			}else {
				lev.setBlock(pos, BlockInit.PLACED_APPLE.get().defaultBlockState().setValue(PlacedAppleBlock.FACING, Direction.UP), 2);
			}
			return true;
		}
		
	}

}
