package com.mtstream.shelved.init;

import com.mtstream.shelved.Shelved;
import com.mtstream.shelved.feature.AppleFeature;
import com.mtstream.shelved.feature.NautilusShellFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureInit {
	public static final DeferredRegister<Feature<?>> FEATURE = DeferredRegister.create(ForgeRegistries.FEATURES, Shelved.MOD_ID);
	
	public static final RegistryObject<AppleFeature> APPLE = FEATURE.register("apple_feature", 
			() ->  new AppleFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<NautilusShellFeature> NAUTILUS_SHELL = FEATURE.register("nautilus_shell_feature", 
			() ->  new NautilusShellFeature(ProbabilityFeatureConfiguration.CODEC));
	
}
