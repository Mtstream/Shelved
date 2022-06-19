package com.mtstream.shelved.event;

import com.mtstream.shelved.Shelved;
import com.mtstream.shelved.gen.AllGen;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Shelved.MOD_ID)
public class BiomeLoadingEvt {
	@SubscribeEvent
	public static void gen(BiomeLoadingEvent evt) {
		AllGen.gen(evt);
	}
}
