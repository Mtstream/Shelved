package com.mtstream.shelved.event;

import com.mtstream.shelved.PlaceableItemsMap;
import com.mtstream.shelved.Shelved;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Shelved.MOD_ID)
public class RightClickEvt {
	@SubscribeEvent
	public static void rightclickPlace(PlayerInteractEvent.RightClickBlock evt) {
		BlockPos pos = evt.getPos();
		Player pla = evt.getPlayer();
		InteractionHand han = evt.getHand();
		ItemStack stack = pla.getItemInHand(han);
		Level lev = evt.getWorld();
		BlockState state = lev.getBlockState(pos);
		Block blk = PlaceableItemsMap.getBlock(stack.getItem());
		if(blk != null) {
			InteractionResult res = ((BlockItem) blk.asItem()).place(new BlockPlaceContext(new UseOnContext(pla, han, new BlockHitResult(pla.getLookAngle(), evt.getFace(), pos, false))));
			evt.setCanceled(res == InteractionResult.SUCCESS);
			evt.setCancellationResult(res);
		}
	}
}
