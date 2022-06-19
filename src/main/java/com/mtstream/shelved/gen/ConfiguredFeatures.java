package com.mtstream.shelved.gen;

import com.mtstream.shelved.init.FeatureInit;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class ConfiguredFeatures {
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> APPLE = FeatureUtils.register("apple", FeatureInit.APPLE.get(), FeatureConfiguration.NONE);
	public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> NAUTILUS_SHELL = FeatureUtils.register("nautilus_shell", FeatureInit.NAUTILUS_SHELL.get(), new ProbabilityFeatureConfiguration(1f));
}
