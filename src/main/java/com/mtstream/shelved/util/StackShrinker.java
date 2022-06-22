package com.mtstream.shelved.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class StackShrinker {
	public static void shrinkStack(Player pla,ItemStack stack) {
		if(!pla.getAbilities().instabuild) {
			stack.shrink(1);
		}
	}
}
