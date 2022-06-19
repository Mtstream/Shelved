package com.mtstream.shelved.gen;

import com.mtstream.shelved.util.Generator;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
	public static final Holder<PlacedFeature> APPLE_PLACED = PlacementUtils.register("shelved:apple_placed", 
			ConfiguredFeatures.APPLE, Generator.uniGen(200, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(128), VerticalAnchor.aboveBottom(200))));
	public static final Holder<PlacedFeature> NAUTILUS_SHELL_PLACED = PlacementUtils.register("shelve:nautilus_shell_placed", ConfiguredFeatures.NAUTILUS_SHELL, 
			AquaticPlacements.seagrassPlacement(1));
}
