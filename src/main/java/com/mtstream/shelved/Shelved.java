package com.mtstream.shelved;

import com.mtstream.shelved.init.BlockInit;
import com.mtstream.shelved.init.FeatureInit;
import com.mtstream.shelved.init.ItemInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("shelved")
public class Shelved {
	public static final String MOD_ID = "shelved";
	public Shelved() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		FeatureInit.FEATURE.register(bus);
		MinecraftForge.EVENT_BUS.register(bus);
	}
}
