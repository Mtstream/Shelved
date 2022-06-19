package com.mtstream.shelved.event;

import com.mtstream.shelved.Shelved;
import com.mtstream.shelved.init.BlockInit;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetupEvt {
	@Mod.EventBusSubscriber(modid = Shelved.MOD_ID,bus = Bus.MOD,value = Dist.CLIENT)
	public class ClientEventBusSubcriber {
		@SubscribeEvent
		public static void clienSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(BlockInit.PLACED_NETHER_STAR.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(BlockInit.PLACED_APPLE.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BlockInit.PLACED_GOLDEN_APPLE.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BlockInit.PLACED_ENCHANTED_GOLDEN_APPLE.get(), RenderType.cutout());
		}
	}
}
